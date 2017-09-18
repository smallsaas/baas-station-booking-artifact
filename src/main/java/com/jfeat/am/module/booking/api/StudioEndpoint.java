package com.jfeat.am.module.booking.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.booking.services.domain.model.StudioModel;
import com.jfeat.am.module.booking.services.persistence.model.Doctor;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.persistence.model.StudioProduct;
import com.jfeat.am.module.booking.services.service.crud.StudioOverProductService;
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
    StudioOverProductService sDservice;
    @Resource
    DomainQueryService domainQueryService;

    /*
    *   查找店铺 by ServiceType
    * */
    @GetMapping("/types")
    public  Tip queryStudioByServiceType(Page page,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "name",required = false)String name){
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<Studio> studios = domainQueryService.queryStudioByServiceType(page,name);
        page.setRecords(studios);
        return SuccessTip.create(page);
    }
    /*
        *   查找店铺 by site
        * */
    @GetMapping("/sites")
    public  Tip queryStudioBySite(Page page,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "site",required = false)String site){
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<Studio> studios = domainQueryService.queryStudioBySite(page,site);
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
    public Tip showStudioModel(@PathVariable long id){
        StudioModel result = domainQueryService.showStudioModel(id);
        return SuccessTip.create(result);
    }
    @DeleteMapping("/{id}")
    public Tip deleteStudio(@PathVariable long id){
        Integer result = sDservice.deleteMaster(id);
        return SuccessTip.create(result);
    }

    /*
    *   CRUD  Products
    *
    * */

    @PostMapping("/{studioId}/products")
    public Tip addStudioProduct(@PathVariable long studioId,@Valid @RequestBody StudioProduct product){
        product.setStudioId(studioId);
        Integer result = sDservice.addSlaveItem(studioId,product);
        return SuccessTip.create(result);
    }
    @PutMapping("/{studioId}/products")
    public Tip updateStudioProduct(@PathVariable long studioId,@Valid@RequestBody StudioProduct product){
        Integer result = sDservice.updateSlaveItem(studioId,product);
        return SuccessTip.create(result);
    }
    @GetMapping("/{studioId}/products/{id}")
    public Tip showStudioProduct(@PathVariable long studioId,@PathVariable long id){
        StudioProduct result = sDservice.getSlaveItem(studioId,id);
        return SuccessTip.create(result);
    }
    @DeleteMapping("/{studioId}/products/{id}")
    public Tip deleteStudioProduct(@PathVariable long studioId,@PathVariable long id){
        Integer result = sDservice.removeSlaveItem(studioId,id);
        return SuccessTip.create(result);
    }
}
