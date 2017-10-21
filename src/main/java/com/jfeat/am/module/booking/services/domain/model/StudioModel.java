package com.jfeat.am.module.booking.services.domain.model;

import com.jfeat.am.module.booking.services.persistence.model.*;

import java.util.List;

/**
 * Created by J4cob on 2017/9/14.
 */
public class StudioModel extends Studio {
//    List<StudioService> services;
    List<StudioProduct> products;
    List<Doctor> doctors;
    List<StudiosPhotos> photos;
    StudioCollect collect;
    long collected;
    long sales;

    public long getSales() {
        return sales;
    }

    public void setSales(long sales) {
        this.sales = sales;
    }

    public long getCollected() {
        return collected;
    }

    public void setCollected(long collected) {
        this.collected = collected;
    }

    public List<ServiceType> getTypes() {
        return types;
    }

    public void setTypes(List<ServiceType> types) {
        this.types = types;
    }

    List<ServiceType> types;

    public List<StudiosPhotos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<StudiosPhotos> photos) {
        this.photos = photos;
    }


    public List<StudioProduct> getProducts() {
        return products;
    }

    public void setProducts(List<StudioProduct> products) {
        this.products = products;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public StudioCollect getCollect() {
        return collect;
    }

    public void setCollect(StudioCollect collect) {
        this.collect = collect;
    }
    //    public List<StudioService> getServices() {
//        return services;
//    }
//
//    public void setServices(List<StudioService> services) {
//        this.services = services;
//    }
}
