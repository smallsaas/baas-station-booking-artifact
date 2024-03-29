package com.jfeat.am.module.booking.services.service.path.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.booking.services.domain.dao.CollectDao;
import com.jfeat.am.module.booking.services.domain.dao.CustomerDao;
import com.jfeat.am.module.booking.services.domain.definition.ServiceCode;
import com.jfeat.am.module.booking.services.domain.model.AppointmentModel;
import com.jfeat.am.module.booking.services.domain.model.StudioCollectModel;
import com.jfeat.am.module.booking.services.persistence.mapper.*;
import com.jfeat.am.module.booking.services.persistence.model.*;
import com.jfeat.am.module.booking.services.service.path.PathService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/9.
 */
@Service
public class PathServiceImpl implements PathService {
    @Resource
    StudioServiceMapper studioServiceMapper;
    @Resource
    StudiosPhotosMapper studiosPhotosMapper;
    @Resource
    ProductsPhotosMapper productsPhotosMapper;
    @Resource
    StudioCollectMapper studioCollectMapper;
    @Resource
    CustomerMapper customerMapper;
    @Resource
    CustomerDao customerDao;
    @Resource
    ServiceTypeMapper typeMapper;
    @Resource
    StudioMapper studioMapper;
    @Resource
    AppointmentMapper appointmentMapper;
    @Resource
    CollectDao collectDao;

    public Integer addStudioService(Long studioId, List<Long> ids) {
        List<StudioService> studioServices = studioServiceMapper.selectList(new EntityWrapper<StudioService>().eq("studio_id", studioId));
        if (studioServices != null || studioServices.size() != 0) {
            studioServiceMapper.delete(new EntityWrapper<StudioService>().eq("studio_id", studioId));
        }
        for (Long id : ids) {
            StudioService studioService = new StudioService();
            studioService.setStudioId(studioId);
            studioService.setTypeId(id);
            studioServiceMapper.insert(studioService);
        }
        return ids.size();
    }

    public Integer addStudioPhotos(Long studioId, List<String> urls) {
        List<StudiosPhotos> photos = studiosPhotosMapper.selectList(new EntityWrapper<StudiosPhotos>().eq("studio_id", studioId));
        if (photos != null || photos.size() != 0) {
            studiosPhotosMapper.delete(new EntityWrapper<StudiosPhotos>().eq("studio_id", studioId));
        }
        for (String url : urls) {
            StudiosPhotos studiosPhotos = new StudiosPhotos();
            studiosPhotos.setStudioId(studioId);
            studiosPhotos.setPhoto(url);
            studiosPhotosMapper.insert(studiosPhotos);
        }
        return urls.size();
    }

    /*
*   add ProductPhotos
* */
    public Integer addProductPhotos(Long productId, List<String> urls) {
        List<ProductsPhotos> photos = productsPhotosMapper.selectList(new EntityWrapper<ProductsPhotos>().eq("product_id", productId));
        if (photos != null || photos.size() != 0) {
            productsPhotosMapper.delete(new EntityWrapper<ProductsPhotos>().eq("product_id", productId));
        }
        for (String url : urls) {
            ProductsPhotos productsPhotos = new ProductsPhotos();
            productsPhotos.setProductId(productId);
            productsPhotos.setPhoto(url);
            productsPhotosMapper.insert(productsPhotos);
        }

        return urls.size();
    }

    /*
    *   get user Info
    * */
   /* public CustomerModel getMoreInfo(long userId) {
        Customer customer = customerDao.queryCustomerByUserId(userId);
        List<StudioCollect> collects =  studioCollectMapper.selectList(new EntityWrapper<StudioCollect>().eq("customer_id", customer.getId()));
        List<CustomerModel> models = new ArrayList<>();
        for(StudioCollect collect:collects){
            Studio studio = studioMapper.selectById(collect.getStudioId());
            *//*models.set*//*

        }


       *//* JSONObject customerObj = JSON.parseObject(JSON.toJSONString(customer));
        List<StudioCollect> favors = studioCollectMapper.selectList(new EntityWrapper<StudioCollect>().eq("customer_id", customer.getId()));
        for(StudioCollect studioCollect:favors){
            Studio studios = studioMapper.selectById(studioCollect.getStudioId());
            customerObj.put("studios",studios);
        }
        CustomerModel model = JSON.parseObject(customerObj.toJSONString(), CustomerModel.class);*//*
        return model;
    }*/

    public List<StudioCollectModel> queryUserCollects(Page<StudioCollectModel> page,
                                                           long customerId){
        /*List<Appointment> appointments = appointmentDao.queryAppointmentByStatus(page,status);*/
        List<StudioCollectModel> collectModels =  collectDao.queryUserCollects(page,customerId);
        for(StudioCollectModel model:collectModels){
            Studio studio = studioMapper.selectById(model.getStudioId());
            model.setStudio(studio);
        }
        return collectModels;
    }

    /*
    *   queryCustomerByUserId
    * */
    public Customer queryCustomerByUserId(long userId) {
        return customerDao.queryCustomerByUserId(userId);
    }

    public Integer deleteTypes(long id) {
        List<StudioService> studioServices = studioServiceMapper.selectList(new EntityWrapper<StudioService>().eq("type_id", id));
        if (studioServices == null || studioServices.size() == 0) {
            return typeMapper.deleteById(id);

        }else {
            return ServiceCode.NOT_ALLOW_TO_DELETE.getCode();

        }
    }


    public Integer addOrCancelFavors(StudioCollect studioCollect) {
        List<StudioCollect> collects = studioCollectMapper.selectList(
                new EntityWrapper<StudioCollect>().eq("customer_id", studioCollect.getCustomerId())
                .eq("studio_id", studioCollect.getStudioId()));
        if (collects == null || collects.size() == 0) {
            return studioCollectMapper.insert(studioCollect);
        } else {
            Map<String,Object> map = new HashMap<>();
            map.put("studio_id",studioCollect.getStudioId());
            map.put("customer_id",studioCollect.getCustomerId());
            return studioCollectMapper.deleteByMap(map);
        }
    }
    /*
    *  通过店铺ID跟customerID查找是否收藏了店铺
    * */
    public Integer queryStudioCollect(long studioId , long customerId){
        /*Map<String,Object> map = new HashMap<>();
        map.put("studio_id",studioId);
        map.put("customer_id",customerId);
        return studioCollectMapper.selectByMap(map);*/

        return studioCollectMapper.selectCount(new EntityWrapper<StudioCollect>().eq("studio_id",studioId)
                .eq("customer_id",customerId));
    }

    /*
    *   get Users appointment
    * */
    public AppointmentModel appointmentDetails(long id){
        Appointment appointment = appointmentMapper.selectById(id);
        JSONObject appointObj = JSON.parseObject(JSON.toJSONString(appointment));
        Studio studio = studioMapper.selectById(appointment.getStudioId());
        appointObj.put("studio",studio);
        AppointmentModel model = JSON.parseObject(appointObj.toJSONString(),AppointmentModel.class);
        return model;
    }
}