package com.jfeat.am.module.booking.services.service.crud.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;


import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.booking.services.service.crud.CustomerService;
import com.jfeat.am.module.booking.services.persistence.mapper.CustomerMapper;
import com.jfeat.am.module.booking.services.persistence.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Customer> getAllCustomers() {
        List<Customer> getAllCustomers = customerMapper.selectList(new EntityWrapper<>());
        return getAllCustomers;
    }

    public Customer registerCustomer(Customer customer) {
        List<Customer> customers = customerMapper.selectList(new EntityWrapper<Customer>().eq("user_id", customer.getUserId()));
        if (customers == null || customers.size() == 0) {
            customerMapper.insert(customer);
            return customer;
        } else {
            return  customerMapper.selectList(new EntityWrapper<Customer>().eq("openid", customer.getOpenid())).get(0);
        }
    }

}
