package com.jfeat.am.module.booking.api;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.booking.domain.service.DomainQueryTypeService;
import com.jfeat.am.module.booking.services.service.crud.EmbServiceService;
import com.jfeat.am.module.booking.services.service.crud.ServiceTypeService;
import com.jfeat.am.module.booking.services.persistence.model.Service;
import com.jfeat.am.module.booking.services.persistence.model.ServiceType;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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
    DomainQueryTypeService queryTypeService;

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
    @GetMapping("/types/lists")
    public Tip showAllType(){
        List<ServiceType> typeList = queryTypeService.allServiceType();
        return SuccessTip.create(typeList);
    }

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

     /* *//*
    *   crud covers
    * *//*
    @PostMapping("/covers")
    public Tip addCovers(@Valid @RequestBody Advertising ad){
        Integer result = advertisingService.createMaster(ad);
        return SuccessTip.create(result);
    }
    @PutMapping("/covers")
    public Tip updateCovers(@Valid@RequestBody Advertising ad){
        Integer result = advertisingService.updateMaster(ad);
        return SuccessTip.create(result);
    }
    @GetMapping("/covers/{id}")
    public Tip showCovers(@PathVariable long id){
        Advertising result = advertisingService.retrieveMaster(id);
        return SuccessTip.create(result);
    }
    @DeleteMapping("/covers/{id}")
    public Tip deleteCovers(@PathVariable long id){
        Integer result = advertisingService.deleteMaster(id);
        return SuccessTip.create(result);
    }


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
    }*/



}
