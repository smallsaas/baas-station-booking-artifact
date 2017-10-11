package com.jfeat.am.module.booking.services.domain.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.booking.services.domain.model.StudioModel;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/25.
 */
public interface StudioDao {

    /*
    *       queryCity
    * */
    List<Studio> queryCity();

    /*
    *   query  by ServiceType
    * */
    List<Map<String, Object>> queryStudioByMultiple(Page<StudioModel> page,
                                                    @Param("tname") String tname,
                                                    @Param("latitude") BigDecimal latitude,
                                                    @Param("longitude") BigDecimal longitude);

    /*
    *       queryStudioByStick
    * */
    List<Studio> queryStudioByStick(
            Page<Studio> page,
            @Param("city") String city);


    /*
    *   query  by Site
    * */
    List<Map<String, Object>> queryStudioBySite(Page<Studio> page,
                                                @Param("site") String site,
                                                @Param("latitude") BigDecimal latitude,
                                                @Param("longitude") BigDecimal longitude);

    /*
    *   query  by name
    * */
    List<Studio> queryStudioByName(Page<Studio> page,
                                   @Param("name") String name);


    List<Map<String,Object>> allStudio(Page<Studio> page);


}
