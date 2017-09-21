package com.jfeat.am.module.booking.services.domain.model;

import com.jfeat.am.module.booking.services.persistence.model.Doctor;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.persistence.model.StudioProduct;
import com.jfeat.am.module.booking.services.persistence.model.StudiosPhotos;

import java.util.List;

/**
 * Created by J4cob on 2017/9/14.
 */
public class StudioModel extends Studio {
    List<StudioProduct> products;
    List<Doctor> doctors;
    List<StudiosPhotos> photos;


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

}
