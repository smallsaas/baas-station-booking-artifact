package com.jfeat.am.module.booking.services.service.crud.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.booking.services.service.crud.CoversService;
import com.jfeat.am.module.booking.services.persistence.mapper.CoversMapper;
import com.jfeat.am.module.booking.services.persistence.model.Covers;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/19.
 */
@Service
public class CoversServiceImpl extends CRUDServiceOnlyImpl<Covers> implements CoversService {

    @Resource
    CoversMapper coversMapper;

    @Override
    protected BaseMapper<Covers> getMasterMapper() {
        return coversMapper;
    }
}
