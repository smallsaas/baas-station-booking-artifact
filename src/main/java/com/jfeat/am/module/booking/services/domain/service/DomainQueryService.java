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
    *   queryStudioByName
    * */
    public Studio queryStudioByName(
                                          String name);

    /*
    *   queryProductByAttribute
    * */
    public List<StudioProduct> queryStudioProduct(Page<StudioProduct> page,
                                                  long studioId,
                                                  String attribute);

    /*
    *   queryAppointmentByUserId
    * */
    public List<Appointment> queryAppointmentByUserId(Page<Appointment> page, Long userId);

    /*
    *   queryStudioByStick
    * */
    public List<Studio> queryStudioByStick(Page<Studio> page, String city);

    /*
    *   queryCity
    * */

    public List<Studio> queryCity();

    /**
     * 模糊查找Appointment
     */
    public List<Appointment> queryAppointment(Page<Appointment> page,
                                              String status,
                                              Long studioId,
                                              Long phone);

    /*
    *   查找店铺 by ServiceType or  StudioName
    * */
    public List<Map<String, Object>> queryStudioByMultiple(Page<StudioModel> page,
                                                           String tname,
                                                           String name,
                                                           BigDecimal latitude,
                                                           BigDecimal longitude);

    /*
        *   查找店铺 by site
        * */
    public List<Map<String, Object>> queryStudioBySite(Page<Studio> page,
                                                       String site, BigDecimal lat, BigDecimal lng
    );

    /*
    *       queryStudio and  show the  product
    * */
    public StudioModel showStudioModel(long id);

    /*
    *   show  product details
    * */
    public StudioProductModel showStudioProductModel(long studioId, long id);

    /*
    *   show Product list
    * */
    public List<StudioProduct> studioProductList();

    /*
   *       精选产品列表
   * */
    public List<StudioProduct> productStickList(Page<StudioProduct> page);

    /*
    *   queryProductByName
    * */
    public  List<StudioProduct> queryProductByName(Page<StudioProduct> page,
                                                   String name);
}
