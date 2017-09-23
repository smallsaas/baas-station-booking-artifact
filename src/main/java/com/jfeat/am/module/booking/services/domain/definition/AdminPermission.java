package com.jfeat.am.module.booking.services.domain.definition;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by J4cob on 2017/9/18.
 */
public class  AdminPermission {
    public  static  final  String CREATE = "studio.create";
    public  static  final  String EDIT = "studio.edit";
    public  static  final  String DELETE = "studio.delete";
    public  static  final  String QUERY = "appointment.query";

    /**
     * Created by Administrator on 2017/9/15.
     */
    public static interface StudioDao {

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
                                       @Param("site") String site,
                                       @Param("latitude") BigDecimal latitude,
                                       @Param("longitude") BigDecimal longitude);

        /*
        *   query  by name
        * */
        List<Studio> queryStudioByName(Page<Studio> page,
                                       @Param("name") String name);


    }
}
