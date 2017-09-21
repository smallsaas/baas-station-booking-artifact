package com.jfeat.am.module.booking.services.domain.model;

import com.jfeat.am.module.booking.services.persistence.model.ProductsPhotos;
import com.jfeat.am.module.booking.services.persistence.model.StudioProduct;
import java.util.List;

/**
 * Created by Administrator on 2017/9/21.
 */
public class StudioProductModel extends StudioProduct{
    List<ProductsPhotos> photos;

    public List<ProductsPhotos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<ProductsPhotos> photos) {
        this.photos = photos;
    }
}
