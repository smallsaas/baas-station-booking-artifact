package com.jfeat.am.module.booking.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.booking.services.domain.definition.AdminPermission;
import com.jfeat.am.module.booking.services.domain.definition.AppointmentStatus;
import com.jfeat.am.module.booking.services.domain.model.AppointmentModel;
import com.jfeat.am.module.booking.services.domain.service.DomainQueryService;
import com.jfeat.am.module.booking.services.persistence.model.Appointment;
import com.jfeat.am.module.booking.services.persistence.model.Customer;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.service.crud.AppointmentService;
import com.jfeat.am.module.booking.services.service.crud.StudioOverProductService;
import com.jfeat.am.module.booking.services.service.path.PathService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Administrator on 2017/9/15.
 */
@RestController
@RequestMapping("/api/appointments")
@Configuration
@ConfigurationProperties(prefix = "am")
public class AppointmentEndpoint {

    @Resource
    AppointmentService appointmentService;
    @Resource
    DomainQueryService domainQueryService;
    @Resource
    StudioOverProductService studioService;
    @Resource
    PathService pathService;
/*    @Resource
    WechatPushOrderService wechatPushOrderService;
    @Resource
    WechatConfigService wechatConfigService;*/


    private boolean wechatPushOrder = true;

    public boolean getWechatPushOrder() {
        return wechatPushOrder;
    }

    public void setWechatPushOrder(boolean wechatPushOrder) {
        this.wechatPushOrder = wechatPushOrder;
    }

    @GetMapping("/admin/{id}")
    @Permission(AdminPermission.QUERY)
    public Tip queryAppointment(@PathVariable long id) {
        return SuccessTip.create(pathService.appointmentDetails(id));
    }

    @PutMapping("/admin")
    @Permission(AdminPermission.EDIT)
    public Tip editAppointment(@Valid @RequestBody Appointment appointment) {
        return SuccessTip.create(appointmentService.updateMaster(appointment));
    }

    /*
     *   fuzzy query
     * */

    @GetMapping("/lists")
    @Permission(AdminPermission.QUERY)
    public Tip queryAppointment(Page page,
                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                @RequestParam(name = "status", required = false) String status,
                                @RequestParam(name = "studioId", required = false) Long studioId,
                                @RequestParam(name = "phone", required = false) Long phone
    ) {
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        List<AppointmentModel> appointments = domainQueryService.queryAppointment(page, status, studioId, phone);
        page.setRecords(appointments);
        return SuccessTip.create(page);

    }

    /*
     *   queryByStatus
     * */
    @GetMapping("/users")
    public Tip queryAppointmentByStatus(Page page,
                                        @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                        @RequestParam(name = "status", required = false) String status
    ) {
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        long userId = JWTKit.getUserId();
        Customer customer = pathService.queryCustomerByUserId(userId);
        if (customer == null) {
            throw new BusinessException(BusinessCode.InvalidKey.getCode(), "customer not found.");
        }
//        List<Appointment> appointments = domainQueryService.queryAppointmentByStatus(page, status);
        List<AppointmentModel> models = domainQueryService.queryAppointmentByStatus(page, customer.getId(), status);

        page.setRecords(models);
        return SuccessTip.create(page);

    }

    @PostMapping
    public Tip createAppointment(@Valid @RequestBody Appointment appointment, HttpServletRequest request) {
        Long userId = JWTKit.getUserId();
        Customer customer = pathService.queryCustomerByUserId(userId);
        if (customer == null) {
            throw new BusinessException(BusinessCode.InvalidKey.getCode(), "customer not found.");
        }
        Studio studio = studioService.retrieveMaster(appointment.getStudioId());
        if (studio == null) {
            throw new BusinessException(BusinessCode.InvalidKey.getCode(), "studio not found.");
        }
        appointment.setCustomerName(customer.getName());
        appointment.setCustomerId(customer.getId());
        appointment.setDoctorId(0L);
        appointment.setStatus(AppointmentStatus.PAY_PENDING.toString());
        appointment.setFee(studio.getFee());
        appointment.setCreateTime(new Date());
        Integer result = appointmentService.createMaster(appointment);
        //pushOrder(String title,String detail,String orderNum,String totalFee,Long tenantId,String openid,String ip,String notifyUrl)
        Long tenantId = JWTKit.getOrgId();
        Map map = Maps.newHashMap();
        /* WechatConfig wechatConfig = wechatConfigService.getByTenantId(tenantId);
        Map map = Maps.newHashMap();
        if (getWechatPushOrder()) {
            map = wechatPushOrderService.pushOrder(studio.getName(),
                    studio.getName(),
                    appointment.getId().toString(),
                    appointment.getFee().multiply(BigDecimal.valueOf(100)).intValue() + "",
                    tenantId,
                    customer.getOpenid(),
                    IpKit.getRealIp(request),
                    wechatConfig.getHost() + "/api/pub/wpay/notify/" + wechatConfig.getAppId(),
                    true);
        }*/
        return SuccessTip.create(map);
    }

    @PutMapping("/{id}")
    public Tip updateAppointment(@PathVariable long id, @Valid @RequestBody Appointment appointment) {
        long userId = JWTKit.getUserId();
        Customer customer = pathService.queryCustomerByUserId(userId);
        Appointment appointments = appointmentService.retrieveMaster(id);
        appointment.setId(id);
        System.out.println(appointment);
        System.out.println(appointments);
        if ((appointments.getStatus().compareTo(AppointmentStatus.DONE.toString()) == 0)) {
            throw new BusinessException(BusinessCode.InvalidKey.getCode(), "no permission");
        }
        if ((appointments.getStatus().compareTo(AppointmentStatus.PAY_PENDING.toString()) == 0)
                && !(appointment.getStatus().compareTo(AppointmentStatus.CANCEL.toString()) == 0)) {
            throw new BusinessException(BusinessCode.InvalidKey.getCode(), "no permission");
        }
        if (!(appointments.getCustomerId().equals(customer.getId()))) {
            throw new BusinessException(BusinessCode.NoPermission.getCode(), "no permission");
        }
        return SuccessTip.create(appointmentService.updateMaster(appointment));

        /*if(appointments.getStatus().equals(AppointmentStatus.PAY_PENDING.toString())){
            throw new BusinessException(BizExceptionEnum.NO_PERMISSION.getCode(), "no permission");
        }
        if (appointments.getCustomerId().equals(customer.getId()) && !(appointments.getStatus().equals(AppointmentStatus.DONE.toString()))) {
            return SuccessTip.create(appointmentService.updateMaster(appointment));
        } else {
            throw new BusinessException(BizExceptionEnum.NO_PERMISSION.getCode(), "no permission");
        }*/
    }


    @GetMapping("/{id}")
    public Tip showAppointmentModel(@PathVariable long id) {
        long userId = JWTKit.getUserId();
        Customer customer = pathService.queryCustomerByUserId(userId);
        if (customer == null) {
            throw new BusinessException(BusinessCode.InvalidKey.getCode(), "customer not found.");
        }
        AppointmentModel result = pathService.appointmentDetails(id);
        if (result.getCustomerId() == customer.getId()) {//|| ShiroKit.hasPermission(AdminPermission.QUERY
            return SuccessTip.create(result);
        }
        throw new BusinessException(BusinessCode.NoPermission.getCode(), "no permission to show customer appointment!");

    }

    @DeleteMapping("/{id}")
    public Tip deleteAppointment(@PathVariable long id) {
        long userId = JWTKit.getUserId();
        Customer customer = pathService.queryCustomerByUserId(userId);
        if (customer == null) {
            throw new BusinessException(BusinessCode.InvalidKey.getCode(), "customer not found.");
        }
        Appointment appointment = appointmentService.retrieveMaster(id);
        if (appointment.getCustomerId() == customer.getId()) {//Per.hasPermission(AdminPermission.EDIT)
            Integer result = appointmentService.deleteMaster(id);
            return SuccessTip.create(result);
        } else {
            throw new BusinessException(BusinessCode.NoPermission.getCode(), "no permission to delete customer appointment!");
        }

    }
}
