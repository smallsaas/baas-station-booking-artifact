package com.jfeat.am.module.booking.services.service.path.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.module.booking.services.domain.dao.CustomerDao;
import com.jfeat.am.module.booking.services.domain.definition.ServiceCode;
import com.jfeat.am.module.booking.services.domain.model.CustomerModel;
import com.jfeat.am.module.booking.services.persistence.mapper.*;
import com.jfeat.am.module.booking.services.persistence.model.*;
import com.jfeat.am.module.booking.services.service.path.PathService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public CustomerModel getMoreInfo(long userId) {
        Customer customer = customerDao.queryCustomerByUserId(userId);
        JSONObject customerObj = JSON.parseObject(JSON.toJSONString(customer));
        List<StudioCollect> favors = studioCollectMapper.selectList(new EntityWrapper<StudioCollect>().eq("customer_id", customer.getId()));
        List<Studio> studioList = new ArrayList<>();
        for(StudioCollect studioCollect:favors){
            Studio studios = studioMapper.selectById(studioCollect.getStudioId());
            studioList.add(studios);
        }
        customerObj.put("studios",studioList);
        CustomerModel model = JSON.parseObject(customerObj.toJSONString(), CustomerModel.class);
        return model;
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
}