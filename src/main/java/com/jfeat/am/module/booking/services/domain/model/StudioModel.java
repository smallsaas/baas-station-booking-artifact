package com.jfeat.am.module.booking.services.domain.model;

import com.jfeat.am.module.booking.services.persistence.model.Doctor;
import com.jfeat.am.module.booking.services.persistence.model.Studio;

import java.util.List;

/**
 * Created by J4cob on 2017/9/14.
 */
public class StudioModel extends Studio {

    List<Doctor> doctors;

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

}
