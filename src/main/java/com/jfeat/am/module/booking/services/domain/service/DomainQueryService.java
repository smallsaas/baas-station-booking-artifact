package com.jfeat.am.module.booking.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.booking.services.domain.model.StudioModel;
import com.jfeat.am.module.booking.services.persistence.model.Appointment;
import com.jfeat.am.module.booking.services.persistence.model.Studio;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
public interface DomainQueryService {

    /**   模糊查找Appointment
    * */
    public List<Appointment> queryAppointment(Page<Appointment> page,
                                              String status,
                                              long StudioId,
                                              Date createTime);
    /*
    *   查找店铺 by ServiceType
    * */
    public List<Studio> queryStudioByServiceType(Page<Studio> page,
                                    String name);

    /*
        *   查找店铺 by site
        * */
    public List<Studio> queryStudioBySite(Page<Studio> page,
                                          String site);

    /*
    *       queryStudio and  show the  product
    * */
    public StudioModel showStudioModel(long id);
}
