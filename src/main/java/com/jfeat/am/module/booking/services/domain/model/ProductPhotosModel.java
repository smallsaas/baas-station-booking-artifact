package com.jfeat.am.module.booking.services.domain.model;

import com.jfeat.am.module.booking.services.persistence.model.ProductsPhotos;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */
public class ProductPhotosModel extends ProductsPhotos{
    private List<String> urls;


    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
