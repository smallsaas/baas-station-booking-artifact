package com.jfeat.am.module.booking.services.service.crud;

import com.jfeat.am.common.crud.CRUDServiceOnly;
import com.jfeat.am.module.booking.services.persistence.model.Customer;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public interface CustomerService extends CRUDServiceOnly<Customer>{

    public List<Customer> getAllCustomers();
}
