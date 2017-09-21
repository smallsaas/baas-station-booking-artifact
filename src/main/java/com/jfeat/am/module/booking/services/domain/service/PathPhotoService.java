package com.jfeat.am.module.booking.services.domain.service;

import com.jfeat.am.module.booking.services.persistence.model.ProductsPhotos;
import com.jfeat.am.module.booking.services.persistence.model.StudiosPhotos;
import java.util.List;

/**
 * Created by Administrator on 2017/9/21.
 */
public interface PathPhotoService {

    public Integer addStudioPhotos(long studioId, StudiosPhotos studiosPhotos);
    public Integer deleteStudioPhotos(long studioId, long id);
    public Integer bulkDeleteStudioPhotos(long studioId, List<Long> ids);
    public Integer addProductPhotos(long studioId, long productId, ProductsPhotos productsPhotos);
    public Integer deleteProductPhotos(long studioId, long productId, long id);
    public Integer bulkDeleteProductPhotos(long studioId, long productId, List<Long> ids);


}
