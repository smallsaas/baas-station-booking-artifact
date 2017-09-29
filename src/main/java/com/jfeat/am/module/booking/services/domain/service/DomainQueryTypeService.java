package com.jfeat.am.module.booking.services.domain.service;



import com.jfeat.am.module.booking.services.persistence.model.ServiceType;
import com.jfeat.am.module.booking.services.persistence.model.StudioService;

import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */
public interface DomainQueryTypeService {

    /*
    *   查看所有的服务类型
    * */

    public List<ServiceType> allServiceType();

    /*
    *   查看所有店铺的服务
    *
    * */
    public List<StudioService> allService();
}
