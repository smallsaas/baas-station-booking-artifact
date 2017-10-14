package com.jfeat.am.module.booking.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.booking.services.domain.model.StudioModel;
import com.jfeat.am.module.booking.services.domain.model.StudioProductModel;
import com.jfeat.am.module.booking.services.persistence.model.Appointment;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.persistence.model.StudioProduct;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/15.
 */
public interface DomainQueryService {

    /*
    *   queryStudioById
    * */
    List<Map<String, Object>> queryStudioById(long id);

    /*
    *   queryAllStudio
    * */

    List<Map<String, Object>> allStudio(Page<Studio> page);

    /*
    *   queryStudioByName
    * */
    List<Studio> queryStudioByName(Page<Studio> page,
                                   String name);

    /*
    *   queryProductByAttribute
    * */
    List<StudioProduct> queryStudioProduct(Page<StudioProduct> page,
                                           long studioId,
                                           String attribute);

    /*
    *   queryAppointmentByUserId
    * */
    List<Appointment> queryAppointmentByCustomerId(Page<Appointment> page, long id);

    /*
    *   queryStudioByStick
    * */
    List<Studio> queryStudioByStick(Page<Studio> page, String city);

    /*
    *   queryCity
    * */

    List<Studio> queryCity();

    /**
     * 模糊查找Appointment
     */
    List<Appointment> queryAppointment(Page<Appointment> page,
                                       String status,
                                       Long studioId,
                                       Long phone);

    /*
    *   查找店铺 by ServiceType or  StudioName
    * */
    List<Map<String, Object>> queryStudioByMultiple(Page<StudioModel> page,
                                                    String tname,
                                                    String name,
                                                    String city,
                                                    String stick,
                                                    BigDecimal latitude,
                                                    BigDecimal longitude);

    /*
        *   查找店铺 by site
        * */
    public List<Map<String, Object>> queryStudioBySite(Page<Studio> page,
                                                       String city, BigDecimal latitude, BigDecimal longitude
    );

    /*
    *       queryStudio and  show the  product
    * */
    StudioModel showStudioModel(long id);

    /*
    *   show  product details
    * */
    StudioProductModel showStudioProductModel(long studioId, long id);

    /*
    *   show Product list
    * */
    List<StudioProduct> studioProductList(Page<StudioProduct> page);

    /*
   *       精选产品列表
   * */
    List<StudioProduct> productStickList(Page<StudioProduct> page, String city);

    /*
    *   queryProductByName
    * */
    List<StudioProduct> queryProductByName(Page<StudioProduct> page,
                                           String name);
}
