package com.jfeat.am.module.booking.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
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

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.HttpMethod;

/**
 * Created by Administrator on 2017/9/15.
 */
@RestController
@RequestMapping("/api/appointments")
@Configuration
@ConfigurationProperties(prefix = "am")
@Api(value = "/api/appointments")
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
//    @Permission(AdminPermission.QUERY)
    @ApiOperation(value = "根据提供ID查询订单记录并返回",response = AppointmentModel.class)
    @ApiParam(name = "id",value = "待查询的订单记录ID")
    public Tip queryAppointment( @PathVariable long id) {
        return SuccessTip.create(pathService.appointmentDetails(id));
    }

    @PutMapping("/admin")
//    @Permission(AdminPermission.EDIT)
    @ApiOperation(value = "根据提供提交的订单记录实体类进行数据的更新",response = Integer.class)
    @ApiParam(value = "待更新的订单记录ID")
    public Tip editAppointment(@Valid @RequestBody Appointment appointment) {
        return SuccessTip.create(appointmentService.updateMaster(appointment));
    }

    @GetMapping("/lists")
//    @Permission(AdminPermission.QUERY)
    @ApiOperation(value = "根据请求参数status,studioId,phone配合pageNum与pageSize进行分页条件查询",response = Page.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前请求页",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录条数",paramType = "query"),
            @ApiImplicitParam(name = "status",value = "记录状态",paramType = "query"),
            @ApiImplicitParam(name = "studioId",value = "工作室ID",paramType = "query"),
            @ApiImplicitParam(name = "phone",value = "联系电话",paramType = "query")
    })
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

    @GetMapping("/users")
    @ApiOperation(value = "根据记录状态进行查询，并将结果返回",response = Page.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前请求页",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录条数",paramType = "query"),
            @ApiImplicitParam(name = "status",value = "记录状态",paramType = "query")
    })
    public Tip queryAppointmentByStatus(Page page,
                                        @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                        @RequestParam(name = "status", required = false) String status) {
        page.setSize(pageSize);
        page.setCurrent(pageNum);
//        long userId = JWTKit.getUserId();
        long userId = 1;
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
    @ApiOperation(value = "将传递的Appointment实体类写入数据库并返回数据结果",response = Map.class)
    @ApiParam(name = "appointment",value = "订单记录实体类")
    public Tip createAppointment(@Valid @RequestBody Appointment appointment) {
//        Long userId = JWTKit.getUserId();
        Long userId = 1l;
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
        Long tenantId = 1l;
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
    @ApiOperation(value = "根据提供的ID和appointment进行相关记录的更新操作",response = Integer.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "待更新记录的ID",paramType = "path"),
            @ApiImplicitParam(name = "appointment",value = "待更新记录的实体类",paramType = "body")
    })
    public Tip updateAppointment(@PathVariable long id, @Valid @RequestBody Appointment appointment) {
        long userId = 1l;// JWTKit.getUserId();
        Customer customer = pathService.queryCustomerByUserId(userId);
        Appointment appointments = appointmentService.retrieveMaster(id);
        appointment.setId(id);
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
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据提供ID查询订单记录",response = Integer.class)
    @ApiParam(name = "id",value = "待查询的订单记录ID")
    public Tip showAppointmentModel(@PathVariable long id) {
        long userId = 1l;//JWTKit.getUserId();
        Customer customer = pathService.queryCustomerByUserId(userId);
        /*if (customer == null) {
            throw new BusinessException(BusinessCode.InvalidKey.getCode(), "customer not found.");
        }*/
        AppointmentModel result = pathService.appointmentDetails(id);
        if (result.getCustomerId() == customer.getId()) {//|| ShiroKit.hasPermission(AdminPermission.QUERY
            return SuccessTip.create(result);
        }
        throw new BusinessException(BusinessCode.NoPermission.getCode(), "no permission to show customer appointment!");

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据提供ID删除订单记录",response = Integer.class)
    @ApiParam(name = "id",value = "待删除的订单记录ID")
    public Tip deleteAppointment(@PathVariable long id) {
        long userId = 1l;//JWTKit.getUserId();
        Customer customer = pathService.queryCustomerByUserId(userId);
        /*if (customer == null) {
            throw new BusinessException(BusinessCode.InvalidKey.getCode(), "customer not found.");
        }*/
        Appointment appointment = appointmentService.retrieveMaster(id);
        if (appointment.getCustomerId() == customer.getId()) {//Per.hasPermission(AdminPermission.EDIT)
            Integer result = appointmentService.deleteMaster(id);
            return SuccessTip.create(result);
        } else {
            throw new BusinessException(BusinessCode.NoPermission.getCode(), "no permission to delete customer appointment!");
        }

    }
}
