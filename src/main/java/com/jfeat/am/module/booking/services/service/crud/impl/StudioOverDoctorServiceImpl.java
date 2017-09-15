package com.jfeat.am.module.booking.services.service.crud.impl;

import com.jfeat.am.common.crud.CRUDFilter;
import com.jfeat.am.common.crud.CRUDObject;
import com.jfeat.am.common.crud.CRUDServiceOverSlave;
import com.jfeat.am.module.booking.services.domain.model.StudioModel;
import com.jfeat.am.module.booking.services.persistence.mapper.DoctorMapper;
import com.jfeat.am.module.booking.services.persistence.mapper.StudioMapper;
import com.jfeat.am.module.booking.services.persistence.model.Doctor;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.service.crud.StudioOverDoctorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by J4cob on 2017/9/14.
 */
@Service
public class StudioOverDoctorServiceImpl implements StudioOverDoctorService, CRUDServiceOverSlave<Studio, StudioModel, Doctor> {

    @Resource
    StudioMapper studioMapper;
    @Resource
    DoctorMapper doctorMapper;


    @Override
    public void appendSlaveData(StudioModel studioModel) {

    }

    @Override
    public void handleSlaveData(StudioModel studioModel) {

    }

    @Override
    public CRUDObject<StudioModel> retrieveMaster(long l, CRUDFilter<Studio> crudFilter, Class<StudioModel> aClass) {
        return null;
    }

    @Override
    public Integer updateMaster(StudioModel studioModel, boolean b, CRUDFilter<Studio> crudFilter) {
        return null;
    }

    @Override
    public Integer createMaster(Studio studio) {
        return studioMapper.insert(studio);
    }

    @Override
    public Integer createMaster(Studio studio, CRUDFilter<Studio> crudFilter) {
        return null;
    }

    @Override
    public Integer updateMaster(Studio studio) {
        return studioMapper.updateById(studio);
    }

    @Override
    public Integer updateMaster(Studio studio, CRUDFilter<Studio> crudFilter) {
        return null;
    }

    @Override
    public Studio retrieveMaster(long l) {
        return studioMapper.selectById(l);
    }

    @Override
    public Integer deleteMaster(long l) {
        return studioMapper.deleteById(l);
    }

    @Override
    public List<Studio> queryMasterList(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<Studio> selectMasterList(String s, Object o) {
        return null;
    }

    @Override
    public Integer bulkDeleteMasterList(List<Long> list) {
        return null;
    }

    @Override
    public Integer addSlaveItem(long l, Doctor doctor) {
        return doctorMapper.insert(doctor);
    }

    @Override
    public Integer updateSlaveItem(long l, Doctor doctor) {
        return doctorMapper.updateById(doctor);
    }

    @Override
    public Integer removeSlaveItem(long l, long l1) {
        return doctorMapper.deleteById(l1);
    }

    @Override
    public Doctor getSlaveItem(long l, long l1) {
        return doctorMapper.selectById(l1);
    }

    @Override
    public List<Doctor> selectSlaveItemList(long l) {
        return null;
    }

    @Override
    public Integer bulkRemoveSlaveItemList(long l, List<Long> list) {
        return null;
    }
}
