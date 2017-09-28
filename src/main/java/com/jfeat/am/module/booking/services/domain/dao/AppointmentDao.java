package com.jfeat.am.module.booking.services.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.booking.services.persistence.model.Appointment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
public interface AppointmentDao{
    List<Appointment> queryAppointment(Page<Appointment> page,
                                       @Param("status") String status,
                                       @Param("studioId") Long studioId,
                                       @Param("phone")Long phone);
}
