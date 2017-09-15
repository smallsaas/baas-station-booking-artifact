package com.jfeat.am.module.booking.services.service.crud.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;


import com.jfeat.am.module.booking.services.persistence.mapper.CustomerMapper;
import com.jfeat.am.module.booking.services.persistence.model.Customer;
import com.jfeat.am.module.booking.services.service.crud.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/14.
 */
@Service
public class CustomerServiceImpl extends CRUDServiceOnlyImpl<Customer> implements CustomerService {
    @Resource
    CustomerMapper customerMapper;

    @Override
    protected BaseMapper<Customer> getMasterMapper() {
        return customerMapper;
    }
}
