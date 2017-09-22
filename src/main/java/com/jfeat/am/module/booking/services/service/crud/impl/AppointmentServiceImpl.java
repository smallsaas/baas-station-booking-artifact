package com.jfeat.am.module.booking.services.service.crud.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.booking.services.service.crud.AppointmentService;
import com.jfeat.am.module.booking.services.persistence.mapper.AppointmentMapper;
import com.jfeat.am.module.booking.services.persistence.model.Appointment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/14.
 */
@Service
public class AppointmentServiceImpl extends CRUDServiceOnlyImpl<Appointment> implements AppointmentService {
    @Resource
    AppointmentMapper appointmentMapper;

    @Override
    protected BaseMapper<Appointment> getMasterMapper() {
        return appointmentMapper;
    }
}
