package com.jfeat.am.module.booking.services.service.crud;

import com.jfeat.am.common.crud.CRUDServiceOverSlave;
import com.jfeat.am.module.booking.services.domain.model.StudioModel;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.persistence.model.StudioProduct;

/**
 * Created by J4cob on 2017/9/14.
 */
public interface StudioOverProductService extends CRUDServiceOverSlave<Studio,StudioModel,StudioProduct>{

    /*public Integer addTest(Studio studio);*/
}
