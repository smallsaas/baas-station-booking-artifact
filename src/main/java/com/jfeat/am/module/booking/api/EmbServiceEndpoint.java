package com.jfeat.am.module.booking.api;

import com.jfeat.am.module.booking.services.domain.model.StudioServiceModel;
import com.jfeat.am.module.booking.services.domain.service.DomainQueryTypeService;
import com.jfeat.am.module.booking.services.persistence.mapper.StudioServiceMapper;
import com.jfeat.am.module.booking.services.persistence.model.Customer;
import com.jfeat.am.module.booking.services.persistence.model.StudioService;
import com.jfeat.am.module.booking.services.service.crud.EmbServiceService;
import com.jfeat.am.module.booking.services.service.crud.ServiceTypeService;
import com.jfeat.am.module.booking.services.persistence.model.ServiceType;

import com.jfeat.am.module.booking.services.service.path.PathService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;

/**
 * Created by Administrator on 2017/9/15.
 */
@RequestMapping("/api/emb/services")
@RestController
@Api(value = "/api/emb/services")
public class EmbServiceEndpoint{
    @Resource
    EmbServiceService embServiceService;
    @Resource
    ServiceTypeService typeService;
    @Resource
    DomainQueryTypeService queryTypeService;
    @Resource
    PathService pathService;
    @Resource
    StudioServiceMapper studioServiceMapper;

    /*
    *   Test
    * */
    @PostMapping("/bulk/add")
    @ApiOperation(value = "根据StudioServiceModel实体类进行添加操作",response = Integer.class)
    @ApiParam(name = "model",value = "工作室服务模型类")
    public Tip addStudioServiceType(@RequestBody StudioServiceModel model){
        Integer result = pathService.addStudioService(model.getStudioId(), model.getIds());
        return SuccessTip.create(result);
    }

    /*
  *   CRUD about Service
  * */
    @GetMapping("/lists")
    @ApiOperation(value = "查找所有服务记录并返回",response = List.class)
    public Tip showAllService(){
        List<StudioService> typeList = queryTypeService.allService();
        return SuccessTip.create(typeList);
    }

    @PostMapping
    @ApiOperation(value = "根据StudioService实体类创建服务记录",response = Integer.class)
    @ApiParam(name = "service",value = "工作室服务实体类")
    public Tip createEmbService(@Valid @RequestBody StudioService service){
        Integer result = embServiceService.createMaster(service);
        return SuccessTip.create(result);
    }

    @PutMapping
    @ApiOperation(value = "根据StudioService实体类更新服务记录",response = Integer.class)
    @ApiParam(name = "service",value = "工作室服务实体类")
    public Tip updateEmbService(@Valid@RequestBody StudioService service){
        Integer result = embServiceService.updateMaster(service);
        return SuccessTip.create(result);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据提供的服务ID进行相关记录的查找操作",response = Integer.class)
    @ApiParam(name = "id",value = "工作室服务ID")
    public Tip showEmbService(@PathVariable long id){
        StudioService result = embServiceService.retrieveMaster(id);
        return SuccessTip.create(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据提供的服务ID进行相关记录的删除操作",response = Integer.class)
    @ApiParam(name = "id",value = "工作室服务ID")
    public Tip deleteEmbService(@PathVariable long id){
        Integer result = embServiceService.deleteMaster(id);
        return SuccessTip.create(result);
    }


    @GetMapping("/types/lists")
    @ApiOperation(value = "查询所有服务类别并返回",response = List.class)
    public Tip showAllType(){
        List<ServiceType> typeList = queryTypeService.allServiceType();
        return SuccessTip.create(typeList);
    }

    @PostMapping("/types")
    @ApiOperation(value = "根据提供的ServiceType实体类创建相应的记录",response = Integer.class)
    @ApiParam(name = "type",value = "服务类别")
    public Tip createType(@Valid @RequestBody ServiceType type){
        Integer result = typeService.createMaster(type);
        return SuccessTip.create(result);
    }

    @PutMapping("/types")
    @ApiOperation(value = "根据提供的ServiceType实体类更新相应的记录",response = Integer.class)
    @ApiParam(name = "type",value = "服务类别")
    public Tip updateType(@Valid@RequestBody ServiceType type){
        Integer result = typeService.updateMaster(type);
        return SuccessTip.create(result);
    }

    @GetMapping("/types/{id}")
    @ApiOperation(value = "根据提供的服务类别ID显示详细记录",response = ServiceType.class)
    @ApiParam(name = "id",value = "服务类别ID")
    public Tip showType(@PathVariable long id){
        ServiceType result = typeService.retrieveMaster(id);
        return SuccessTip.create(result);
    }

    @DeleteMapping("/types/{id}")
    @ApiOperation(value = "根据提供的服务类别ID删除详细记录",response = Integer.class)
    @ApiParam(name = "id",value = "服务类别ID")
    public Tip deleteType(@PathVariable long id){
            return SuccessTip.create(pathService.deleteTypes(id));
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
