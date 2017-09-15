package com.jfeat.am.module.booking.services.service.patch.impl;

import com.baomidou.mybatisplus.plugins.Page;


import com.jfeat.am.module.booking.services.domain.dao.AppointmentDao;
import com.jfeat.am.module.booking.services.domain.dao.StudioDao;
import com.jfeat.am.module.booking.services.persistence.model.Appointment;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.service.patch.PatchService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
@Service
public class PatchServiceImpl implements PatchService {
    @Resource
    AppointmentDao appointmentDao;
    @Resource
    StudioDao studioDao;
    /*
    *   查找订单
    * */
    public List<Appointment> queryAppointment(Page<Appointment> page,
                                              String status,
                                              long StudioId,
                                              Date createTime){
        return appointmentDao.queryAppointment(page, status, StudioId, createTime);
    }

    /*
    *   查找店铺
    * */

    public List<Studio> queryStudio(Page<Studio> page,
                                    String name){
        return studioDao.queryStudio(page,name);
    }
}
