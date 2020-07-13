package com.jfeat.am.module.booking.services.service.crud;

import com.jfeat.am.module.booking.services.persistence.model.StudioCollect;
import com.jfeat.crud.plus.CRUDServiceOnly;

import java.util.List;

/**
 * Created by J4cob on 2017/9/23.
 */
public interface CollectService extends CRUDServiceOnly<StudioCollect> {
    public Integer deleteCollect(Long studioId, Long userId);

    public List<StudioCollect> allCollect();
}
