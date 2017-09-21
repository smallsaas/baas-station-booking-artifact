package com.jfeat.am.module.booking.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;

import com.jfeat.am.core.jwt.JWTKit;

import com.jfeat.am.core.shiro.ShiroKit;
import com.jfeat.am.module.booking.api.bean.Ids;
import com.jfeat.am.module.booking.domain.definition.AdminPermission;
import com.jfeat.am.module.booking.domain.definition.AppointmentStatus;
import com.jfeat.am.module.booking.domain.service.DomainQueryService;
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
                                @RequestParam(name = "studioId",required = false)Long studioId,
                                @RequestParam(name = "createTime",required = false)Date createTime) {
        long userId = JWTKit.getUserId(getHttpServletRequest());
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        List<Appointment> appointments = domainQueryService.queryAppointment(page, status, studioId, createTime);
        if (ShiroKit.hasPermission(AdminPermission.QUERY)) {
            page.setRecords(appointments);
            return SuccessTip.create(page);
        }
        return SuccessTip.create();
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
        Integer result = appointmentService.updateMaster(appointment);
        return SuccessTip.create(result);
    }
    @GetMapping("/{id}")
    public Tip showAppointment(@PathVariable long id){
        Appointment result = appointmentService.retrieveMaster(id);
        return SuccessTip.create(result);
    }
    @DeleteMapping("/{id}")
    public Tip deleteAppointment(@PathVariable long id){
        Integer result = appointmentService.deleteMaster(id);
        return SuccessTip.create(result);
    }
}
