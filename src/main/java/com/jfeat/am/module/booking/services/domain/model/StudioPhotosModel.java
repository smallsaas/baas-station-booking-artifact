package com.jfeat.am.module.booking.services.domain.model;

import com.jfeat.am.module.booking.services.persistence.model.StudiosPhotos;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */
public class StudioPhotosModel extends StudiosPhotos{
    private List<String> urls;


    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
