package com.jfeat.am.module.booking.services.service.crud.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.booking.services.persistence.mapper.DoctorMapper;
import com.jfeat.am.module.booking.services.persistence.model.Doctor;

import javax.annotation.Resource;
import javax.xml.ws.ServiceMode;

/**
 * Created by Administrator on 2017/9/14.
 */

@ServiceMode
public class DoctorServiceImpl extends CRUDServiceOnlyImpl<Doctor> {
    @Resource
    DoctorMapper doctorMapper;

    @Override
    protected BaseMapper<Doctor> getMasterMapper() {
        return doctorMapper;
    }
}
