package com.jfeat.am.module.booking.services.service.crud.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceModelImpl;
import com.jfeat.am.module.booking.services.domain.model.StudioModel;
import com.jfeat.am.module.booking.services.persistence.mapper.DoctorMapper;
import com.jfeat.am.module.booking.services.persistence.mapper.StudioMapper;
import com.jfeat.am.module.booking.services.persistence.model.Doctor;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.service.crud.StudioModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/14.
 */
@Service
public class StudioModelServiceImpl extends CRUDServiceModelImpl<Studio,Doctor> implements StudioModelService{
    @Resource
    StudioMapper studioMapper;
    @Resource
    DoctorMapper doctorMapper;

    @Override
    public void appendSlaveData(Doctor doctor) {

    }

    @Override
    public void handleSlaveData(Doctor doctor) {

    }

    @Override
    protected BaseMapper<Studio> getMasterMapper() {
        return null;
    }
}
