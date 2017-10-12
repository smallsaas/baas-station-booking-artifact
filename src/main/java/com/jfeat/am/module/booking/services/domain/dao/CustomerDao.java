package com.jfeat.am.module.booking.services.domain.dao;

import com.jfeat.am.module.booking.services.persistence.model.Customer;

/**
 * Created by Administrator on 2017/10/12.
 */
public interface CustomerDao {

    Customer queryCustomerByUserId(long userId);
}
