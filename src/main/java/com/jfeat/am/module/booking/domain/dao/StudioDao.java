package com.jfeat.am.module.booking.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
public interface StudioDao {

    /*
    *   query  by ServiceType
    * */
    List<Studio> queryStudioByMultiple(Page<Studio> page,
                                       @Param("tname") String tname,
                                       @Param("name") String name);


    /*
    *   query  by Site
    * */
    List<Studio> queryStudioBySite(Page<Studio> page,
                                   @Param("site") String site);

    /*
    *   query  by name
    * */
    List<Studio> queryStudioByName(Page<Studio> page,
                                   @Param("name") String name);


}
