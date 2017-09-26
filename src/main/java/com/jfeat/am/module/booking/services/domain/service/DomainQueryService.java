package com.jfeat.am.module.booking.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.booking.services.domain.model.StudioModel;
import com.jfeat.am.module.booking.services.domain.model.StudioProductModel;
import com.jfeat.am.module.booking.services.persistence.model.Appointment;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.persistence.model.StudioProduct;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
public interface DomainQueryService {

    /*
    *   queryStudioByStick
    * */
    public List<Studio> queryStudioByStick();

    /*
    *   queryCity
    * */

    public List<Studio> queryCity();
    /**
     * 模糊查找Appointment
     */
    public List<Appointment> queryAppointment(Page<Appointment> page,
                                              String status,
                                              long StudioId);

    /*
    *   查找店铺 by ServiceType or  StudioName
    * */
    public List<StudioModel> queryStudioByMultiple(Page<StudioModel> page,
                                                   String tname,
                                                   String name);

    /*
        *   查找店铺 by site
        * */
    public List<Studio> queryStudioBySite(Page<Studio> page,
                                          String site, BigDecimal lat, BigDecimal lng
    );

    /*
    *       queryStudio and  show the  product
    * */
    public StudioModel showStudioModel(long id);

    /*
    *   show  product details
    * */
    public StudioProductModel showStudioProductModel(long studioId, long id);

    /*
    *   show Product list
    * */
    public List<StudioProduct> studioProductList();
}
