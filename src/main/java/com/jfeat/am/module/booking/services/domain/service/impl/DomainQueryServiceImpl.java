package com.jfeat.am.module.booking.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;


import com.jfeat.am.module.booking.services.domain.dao.AppointmentDao;
import com.jfeat.am.module.booking.services.domain.dao.StudioDao;
import com.jfeat.am.module.booking.services.domain.dao.StudioProductDao;
import com.jfeat.am.module.booking.services.domain.model.StudioModel;
import com.jfeat.am.module.booking.services.domain.model.StudioProductModel;
import com.jfeat.am.module.booking.services.domain.service.DomainQueryService;
import com.jfeat.am.module.booking.services.persistence.mapper.AppointmentMapper;
import com.jfeat.am.module.booking.services.persistence.mapper.StudioMapper;
import com.jfeat.am.module.booking.services.persistence.mapper.StudioProductMapper;
import com.jfeat.am.module.booking.services.persistence.mapper.StudiosPhotosMapper;
import com.jfeat.am.module.booking.services.persistence.model.*;


import com.jfeat.am.module.booking.services.service.crud.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    @Resource
    StudiosPhotosMapper studiosPhotosMapper;
    @Resource
    CustomerService customerService;
    @Resource
    AppointmentMapper appointmentMapper;
    @Resource
    StudioProductDao studioProductDao;
    /*
    *   queryProductByAttribute
    * */
    public List<StudioProduct> queryStudioProduct(Page<StudioProduct> page,
                                                  long studioId,
                                              String attribute) {
        return studioProductDao.queryProductByAttribute(page,attribute);
    }

    /*
    *   queryAppointmentByUserId
    * */
    public List<Appointment> queryAppointmentByUserId(long id){
        List<Appointment> appointments = appointmentMapper.selectList(new EntityWrapper<Appointment>().eq("customer_id",id));
        return appointments;
    }

    /*
    *   queryStudioByStick
    * */
    public List<Studio> queryStudioByStick(Page<Studio> page,String city){
        return studioDao.queryStudioByStick(page, city);
    }

     /*
    *   查找city
    * */

    public List<Studio> queryCity(){
        return studioDao.queryCity();
    }


    /*
    *   查找订单
    * */
    public List<Appointment> queryAppointment(Page<Appointment> page,
                                              String status,
                                              long StudioId) {
        return appointmentDao.queryAppointment(page, status, StudioId);
    }

    /*
    *   查找店铺 by ServiceType
    * */

    public List<StudioModel> queryStudioByMultiple(Page<StudioModel> page,
                                                   String tname,
                                                   String name) {
        return studioDao.queryStudioByMultiple(page, tname,name);
    }

    /*
    *   查找店铺 by site
    * */

    public List<Map<String,Object>> queryStudioBySite(Page<Studio> page,
                                                      String site, BigDecimal latitude, BigDecimal longitude
                                          ) {
        return studioDao.queryStudioBySite(page, site,latitude,longitude);
    }

    /*
    *       信息
    * */
    public StudioModel showStudioModel(long id) {
        Studio studio = studioMapper.selectById(id);
        JSONObject studioObj = JSON.parseObject(JSON.toJSONString(studio));
        List<StudioProduct> products = studioProductMapper.selectList(new EntityWrapper<StudioProduct>().eq("studio_id", id));
        List<StudiosPhotos> photos = studiosPhotosMapper.selectList(new EntityWrapper<StudiosPhotos>().eq("studio_id", id));
        studioObj.put("products", products);
        studioObj.put("photos", photos);
        StudioModel model = JSON.parseObject(studioObj.toJSONString(), StudioModel.class);
        return model;
    }

    public StudioProductModel showStudioProductModel(long studioId,long id) {
        StudioProduct studioProduct = studioProductMapper.selectById(id);
        JSONObject productObj = JSON.parseObject(JSON.toJSONString(studioProduct));
        List<StudioProduct> photos = studioProductMapper.selectList(new EntityWrapper<StudioProduct>().eq("product_id", id));
        productObj.put("photos", photos);
        StudioProductModel model = JSON.parseObject(productObj.toJSONString(), StudioProductModel.class);
        return model;
    }
    /*
    *   产品列表
    * */
    public  List<StudioProduct> studioProductList(){
        return studioProductMapper.selectList(new EntityWrapper<StudioProduct>());
    }
}
