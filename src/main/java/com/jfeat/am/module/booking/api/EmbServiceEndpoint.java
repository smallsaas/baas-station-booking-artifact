package com.jfeat.am.module.booking.api;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.booking.services.persistence.model.Advertising;
import com.jfeat.am.module.booking.services.persistence.model.Service;
import com.jfeat.am.module.booking.services.persistence.model.ServiceType;
import com.jfeat.am.module.booking.services.service.crud.AdvertisingService;
import com.jfeat.am.module.booking.services.service.crud.EmbServiceService;
import com.jfeat.am.module.booking.services.service.crud.ServiceTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by Administrator on 2017/9/15.
 */
@RequestMapping("/api/emb/services")
@RestController
public class EmbServiceEndpoint extends BaseController{
    @Resource
    EmbServiceService embServiceService;
    @Resource
    ServiceTypeService typeService;
    @Resource
    AdvertisingService advertisingService;


    @PostMapping("/ad")
    public Tip createAd(@Valid @RequestBody Advertising advertising){
        Integer result = advertisingService.createMaster(advertising);
        return SuccessTip.create(result);
    }
    @PutMapping("/ad")
    public Tip updateAd(@Valid@RequestBody Advertising advertising){
        Integer result = advertisingService.updateMaster(advertising);
        return SuccessTip.create(result);
    }
    @GetMapping("/ad/{id}")
    public Tip showAd(@PathVariable long id){
        Advertising result = advertisingService.retrieveMaster(id);
        return SuccessTip.create(result);
    }
    @DeleteMapping("/ad/{id}")
    public Tip deleteAd(@PathVariable long id){
        Integer result = advertisingService.deleteMaster(id);
        return SuccessTip.create(result);
    }


    /*
  *   CRUD about Service
  * */
    @PostMapping
    public Tip createEmbService(@Valid @RequestBody Service service){
        Integer result = embServiceService.createMaster(service);
        return SuccessTip.create(result);
    }
    @PutMapping
    public Tip updateEmbService(@Valid@RequestBody Service service){
        Integer result = embServiceService.updateMaster(service);
        return SuccessTip.create(result);
    }
    @GetMapping("/{id}")
    public Tip showEmbService(@PathVariable long id){
        Service result = embServiceService.retrieveMaster(id);
        return SuccessTip.create(result);
    }
    @DeleteMapping("/{id}")
    public Tip deleteEmbService(@PathVariable long id){
        Integer result = embServiceService.deleteMaster(id);
        return SuccessTip.create(result);
    }


    /*
    *   servicetype  暂时没用到
    * */
    @PostMapping("/types")
    public Tip createType(@Valid @RequestBody ServiceType type){
        Integer result = typeService.createMaster(type);
        return SuccessTip.create(result);
    }
    @PutMapping("/types")
    public Tip updateType(@Valid@RequestBody ServiceType type){
        Integer result = typeService.updateMaster(type);
        return SuccessTip.create(result);
    }
    @GetMapping("/types/{id}")
    public Tip showType(@PathVariable long id){
        ServiceType result = typeService.retrieveMaster(id);
        return SuccessTip.create(result);
    }
    @DeleteMapping("/types/{id}")
    public Tip deleteType(@PathVariable long id){
        Integer result = typeService.deleteMaster(id);
        return SuccessTip.create(result);
    }
}
