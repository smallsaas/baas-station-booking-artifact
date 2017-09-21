package com.jfeat.am.module.booking.api;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.booking.services.service.crud.CustomerService;
import com.jfeat.am.module.booking.services.persistence.model.Customer;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by Administrator on 2017/9/15.
 */
@RequestMapping("/api/customers")
@RestController
public class CustomerEndpoint extends BaseController{
    @Resource
    CustomerService customerService;

    /*
  *   CRUD about Customer
  * */
    @PostMapping
    public Tip createCustomer(@Valid @RequestBody Customer customer){
        Integer result = customerService.createMaster(customer);
        return SuccessTip.create(result);
    }
    @PutMapping
    public Tip updateCustomer(@Valid@RequestBody Customer customer){
        Integer result = customerService.updateMaster(customer);
        return SuccessTip.create(result);
    }
    @GetMapping("/{id}")
    public Tip showCustomer(@PathVariable long id){
        Customer result = customerService.retrieveMaster(id);
        return SuccessTip.create(result);
    }
    @DeleteMapping("/{id}")
    public Tip deleteCustomer(@PathVariable long id){
        Integer result = customerService.deleteMaster(id);
        return SuccessTip.create(result);
    }
}
