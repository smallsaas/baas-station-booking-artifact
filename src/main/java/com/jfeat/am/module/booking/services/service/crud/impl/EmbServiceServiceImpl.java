package com.jfeat.am.module.booking.services.service.crud.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.jfeat.am.module.booking.services.persistence.model.StudioService;
import com.jfeat.am.module.booking.services.service.crud.EmbServiceService;
import com.jfeat.am.module.booking.services.persistence.mapper.StudioServiceMapper;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/15.
 */
@Service
public class EmbServiceServiceImpl extends CRUDServiceOnlyImpl<StudioService>
        implements EmbServiceService {

    @Resource
    StudioServiceMapper studioServiceMapper;

    @Override
    protected BaseMapper<com.jfeat.am.module.booking.services.persistence.model.StudioService> getMasterMapper() {
        return studioServiceMapper;
    }
}
