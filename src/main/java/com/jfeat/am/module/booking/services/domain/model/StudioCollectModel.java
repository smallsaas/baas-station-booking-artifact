package com.jfeat.am.module.booking.services.domain.model;

import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.persistence.model.StudioCollect;
import java.util.List;

/**
 * Created by Administrator on 2017/10/18.
 */
public class StudioCollectModel extends StudioCollect{
    Studio studio;

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }
}
