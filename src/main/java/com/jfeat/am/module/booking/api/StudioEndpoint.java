package com.jfeat.am.module.booking.api;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.service.crud.StudioOverDoctorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by J4cob on 2017/9/14.
 */
@RequestMapping("/api/studios")
@RestController
public class StudioEndpoint extends BaseController{
    @Resource
    StudioOverDoctorService sDservice;

    /*
    *   CRUD about Studio
    * */
    @PostMapping
    public Tip createStudio(@Valid @RequestBody Studio studio){
        Integer result = sDservice.createMaster(studio);
        return SuccessTip.create(result);
    }
    @PutMapping
    public Tip updateStudio(@Valid@RequestBody Studio studio){
        Integer result = sDservice.updateMaster(studio);
        return SuccessTip.create(result);
    }
    @GetMapping("/{id}")
    public Tip showStudio(@PathVariable long id){
        Studio result = sDservice.retrieveMaster(id);
        return SuccessTip.create(result);
    }
    @DeleteMapping("/{id}")
    public Tip deleteStudio(@PathVariable long id){
        Integer result = sDservice.deleteMaster(id);
        return SuccessTip.create(result);
    }

    /*
    *   CRUD  Doctor
    * */

    @PostMapping
    public Tip addDoctor(@Valid @RequestBody Studio studio){
        Integer result = sDservice.createMaster(studio);
        return SuccessTip.create(result);
    }
    @PutMapping
    public Tip updateDoctor(@Valid@RequestBody Studio studio){
        Integer result = sDservice.updateMaster(studio);
        return SuccessTip.create(result);
    }
    @GetMapping("/{id}")
    public Tip showDoctor(@PathVariable long id){
        Studio result = sDservice.retrieveMaster(id);
        return SuccessTip.create(result);
    }
    @DeleteMapping("/{id}")
    public Tip deleteDoctoro(@PathVariable long id){
        Integer result = sDservice.deleteMaster(id);
        return SuccessTip.create(result);
    }
}
