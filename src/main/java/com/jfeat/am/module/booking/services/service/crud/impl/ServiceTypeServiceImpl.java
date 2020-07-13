package com.jfeat.am.module.booking.services.service.crud.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.booking.services.service.crud.ServiceTypeService;
import com.jfeat.am.module.booking.services.persistence.mapper.ServiceTypeMapper;
import com.jfeat.am.module.booking.services.persistence.model.ServiceType;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/15.
 */
@Service
public class ServiceTypeServiceImpl extends CRUDServiceOnlyImpl<ServiceType>
                        implements ServiceTypeService {

    @Resource
    ServiceTypeMapper typeMapper;

    @Override
    protected BaseMapper<ServiceType> getMasterMapper() {
        return typeMapper;
    }
}
