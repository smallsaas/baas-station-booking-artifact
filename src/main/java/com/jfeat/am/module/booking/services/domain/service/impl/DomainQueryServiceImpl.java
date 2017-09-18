package com.jfeat.am.module.booking.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;


import com.jfeat.am.module.booking.services.domain.dao.AppointmentDao;
import com.jfeat.am.module.booking.services.domain.dao.StudioDao;
import com.jfeat.am.module.booking.services.domain.model.StudioModel;
import com.jfeat.am.module.booking.services.persistence.mapper.StudioMapper;
import com.jfeat.am.module.booking.services.persistence.mapper.StudioProductMapper;
import com.jfeat.am.module.booking.services.persistence.model.Appointment;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.domain.service.DomainQueryService;

import com.jfeat.am.module.booking.services.persistence.model.StudioProduct;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
@Service
public class DomainQueryServiceImpl implements DomainQueryService {
    @Resource
    AppointmentDao appointmentDao;
    @Resource
    StudioDao studioDao;
    @Resource
    StudioMapper studioMapper;
    @Resource
    StudioProductMapper studioProductMapper;
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
    *   查找店铺 by ServiceType
    * */

    public List<Studio> queryStudioByServiceType(Page<Studio> page,
                                    String name){
        return studioDao.queryStudioByServiceType(page,name);
    }

    /*
    *   查找店铺 by site
    * */

    public List<Studio> queryStudioBySite(Page<Studio> page,
                                                 String site){
        return studioDao.queryStudioBySite(page,site);
    }
    /*
    *   query
    * */
    public StudioModel showStudioModel(long id){
        Studio studio = studioMapper.selectById(id);
        JSONObject studioObj  = JSON.parseObject(JSON.toJSONString(studio));
        List<StudioProduct> products = studioProductMapper.selectList(new EntityWrapper<StudioProduct>().eq("studio_id",id));
        studioObj.put("products",products);
        StudioModel model = JSON.parseObject(studioObj.toJSONString(),StudioModel.class);
        return model;
    }
}
