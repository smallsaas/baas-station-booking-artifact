package com.jfeat.am.module.booking.services.service.crud.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.booking.services.persistence.mapper.AdvertisingMapper;
import com.jfeat.am.module.booking.services.persistence.model.Advertising;
import com.jfeat.am.module.booking.services.service.crud.AdvertisingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by J4cob on 2017/9/18.
 */
@Service
public class AdvertisingServiceImpl extends CRUDServiceOnlyImpl<Advertising> implements AdvertisingService{

    @Resource
    AdvertisingMapper advertisingMapper;

    @Override
    protected BaseMapper<Advertising> getMasterMapper() {
        return advertisingMapper;
    }
}
