package com.jfeat.am.module.booking.domain.service;

import com.jfeat.am.module.booking.services.persistence.model.ServiceType;

import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */
public interface DomainQueryTypeService {
    public List<ServiceType> allServiceType();
}
