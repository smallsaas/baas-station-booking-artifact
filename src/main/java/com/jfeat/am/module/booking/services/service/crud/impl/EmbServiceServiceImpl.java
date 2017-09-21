package com.jfeat.am.module.booking.services.service.crud.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.booking.services.service.crud.EmbServiceService;
import com.jfeat.am.module.booking.services.persistence.mapper.ServiceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/15.
 */
@Service
public class EmbServiceServiceImpl extends CRUDServiceOnlyImpl<com.jfeat.am.module.booking.services.persistence.model.Service>
        implements EmbServiceService {

    @Resource
    ServiceMapper embMapper;

    @Override
    protected BaseMapper<com.jfeat.am.module.booking.services.persistence.model.Service> getMasterMapper() {
        return embMapper;
    }
}
