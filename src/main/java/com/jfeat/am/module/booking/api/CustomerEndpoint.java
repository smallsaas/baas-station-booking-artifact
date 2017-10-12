package com.jfeat.am.module.booking.api;

import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.booking.services.domain.model.CustomerModel;
import com.jfeat.am.module.booking.services.service.crud.CustomerService;
import com.jfeat.am.module.booking.services.persistence.model.Customer;


import com.jfeat.am.module.booking.services.service.path.PathService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
@RequestMapping("/api/customers")
@RestController
public class CustomerEndpoint extends BaseController {
    @Resource
    CustomerService customerService;
    @Resource
    PathService pathService;

    /*
    *   Customer
    * */
    @GetMapping
    public Tip getAllCustomers() {
        List<Customer> result = customerService.getAllCustomers();
        return SuccessTip.create(result);
    }

    /*
  *   CRUD about Customer
  * */
    @PostMapping
    public Tip createCustomer(@Valid @RequestBody Customer customer) {
        customer.setUserId(JWTKit.getUserId(getHttpServletRequest()));
        customer.setCreateTime(new Date());
        Customer result = customerService.registerCustomer(customer);
        return SuccessTip.create(result);
    }

    @PutMapping
    public Tip updateCustomer(@Valid @RequestBody Customer customer) {
        Integer result = customerService.updateMaster(customer);
        return SuccessTip.create(result);
    }

    @GetMapping("/{id}")
    public Tip showCustomer(@PathVariable long id) {
        Customer result = customerService.retrieveMaster(id);
        return SuccessTip.create(result);
    }

    @DeleteMapping("/{id}")
    public Tip deleteCustomer(@PathVariable long id) {
        Integer result = customerService.deleteMaster(id);
        return SuccessTip.create(result);
    }

    @GetMapping("/users")
    public Tip getSelfFiles() {
        long userId = JWTKit.getUserId(getHttpServletRequest());
        CustomerModel model = pathService.getMoreInfo(userId);
        return SuccessTip.create(model);
    }
}
