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
    public Studio retrieveMaster(long id) {
        return studioMapper.selectById(id);
    }

    @Override
    public Integer deleteMaster(long id) {
        return studioMapper.deleteById(id);
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
    public Integer addSlaveItem(long id, Doctor doctor) {
        return doctorMapper.insert(doctor);
    }

    @Override
    public Integer updateSlaveItem(long id, Doctor doctor) {
        return doctorMapper.updateById(doctor);
    }

    @Override
    public Integer removeSlaveItem(long masterId, long id) {
        return doctorMapper.deleteById(id);
    }

    @Override
    public Doctor getSlaveItem(long masterId, long id) {
        return doctorMapper.selectById(id);
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
