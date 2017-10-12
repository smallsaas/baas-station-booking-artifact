package com.jfeat.am.module.booking.services.domain.dao;

import com.jfeat.am.module.booking.services.persistence.model.Customer;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/10/12.
 */
public interface CustomerDao {

    Customer queryCustomerByUserId(@Param("userId") long userId);
}
