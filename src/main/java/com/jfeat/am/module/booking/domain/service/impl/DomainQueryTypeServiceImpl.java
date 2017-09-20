package com.jfeat.am.module.booking.domain.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.booking.domain.service.DomainQueryTypeService;
import com.jfeat.am.module.booking.services.persistence.mapper.ServiceTypeMapper;
import com.jfeat.am.module.booking.services.persistence.model.ServiceType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */
@Service
public class DomainQueryTypeServiceImpl implements DomainQueryTypeService{
    @Resource
    ServiceTypeMapper serviceTypeMapper;

    public List<ServiceType> allServiceType(){
        List<ServiceType> typeList = serviceTypeMapper.selectList(new EntityWrapper<>());
        return typeList;
    }
}
