package com.jfeat.am.module.booking.services.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
public interface StudioDao{

    /*
    *   query  by ServiceType
    * */
    List<Studio> queryStudioByServiceType(Page<Studio> page,
                             @Param("name") String name);


    /*
    *   query  by Site
    * */
    List<Studio> queryStudioBySite(Page<Studio> page,
                                          @Param("site") String site);

}
