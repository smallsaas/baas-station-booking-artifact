package com.jfeat.am.module.booking.services.service.path;

import com.jfeat.am.module.booking.api.bean.Ids;
import com.jfeat.am.module.booking.services.domain.model.AppointmentModel;
import com.jfeat.am.module.booking.services.domain.model.CustomerModel;
import com.jfeat.am.module.booking.services.persistence.model.Customer;
import com.jfeat.am.module.booking.services.persistence.model.StudioCollect;
import com.jfeat.am.module.booking.services.persistence.model.StudioService;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */
public interface PathService {
    /*
    *   delete Type
    * */
    public Integer deleteTypes(long id);

    Integer addStudioService(Long studioId, List<Long> ids);

    Integer addStudioPhotos(Long studioId, List<String> urls);


    /*
    *   add ProductPhotos
    * */
    Integer addProductPhotos(Long productId, List<String> urls);

    /*
   *   get user Info
   * */
    CustomerModel getMoreInfo(long userId);

    /*
    *   queryCustomerByUserId
    * */
    public Customer queryCustomerByUserId(long userId);

    /*
    *      添加或取消收藏
    * */

    Integer addOrCancelFavors(StudioCollect studioCollect);


    /*
    *   get Users appointment
    * */
    public AppointmentModel appointmentDetails(long id);
}
