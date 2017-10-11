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
    StudioServiceMapper serviceMapper;

    @Resource
    ProductsPhotosMapper productsPhotosMapper;
    /*
    *   queryAllStudio
    * */

   public List<Studio> allStudio(Page<Studio> page
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
    public List<Appointment> queryAppointmentByUserId(Page<Appointment> page, Long userId) {
        List<Appointment> appointments = appointmentMapper.selectList(new EntityWrapper<Appointment>().eq("customer_id", userId));
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
                                                           BigDecimal latitude,
                                                           BigDecimal longitude) {
        return studioDao.queryStudioByMultiple(page, tname, name, latitude, longitude);
    }

    /*
    *   查找店铺 by site
    * */

    public List<Map<String, Object>> queryStudioBySite(Page<Studio> page,
                                                       String site, BigDecimal latitude, BigDecimal longitude
    ) {
        return studioDao.queryStudioBySite(page, site, latitude, longitude);
    }

    /*
    *       信息
    * */
    public StudioModel showStudioModel(long id) {
        Studio studio = studioMapper.selectById(id);
        JSONObject studioObj = JSON.parseObject(JSON.toJSONString(studio));
        List<StudioProduct> products = studioProductMapper.selectList(new EntityWrapper<StudioProduct>().eq("studio_id", id));
        List<StudiosPhotos> photos = studiosPhotosMapper.selectList(new EntityWrapper<StudiosPhotos>().eq("studio_id", id));
        List<com.jfeat.am.module.booking.services.persistence.model.StudioService> services =
                serviceMapper.selectList(new EntityWrapper<com.jfeat.am.module.booking.services.persistence.model.StudioService>().eq("studio_id", id));
        studioObj.put("services", services);
        studioObj.put("products", products);
        studioObj.put("photos", photos);
        StudioModel model = JSON.parseObject(studioObj.toJSONString(), StudioModel.class);
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


    public List<StudioProduct> productStickList(Page<StudioProduct> page) {
        return studioProductDao.productStickList(page);
    }

    /*
    *       queryProductByName
    * */

    public  List<StudioProduct> queryProductByName(Page<StudioProduct> page,
                                            String name){
        return studioProductDao.queryProductByName(page,name);
    }
}
