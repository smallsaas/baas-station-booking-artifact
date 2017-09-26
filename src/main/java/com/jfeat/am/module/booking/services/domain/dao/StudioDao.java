package com.jfeat.am.module.booking.services.domain.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.booking.services.domain.model.StudioModel;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

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
        List<StudioModel> queryStudioByMultiple(Page<StudioModel> page,
                                                @Param("tname") String tname,
                                                @Param("name") String name);

        /*
        *       queryStudioByStick
        * */
        List<Studio> queryStudioByStick();


        /*
        *   query  by Site
        * */
        List<Studio> queryStudioBySite(Page<Studio> page,
                                       @Param("site") String site,
                                       @Param("latitude") BigDecimal latitude,
                                       @Param("longitude") BigDecimal longitude);

        /*
        *   query  by name
        * */
        List<Studio> queryStudioByName(Page<Studio> page,
                                       @Param("name") String name);


}
