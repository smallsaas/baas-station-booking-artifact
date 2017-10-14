package com.jfeat.am.module.booking.services.domain.model;

import com.jfeat.am.module.booking.services.persistence.model.Customer;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.persistence.model.StudioCollect;
import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */
public class CustomerModel extends Customer{
    List<StudioCollect> favors;
    List<Studio> studios;

    public List<Studio> getStudios() {
        return studios;
    }

    public void setStudios(List<Studio> studios) {
        this.studios = studios;
    }

    public List<StudioCollect> getFavors() {
        return favors;
    }

    public void setFavors(List<StudioCollect> favors) {
        this.favors = favors;
    }
}
