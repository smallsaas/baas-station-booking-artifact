package com.jfeat.am.module.booking.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;

import com.jfeat.am.core.jwt.JWTKit;

import com.jfeat.am.core.shiro.ShiroKit;
import com.jfeat.am.module.booking.services.domain.definition.AdminPermission;
import com.jfeat.am.module.booking.services.domain.definition.AppointmentStatus;
import com.jfeat.am.module.booking.services.domain.service.DomainQueryService;
import com.jfeat.am.module.booking.services.service.crud.AppointmentService;
import com.jfeat.am.module.booking.services.persistence.model.Appointment;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

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

    /*
    *   fuzzy query
    * */

    @GetMapping("/lists")
    public Tip queryAppointment(Page page,
                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                @RequestParam(name = "status",required = false) String status,
                                @RequestParam(name = "studioId",required = false)Long studioId
                                ) {
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        if (ShiroKit.hasPermission(AdminPermission.QUERY)) {
            List<Appointment> appointments = domainQueryService.queryAppointment(page, status, studioId);
            page.setRecords(appointments);
            return SuccessTip.create(page);
        }else{
            long userId = JWTKit.getUserId(getHttpServletRequest());
            List<Appointment> appointments = domainQueryService.queryAppointmentByUserId(userId);
            page.setRecords(appointments);
            return SuccessTip.create(page);
        }
    }    /*
   *   CRUD about Appointment
   * */
    @PostMapping
    public Tip createAppointment(@Valid @RequestBody Appointment appointment){
        Long userId = JWTKit.getUserId(getHttpServletRequest());
        appointment.setCustomerId(userId);
        appointment.setDoctorId(0L);
        appointment.setStatus(AppointmentStatus.TO_BE_COMFIRMED.toString());
        appointment.setCreateTime(new Date());
        Integer result = appointmentService.createMaster(appointment);
        return SuccessTip.create(result);
    }
    @PutMapping
    public Tip updateAppointment(@Valid@RequestBody Appointment appointment){
        long userId = JWTKit.getUserId(getHttpServletRequest());
        if(ShiroKit.hasPermission(AdminPermission.EDIT)){
        Integer result = appointmentService.updateMaster(appointment);
        return SuccessTip.create(result);
        }else if(appointment.getId() == userId && appointment.getStatus() != AppointmentStatus.DONE.toString()){ //未完成的并且有当前用户发起的订单
            Integer result = appointmentService.updateMaster(appointment);
            return SuccessTip.create(result);
        }
        return null;
    }
    @GetMapping("/{id}")
    public Tip showAppointment(@PathVariable long id){
        Appointment result = appointmentService.retrieveMaster(id);
        return SuccessTip.create(result);
    }
    @DeleteMapping("/{id}")
    public Tip deleteAppointment(@PathVariable long id){
        Appointment appointment = appointmentService.retrieveMaster(id);
        Integer result = appointmentService.deleteMaster(id);
        return SuccessTip.create(result);
    }
}
