package com.jfeat.am.module.booking.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;

import com.jfeat.am.common.exception.BizExceptionEnum;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.common.persistence.model.WechatConfig;
import com.jfeat.am.core.jwt.JWTKit;

import com.jfeat.am.core.shiro.ShiroKit;
import com.jfeat.am.modular.wechat.service.WechatConfigService;
import com.jfeat.am.modular.wechat.service.WechatPushOrderService;
import com.jfeat.am.module.booking.services.domain.definition.AdminPermission;
import com.jfeat.am.module.booking.services.domain.definition.AppointmentStatus;
import com.jfeat.am.module.booking.services.domain.service.DomainQueryService;
import com.jfeat.am.module.booking.services.persistence.model.Customer;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.service.crud.AppointmentService;
import com.jfeat.am.module.booking.services.persistence.model.Appointment;

import com.jfeat.am.module.booking.services.service.crud.StudioOverProductService;
import com.jfeat.am.module.booking.services.service.path.PathService;
import com.jfinal.weixin.sdk.kit.IpKit;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/15.
 */
@RestController
@RequestMapping("/api/appointments")
public class AppointmentEndpoint extends BaseController{

    @Resource
    AppointmentService appointmentService;
    @Resource
    DomainQueryService domainQueryService;
    @Resource
    StudioOverProductService studioService;
    @Resource
    PathService pathService;
    @Resource
    WechatPushOrderService wechatPushOrderService;
    @Resource
    WechatConfigService wechatConfigService;

    /*
    *   fuzzy query
    * */

    @GetMapping("/lists")
    @Permission(AdminPermission.QUERY)
    public Tip queryAppointment(Page page,
                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                @RequestParam(name = "status",required = false) String status,
                                @RequestParam(name = "studioId",required = false)Long studioId,
                                @RequestParam(name = "phone",required = false)Long phone
                                ) {
            page.setSize(pageSize);
            page.setCurrent(pageNum);
            List<Appointment> appointments = domainQueryService.queryAppointment(page, status, studioId,phone);
            page.setRecords(appointments);
            return SuccessTip.create(page);

    }
    /*
    *   queryUserAppointment
    * */
    @GetMapping("/users")
    public Tip queryUserAppointment(Page page,
                                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
                                    ){
        long userId = JWTKit.getUserId(getHttpServletRequest());
        Customer customer = pathService.queryCustomerByUserId(userId);
        if (customer == null) {
            throw new BusinessException(BizExceptionEnum.SERVER_ERROR.getCode(),"customer not found.");
        }
        page.setSize(pageSize);
        page.setCurrent(pageNum);

        List<Appointment> appointments = domainQueryService.queryAppointmentByCustomerId(page,customer.getId());
        page.setRecords(appointments);
        return SuccessTip.create(page);

    }
    /*
   *   CRUD about Appointment
   * */
    @PostMapping
    public Tip createAppointment(@Valid @RequestBody Appointment appointment){
        Long userId = JWTKit.getUserId(getHttpServletRequest());
        Customer customer = pathService.queryCustomerByUserId(userId);
        if (customer == null) {
            throw new BusinessException(BizExceptionEnum.SERVER_ERROR.getCode(),"customer not found.");
        }
        Studio studio = studioService.retrieveMaster(appointment.getStudioId());
        if (studio == null) {
            throw new BusinessException(BizExceptionEnum.SERVER_ERROR.getCode(),"studio not found.");
        }
        appointment.setCustomerName(customer.getName());
        appointment.setCustomerId(customer.getId());
        appointment.setDoctorId(0L);
        appointment.setStatus(AppointmentStatus.PAY_PENDING.toString());
        appointment.setFee(studio.getFee());
        appointment.setCreateTime(new Date());
        Integer result = appointmentService.createMaster(appointment);
        //pushOrder(String title,String detail,String orderNum,String totalFee,Long tenantId,String openid,String ip,String notifyUrl)
        Long tenantId = JWTKit.getTenantId(getHttpServletRequest());
        WechatConfig wechatConfig = wechatConfigService.getByTenantId(tenantId);
        Map map = wechatPushOrderService.pushOrder(studio.getName(),
                studio.getName(),
                appointment.getId().toString(),
                appointment.getFee().multiply(BigDecimal.valueOf(100)).intValue() + "",
                tenantId,
                customer.getOpenid(),
                IpKit.getRealIp(getHttpServletRequest()),
                wechatConfig.getHost() + "/api/pub/wpay/notify/" + wechatConfig.getAppId(),
                true);
        logger.debug("push order result: {}", map);
        return SuccessTip.create(map);
    }

    @PutMapping
    public Tip updateAppointment(@Valid@RequestBody Appointment appointment){
        long userId = JWTKit.getUserId(getHttpServletRequest());
        Customer customer = pathService.queryCustomerByUserId(userId);
        if((appointment.getCustomerId() == customer.getId() && appointment.getStatus() != AppointmentStatus.DONE.toString())
                || ShiroKit.hasPermission(AdminPermission.EDIT)) {
            return SuccessTip.create(appointmentService.updateMaster(appointment));
        }else {
            throw new BusinessException(BizExceptionEnum.OUT_OF_RANGE.getCode(),"no permission");
        }
    }
    @GetMapping("/{id}")
    public Tip showAppointment(@PathVariable long id){
        long userId = JWTKit.getUserId(getHttpServletRequest());
        Customer customer = pathService.queryCustomerByUserId(userId);
        if (customer == null) {
            throw new BusinessException(BizExceptionEnum.SERVER_ERROR.getCode(), "customer not found.");
        }
        Appointment result = appointmentService.retrieveMaster(id);
        if (result.getCustomerId() == customer.getId() || ShiroKit.hasPermission(AdminPermission.QUERY)){
            return SuccessTip.create(result);
        }
        throw new  BusinessException(BizExceptionEnum.NO_PERMISSION.getCode(),"no permission to show customer appointment!");

    }
    @DeleteMapping("/{id}")
    public Tip deleteAppointment(@PathVariable long id){
        long userId = JWTKit.getUserId(getHttpServletRequest());
        Customer customer = pathService.queryCustomerByUserId(userId);
        if (customer == null) {
            throw new BusinessException(BizExceptionEnum.SERVER_ERROR.getCode(),"customer not found.");
        }
        Appointment appointment = appointmentService.retrieveMaster(id);
        if (appointment.getCustomerId() == customer.getId() || ShiroKit.hasPermission(AdminPermission.EDIT)){
            Integer result = appointmentService.deleteMaster(id);
            return SuccessTip.create(result);
        }else{
            throw new BusinessException(BizExceptionEnum.NO_PERMISSION.getCode(),"no permission to delete customer appointment!");
        }

    }
}
