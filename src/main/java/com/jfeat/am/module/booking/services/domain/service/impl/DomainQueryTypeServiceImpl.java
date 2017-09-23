package com.jfeat.am.module.booking.services.domain.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.booking.services.domain.service.DomainQueryTypeService;
import com.jfeat.am.module.booking.services.persistence.mapper.ServiceMapper;
import com.jfeat.am.module.booking.services.persistence.mapper.ServiceTypeMapper;
import com.jfeat.am.module.booking.services.persistence.model.ServiceType;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */
@Service
public class DomainQueryTypeServiceImpl implements DomainQueryTypeService {
    @Resource
    ServiceTypeMapper serviceTypeMapper;
    @Resource
    ServiceMapper embServiceMapper;

    public List<ServiceType> allServiceType(){
        List<ServiceType> typeList = serviceTypeMapper.selectList(new EntityWrapper<>());
        return typeList;
    }

    public List<com.jfeat.am.module.booking.services.persistence.model.Service> allService(){
        List<com.jfeat.am.module.booking.services.persistence.model.Service> typeList = embServiceMapper.selectList(new EntityWrapper<>());
        return typeList;
    }

}
