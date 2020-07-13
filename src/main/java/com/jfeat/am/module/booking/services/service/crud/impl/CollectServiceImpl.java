package com.jfeat.am.module.booking.services.service.crud.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.booking.services.persistence.mapper.StudioCollectMapper;
import com.jfeat.am.module.booking.services.persistence.model.StudioCollect;
import com.jfeat.am.module.booking.services.service.crud.CollectService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
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

    @Override
    public List<StudioCollect>  allCollect(){
        List<StudioCollect> collects = studioCollectMapper.selectList(new EntityWrapper<>());
        return collects;
    }

    @Override
    public Integer deleteCollect(Long studioId, Long customerId){
        Map<String,Object> map = new HashMap<>();
        map.put("studio_id",studioId);
        map.put("customer_id",customerId);
        return studioCollectMapper.deleteByMap(map);
    }
}
