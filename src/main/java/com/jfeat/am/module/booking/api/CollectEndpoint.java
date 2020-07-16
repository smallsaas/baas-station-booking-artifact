package com.jfeat.am.module.booking.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.booking.services.domain.model.StudioCollectModel;
import com.jfeat.am.module.booking.services.persistence.model.Customer;
import com.jfeat.am.module.booking.services.persistence.model.StudioCollect;
import com.jfeat.am.module.booking.services.service.crud.CollectService;
import com.jfeat.am.module.booking.services.service.path.PathService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by J4cob on 2017/9/23.
 */
@RestController
@RequestMapping("/api/collect")
@Api(value = "/api/collect")
public class CollectEndpoint  {

    @Resource
    CollectService collectService;
    @Resource
    PathService pathService;

    @GetMapping("/lists")
    @ApiOperation(value = "收集所有店铺记录并返回",response = List.class)
    public Tip allCollect() {
        return SuccessTip.create(collectService.allCollect());
    }

    @PostMapping
    @ApiOperation(value = "根据传递的StudioCollect创建相应的记录",response = Integer.class)
    @ApiParam(name = "studioCollect",value = "工作室集合实体类")
    public Tip createCollect(@Valid @RequestBody StudioCollect studioCollect) {

        long userId = 1l;//JWTKit.getUserId();
        studioCollect.setCreateTime(new Date());
        Customer customer = pathService.queryCustomerByUserId(userId);
        if (customer == null) {
            throw new BusinessException(BusinessCode.InvalidKey.getCode(),"customer not found.");
        }
        studioCollect.setCustomerId(customer.getId());
        return SuccessTip.create(pathService.addOrCancelFavors(studioCollect));
    }

    @DeleteMapping("/{studioId}")
    @ApiOperation(value = "根据提供ID删除收藏记录",response = Integer.class)
    @ApiParam(name = "id",value = "待删除的收藏记录ID")
    public Tip deleteCollect(@PathVariable Long studioId) {
        long customerId = 1l;//JWTKit.getUserId();
        return SuccessTip.create(collectService.deleteCollect(studioId, customerId));
    }

    @GetMapping("/users")
    @ApiOperation(value = "根据用户ID查询名下的工作室收藏记录",response = List.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前请求页",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录条数",paramType = "query")
    })
    public Tip getSelfFiles(Page page,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        long userId = 1l;//JWTKit.getUserId();
        Customer customer = pathService.queryCustomerByUserId(userId);
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<StudioCollectModel> model = pathService.queryUserCollects(page,customer.getId());
        page.setRecords(model);
        return SuccessTip.create(page);
    }
}
