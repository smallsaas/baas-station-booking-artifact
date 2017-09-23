package com.jfeat.am.module.booking.services.service.crud.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.booking.services.persistence.mapper.StudioCollectMapper;
import com.jfeat.am.module.booking.services.persistence.model.StudioCollect;
import com.jfeat.am.module.booking.services.service.crud.CollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by J4cob on 2017/9/23.
 */
@Service
public class CollectServiceImpl extends CRUDServiceOnlyImpl<StudioCollect> implements CollectService{
    @Resource
    StudioCollectMapper studioCollectMapper;


    @Override
    protected BaseMapper<StudioCollect> getMasterMapper() {
        return studioCollectMapper;
    }

    public Integer deleteCollect(Long studioId, Long userId){
        Map<String,Object> map = new HashMap<>();
        map.put("studio_id",studioId);
        map.put("user_id",userId);
        return studioCollectMapper.deleteByMap(map);
    }
}
