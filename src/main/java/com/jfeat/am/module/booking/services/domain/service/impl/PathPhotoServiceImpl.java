package com.jfeat.am.module.booking.services.domain.service.impl;

import com.jfeat.am.module.booking.services.domain.service.PathPhotoService;
import com.jfeat.am.module.booking.services.persistence.mapper.ProductsPhotosMapper;
import com.jfeat.am.module.booking.services.persistence.mapper.StudiosPhotosMapper;
import com.jfeat.am.module.booking.services.persistence.model.ProductsPhotos;
import com.jfeat.am.module.booking.services.persistence.model.StudiosPhotos;


import org.springframework.stereotype.Service;

import java.util.List;
import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/21.
 */
@Service
public class PathPhotoServiceImpl implements PathPhotoService {

    @Resource
    ProductsPhotosMapper productsPhotosMapper;
    @Resource
    StudiosPhotosMapper studiosPhotosMapper;

    /*
    *   StudioPhotos  add  n delete
    * */
    public Integer addStudioPhotos(long studioId, StudiosPhotos studiosPhotos) {
        studiosPhotos.setStudioId(studioId);
        return studiosPhotosMapper.insert(studiosPhotos);
    }

    public Integer deleteStudioPhotos(long studioId, long id) {
        return studiosPhotosMapper.deleteById(id);
    }

    public Integer bulkDeleteStudioPhotos(long studioId, List<Long> ids) {
        return studiosPhotosMapper.deleteBatchIds(ids);
    }

    /*
    *   ProductPhotos add n delete
    * */
    public Integer addProductPhotos(long studioId, long productId, ProductsPhotos productsPhotos) {
        productsPhotos.setProductId(productId);
        return productsPhotosMapper.insert(productsPhotos);
    }

    public Integer deleteProductPhotos(long studioId, long productId, long id) {
        return productsPhotosMapper.deleteById(id);
    }

    public Integer bulkDeleteProductPhotos(long studioId, long productId, List<Long> ids) {
        return productsPhotosMapper.deleteBatchIds(ids);
    }

}