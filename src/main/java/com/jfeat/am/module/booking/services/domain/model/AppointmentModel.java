package com.jfeat.am.module.booking.services.domain.model;

import com.jfeat.am.module.booking.services.persistence.model.Appointment;
import com.jfeat.am.module.booking.services.persistence.model.Studio;

/**
 * Created by Administrator on 2017/10/16.
 */
public class AppointmentModel extends Appointment{
    Studio studio;


    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }
}
