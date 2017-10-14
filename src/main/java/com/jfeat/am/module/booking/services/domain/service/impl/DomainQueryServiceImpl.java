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
import com.jfeat.am.module.booking.services.persistence.mapper.*;
import com.jfeat.am.module.booking.services.persistence.model.*;


import com.jfeat.am.module.booking.services.service.crud.CustomerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
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
    @Resource
    StudioServiceMapper studioServiceMapper;

    @Resource
    ProductsPhotosMapper productsPhotosMapper;

    /*
    *   queryStudioById
    * */
    public List<Map<String,Object>> queryStudioById(long id){
        return null;
    }
    /*
    *   queryAllStudio
    * */

   public List<Map<String,Object>> allStudio(Page<Studio> page
                                   ){
       return studioDao.allStudio(page);
   }
    /*
    *   queryStudioByName
    * */
    public List<Studio> queryStudioByName(Page<Studio> page,
                                   String name){
        return studioDao.queryStudioByName(page, name);
    }
    /*
    *   queryProductByAttribute
    * */
    public List<StudioProduct> queryStudioProduct(Page<StudioProduct> page,
                                                  long studioId,
                                                  String attribute) {
        return studioProductDao.queryProductByAttribute(page, attribute);
    }

    /*
    *   queryAppointmentByUserId
    * */
    public List<Appointment> queryAppointmentByCustomerId(Page<Appointment> page, long id) {
        List<Appointment> appointments = appointmentMapper.selectList(new EntityWrapper<Appointment>().eq("id", id));
        return appointments;
    }

    /*
    *   queryStudioByStick
    * */
    public List<Studio> queryStudioByStick(Page<Studio> page, String city) {
        return studioDao.queryStudioByStick(page, city);
    }

     /*
    *   查找city
    * */

    public List<Studio> queryCity() {
        return studioDao.queryCity();
    }


    /*
    *   查找订单
    * */
    public List<Appointment> queryAppointment(Page<Appointment> page,
                                              String status,
                                              Long studioId,
                                              Long phone) {
        return appointmentDao.queryAppointment(page, status, studioId, phone);
    }

    /*
    *   查找店铺 by ServiceType
    * */

    public List<Map<String, Object>> queryStudioByMultiple(Page<StudioModel> page,
                                                           String tname,
                                                           String name,
                                                           String city,
                                                           String stick,
                                                           BigDecimal latitude,
                                                           BigDecimal longitude) {
        return studioDao.queryStudioByMultiple(page, tname,name,city,stick, latitude, longitude);
    }

    /*
    *   查找店铺 by site
    * */

    public List<Map<String, Object>> queryStudioBySite(Page<Studio> page,
                                                       String city, BigDecimal latitude, BigDecimal longitude
    ) {
        return studioDao.queryStudioBySite(page, city, latitude, longitude);
    }

    /*
    *       信息
    * */
    public StudioModel showStudioModel(long id) {
        StudioModel model = studioDao.queryStudioById(id);
//        Map map = new HashMap<>();
        List<StudioProduct> products = studioProductMapper.selectList(new EntityWrapper<StudioProduct>().eq("studio_id", id));
        List<StudiosPhotos> photos = studiosPhotosMapper.selectList(new EntityWrapper<StudiosPhotos>().eq("studio_id", id));
//        map.put("products", products);
//        map.put("photos", photos);
//        map.put("studio",studio);
        model.setPhotos(photos);
        model.setProducts(products);
        return model;
    }

    public StudioProductModel showStudioProductModel(long studioId, long id) {
        StudioProduct studioProduct = studioProductMapper.selectById(id);
        JSONObject productObj = JSON.parseObject(JSON.toJSONString(studioProduct));
        List<ProductsPhotos> photos = productsPhotosMapper.selectList(new EntityWrapper<ProductsPhotos>().eq("product_id", id));
        productObj.put("photos", photos);
        StudioProductModel model = JSON.parseObject(productObj.toJSONString(), StudioProductModel.class);
        return model;
    }

    /*
    *   产品列表
    * */
    public List<StudioProduct> studioProductList(Page<StudioProduct> page) {
        return studioProductMapper.selectList(new EntityWrapper<StudioProduct>());


    }

    /*
    *       精选产品列表
    * */


    public List<StudioProduct> productStickList(Page<StudioProduct> page,String city) {
        return studioProductDao.productStickList(page,city);
    }

    /*
    *       queryProductByName
    * */

    public  List<StudioProduct> queryProductByName(Page<StudioProduct> page,
                                            String name){
        return studioProductDao.queryProductByName(page,name);
    }
}
