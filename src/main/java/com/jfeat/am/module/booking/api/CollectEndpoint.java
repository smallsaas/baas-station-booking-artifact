package com.jfeat.am.module.booking.api;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.booking.services.domain.dao.CustomerDao;
import com.jfeat.am.module.booking.services.domain.model.CustomerModel;
import com.jfeat.am.module.booking.services.persistence.model.Customer;
import com.jfeat.am.module.booking.services.persistence.model.StudioCollect;
import com.jfeat.am.module.booking.services.service.crud.CollectService;
import com.jfeat.am.module.booking.services.service.path.PathService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

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
        studioCollect.setCustomerId(customer.getId());
        return SuccessTip.create(pathService.addOrCancelFavors(studioCollect));
    }

    @DeleteMapping("/{studioId}")
    public Tip deleteCollect(@PathVariable Long studioId) {
        long customerId = JWTKit.getUserId(getHttpServletRequest());
        return SuccessTip.create(collectService.deleteCollect(studioId, customerId));
    }
    @GetMapping("/users")
    public Tip getSelfFiles() {
        long userId = JWTKit.getUserId(getHttpServletRequest());
        CustomerModel model = pathService.getMoreInfo(userId);
        return SuccessTip.create(model);
    }
}
