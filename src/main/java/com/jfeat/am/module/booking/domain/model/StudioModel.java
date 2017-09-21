package com.jfeat.am.module.booking.domain.model;

import com.jfeat.am.module.booking.services.persistence.model.Doctor;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.persistence.model.StudioProduct;

import java.util.List;

/**
 * Created by J4cob on 2017/9/14.
 */
public class StudioModel extends Studio {
    List<StudioProduct> products;
    List<Doctor> doctors;

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
