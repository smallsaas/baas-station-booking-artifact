package com.jfeat.am.module.booking.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.common.exception.BizExceptionEnum;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.booking.services.domain.dao.CustomerDao;
import com.jfeat.am.module.booking.services.domain.model.CustomerModel;
import com.jfeat.am.module.booking.services.domain.model.StudioCollectModel;
import com.jfeat.am.module.booking.services.persistence.model.Customer;
import com.jfeat.am.module.booking.services.persistence.model.StudioCollect;
import com.jfeat.am.module.booking.services.service.crud.CollectService;
import com.jfeat.am.module.booking.services.service.path.PathService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by J4cob on 2017/9/23.
 */
@RestController
@RequestMapping("/api/collect")
public class CollectEndpoint extends BaseController {

    @Resource
    CollectService collectService;
    @Resource
    PathService pathService;

    @GetMapping("/lists")
    public Tip allCollect() {
        return SuccessTip.create(collectService.allCollect());
    }

    @PostMapping
    public Tip createCollect(@Valid @RequestBody StudioCollect studioCollect) {

        long userId = JWTKit.getUserId(getHttpServletRequest());
        studioCollect.setCreateTime(new Date());
        Customer customer = pathService.queryCustomerByUserId(userId);
        if (customer == null) {
            throw new BusinessException(BizExceptionEnum.SERVER_ERROR.getCode(),"customer not found.");
        }
        studioCollect.setCustomerId(customer.getId());
        return SuccessTip.create(pathService.addOrCancelFavors(studioCollect));
    }

    @DeleteMapping("/{studioId}")
    public Tip deleteCollect(@PathVariable Long studioId) {
        long customerId = JWTKit.getUserId(getHttpServletRequest());
        return SuccessTip.create(collectService.deleteCollect(studioId, customerId));
    }
    @GetMapping("/users")
    public Tip getSelfFiles(Page page,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        long userId = JWTKit.getUserId(getHttpServletRequest());
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<StudioCollectModel> model = pathService.queryUserCollects(page,userId);
        page.setRecords(model);
        return SuccessTip.create(page);
    }
}
