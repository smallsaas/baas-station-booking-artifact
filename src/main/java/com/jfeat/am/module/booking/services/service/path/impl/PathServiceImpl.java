package com.jfeat.am.module.booking.services.service.path.impl;

import com.jfeat.am.module.booking.api.bean.Ids;
import com.jfeat.am.module.booking.services.persistence.mapper.StudioServiceMapper;
import com.jfeat.am.module.booking.services.persistence.model.StudioService;
import com.jfeat.am.module.booking.services.service.path.PathService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by Administrator on 2017/10/9.
 */
@Service
public class PathServiceImpl implements PathService{
    @Resource
    StudioServiceMapper studioServiceMapper;
    public Integer addStudioService(StudioService studioService, List<Long> ids){
        int affect = ids.size();
        while(affect >= 0){
        studioServiceMapper.insert(studioService);
            affect--;
        }
        return affect;
    }
}
