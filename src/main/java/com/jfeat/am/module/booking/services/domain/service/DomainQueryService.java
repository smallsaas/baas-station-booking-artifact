package com.jfeat.am.module.booking.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
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
    *   查找店铺
    * */
    public List<Studio> queryStudio(Page<Studio> page,
                                    String name);
}
