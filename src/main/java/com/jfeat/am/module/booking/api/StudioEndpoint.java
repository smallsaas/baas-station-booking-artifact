package com.jfeat.am.module.booking.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.booking.services.persistence.model.Doctor;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.service.crud.StudioOverDoctorService;
import com.jfeat.am.module.booking.services.domain.service.DomainQueryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by J4cob on 2017/9/14.
 */
@RequestMapping("/api/studios")
@RestController
public class StudioEndpoint extends BaseController{
    @Resource
    StudioOverDoctorService sDservice;
    @Resource
    DomainQueryService domainQueryService;

    /*
    *   查找店铺
    * */
    @GetMapping("/lists")
    public  Tip queryStudio(Page page,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "name",required = false)String name){
        List<Studio> studios = domainQueryService.queryStudio(page,name);
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(studios);
        return SuccessTip.create(page);
    }


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

    @PostMapping("/{studioId}/doctors")
    public Tip addDoctor(@PathVariable long studioId,@Valid @RequestBody Doctor doctor){
        doctor.setStudioId(studioId);
        Integer result = sDservice.addSlaveItem(studioId,doctor);
        return SuccessTip.create(result);
    }
    @PutMapping("/{studioId}/doctors")
    public Tip updateDoctor(@PathVariable long studioId,@Valid@RequestBody Doctor doctor){
        Integer result = sDservice.updateSlaveItem(studioId,doctor);
        return SuccessTip.create(result);
    }
    @GetMapping("/{studioId}/doctors/{id}")
    public Tip showDoctor(@PathVariable long studioId,@PathVariable long id){
        Doctor result = sDservice.getSlaveItem(studioId,id);
        return SuccessTip.create(result);
    }
    @DeleteMapping("/{studioId}/doctors/{id}")
    public Tip deleteDoctor(@PathVariable long studioId,@PathVariable long id){
        Integer result = sDservice.removeSlaveItem(studioId,id);
        return SuccessTip.create(result);
    }
}
