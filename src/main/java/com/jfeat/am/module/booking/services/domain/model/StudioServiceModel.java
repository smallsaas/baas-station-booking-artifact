package com.jfeat.am.module.booking.services.domain.model;

import com.jfeat.am.module.booking.services.persistence.model.StudioService;
import java.util.List;


/**
 * Created by Administrator on 2017/10/9.
 */
public class StudioServiceModel extends StudioService{
    private List<Long> ids;




    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
