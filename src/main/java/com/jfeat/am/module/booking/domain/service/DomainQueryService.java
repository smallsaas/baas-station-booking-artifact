package com.jfeat.am.module.booking.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.booking.domain.model.StudioModel;
import com.jfeat.am.module.booking.domain.model.StudioProductModel;
import com.jfeat.am.module.booking.services.persistence.model.Appointment;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.persistence.model.StudioProduct;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
public interface DomainQueryService {

    /**
     * 模糊查找Appointment
     */
    public List<Appointment> queryAppointment(Page<Appointment> page,
                                              String status,
                                              long StudioId,
                                              Date createTime);

    /*
    *   查找店铺 by ServiceType or  StudioName
    * */
    public List<Studio> queryStudioByMultiple(Page<Studio> page,
                                              String tname,
                                              String name);

    /*
        *   查找店铺 by site
        * */
    public List<Studio> queryStudioBySite(Page<Studio> page,
                                          String site);

    /*
    *   查找店铺 by name
    * */

    public List<Studio> queryStudioByName(Page<Studio> page,
                                          String name);

    /*
    *       queryStudio and  show the  product
    * */
    public StudioModel showStudioModel(long id);

    /*
    *   show  product details
    * */
    public StudioProductModel showStudioProductModel(long studioId,long id);
    /*
    *   show Product list
    * */
    public  List<StudioProduct> studioProductList();
}
