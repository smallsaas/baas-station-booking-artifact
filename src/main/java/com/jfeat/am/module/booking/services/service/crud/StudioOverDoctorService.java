package com.jfeat.am.module.booking.services.service.crud;

import com.jfeat.am.common.crud.CRUDServiceOverSlave;
import com.jfeat.am.module.booking.services.domain.model.StudioModel;
import com.jfeat.am.module.booking.services.persistence.model.Doctor;
import com.jfeat.am.module.booking.services.persistence.model.Studio;

/**
 * Created by J4cob on 2017/9/14.
 */
public interface StudioOverDoctorService extends CRUDServiceOverSlave<Studio,StudioModel,Doctor>{
}
