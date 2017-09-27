package com.jfeat.am.module.booking.api;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.booking.services.persistence.model.StudioCollect;
import com.jfeat.am.module.booking.services.service.crud.CollectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by J4cob on 2017/9/23.
 */
@RestController
@RequestMapping("/api/collect")
public class CollectEndpoint extends BaseController{

    @Resource
    CollectService collectService;

    @PostMapping
    public Tip createCollect(@Valid @RequestBody StudioCollect studioCollect){
        long userId = JWTKit.getUserId(getHttpServletRequest());
        studioCollect.setCustomerId(userId);
        return SuccessTip.create(collectService.createMaster(studioCollect));
    }

    @DeleteMapping("/{studioId}")
    public Tip deleteCollect(@PathVariable Long studioId){
        long customerId = JWTKit.getUserId(getHttpServletRequest());
        return SuccessTip.create(collectService.deleteCollect(studioId,customerId));
    }
}
