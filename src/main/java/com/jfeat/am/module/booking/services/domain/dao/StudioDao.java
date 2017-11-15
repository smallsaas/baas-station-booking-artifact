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
    *   queryStudioById
    * */
    StudioModel queryStudioById(@Param("id")long id);
    /*
    *       queryCity
    * */
    List<Studio> queryCity();

    /*
    *   query  by ServiceType
    * */
    List<Map<String, Object>> queryStudioByTypeName(Page<StudioModel> page,
                                                    @Param("typeName") String typeName,
                                                    @Param("city") String city);

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
                                                @Param("city") String city,
                                                @Param(("typeName")) String typeName,
                                                @Param("name") String name,
                                                @Param("latitude") BigDecimal latitude,
                                                @Param("longitude") BigDecimal longitude);

    /*
    *   query  by name
    * */
    List<Studio> queryStudioByName(Page<Studio> page,
                                   @Param("name") String name);


    List<Map<String,Object>> allStudio(Page<Studio> page);


}
