package com.jfeat.am.module.booking.api;

import com.jfeat.am.module.booking.services.persistence.model.Customer;
import com.jfeat.am.module.booking.services.service.crud.CustomerService;
import com.jfeat.am.module.booking.services.service.path.PathService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by Administrator on 2017/9/15.
 */
@RequestMapping("/api/customers")
@RestController
@Api(value = "/api/customers")
public class CustomerEndpoint {
    @Resource
    CustomerService customerService;
    @Resource
    PathService pathService;

    /*
    *   Customer
    * */
    @GetMapping
    @ApiOperation(value = "获取所有的客户记录并返回",response = List.class)
    public Tip getAllCustomers() {
        List<Customer> result = customerService.getAllCustomers();
        return SuccessTip.create(result);
    }

    /*
  *   CRUD about Customer
  * */
    @PostMapping
    @ApiOperation(value = "根据传递的Customer实体类创建数据库记录",response = Customer.class)
    @ApiParam(name = "customer",value = "客户记录实体类")
    public Tip createCustomer(@Valid @RequestBody Customer customer) {
        customer.setUserId(1L);// JWTKit.getUserId()
        customer.setCreateTime(new Date());
        Customer result = customerService.registerCustomer(customer);
        return SuccessTip.create(result);
    }

    @PutMapping
    @ApiOperation(value = "根据提供的Customer实体类进行相关记录的更新操作",response = Integer.class)
    @ApiParam(name = "customer",value = "客户记录实体类")
    public Tip updateCustomer(@Valid @RequestBody Customer customer) {
        Integer result = customerService.updateMaster(customer);
        return SuccessTip.create(result);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据提供的客户ID进行相关记录的查找操作",response = Customer.class)
    @ApiParam(name = "id",value = "客户记录ID")
    public Tip showCustomer(@PathVariable long id) {
        Customer result = customerService.retrieveMaster(id);
        return SuccessTip.create(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据提供的客户ID进行相关记录的删除操作",response = Integer.class)
    @ApiParam(name = "id",value = "客户记录ID")
    public Tip deleteCustomer(@PathVariable long id) {
        Integer result = customerService.deleteMaster(id);
        return SuccessTip.create(result);
    }


}
