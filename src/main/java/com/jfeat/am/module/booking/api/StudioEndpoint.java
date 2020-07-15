package com.jfeat.am.module.booking.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.jfeat.am.common.annotation.Permission;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.booking.api.bean.Ids;
import com.jfeat.am.module.booking.services.domain.definition.AdminPermission;
import com.jfeat.am.module.booking.services.domain.definition.StudioStick;
import com.jfeat.am.module.booking.services.domain.model.ProductPhotosModel;
import com.jfeat.am.module.booking.services.domain.model.StudioModel;
import com.jfeat.am.module.booking.services.domain.model.StudioPhotosModel;
import com.jfeat.am.module.booking.services.domain.service.DomainQueryService;
import com.jfeat.am.module.booking.services.domain.service.PathPhotoService;
import com.jfeat.am.module.booking.services.persistence.mapper.StudioProductMapper;
import com.jfeat.am.module.booking.services.persistence.model.*;
import com.jfeat.am.module.booking.services.service.crud.CustomerService;
import com.jfeat.am.module.booking.services.service.crud.StudioOverProductService;

import com.jfeat.am.module.booking.services.service.crud.impl.StudioOverProductServiceImpl;
import com.jfeat.am.module.booking.services.service.path.PathService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;


/**
 * Created by J4cob on 2017/9/14.
 */
@RequestMapping("/api/studios")
@RestController
@Api(value = "/api/studios")
public class StudioEndpoint{
    @Resource
    StudioOverProductServiceImpl sDservice;
    @Resource
    DomainQueryService domainQueryService;
    @Resource
    CustomerService customerService;
    @Resource
    StudioProductMapper studioProductMapper;
    @Resource
    PathService pathService;

    @Resource
    PathPhotoService pathPhotoService;

    /*
    *   getAllStudio
    *
    * */
 /*   @GetMapping("/test/{id}")
    public Tip queryCollect(@PathVariable long id){
        long userId = JWTKit.getUserId(getHttpServletRequest());
        Customer customer = pathService.queryCustomerByUserId(userId);
        return SuccessTip.create(pathService.queryStudioCollect(id,customer.getId()));

    }*/

    @GetMapping("/all")
    @ApiOperation(value = "根据请求参数pageNum与pageSize进行分页查询",response = Page.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前请求页"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录条数")
    })
    public Tip allStudio(Page page,
                         @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<Map<String,Object>> studio = domainQueryService.allStudio(page);
        page.setRecords(studio);
        return SuccessTip.create(page);
    }

    /*
    *   queryStudioByName
    * */
    @GetMapping
    @ApiOperation(value = "根据请求参数name配合pageNum与pageSize进行分页条件查询",response = Page.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前请求页"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录条数"),
            @ApiImplicitParam(name = "name",value = "工作室名称")
    })
    public Tip queryStudioByName(Page page,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(name = "name", required = false) String name) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<Studio> studio = domainQueryService.queryStudioByName(page, name);
        page.setRecords(studio);
        return SuccessTip.create(page);
    }

    /*
    *   无精选店铺
    * */
    @GetMapping("/stick")
    @ApiOperation(value = "根据请求参数name配合pageNum与pageSize进行分页条件查询",response = List.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前请求页"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录条数"),
            @ApiImplicitParam(name = "city",value = "工作室名称")
    })
    public Tip queryStudioByStick(Page page,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                  @RequestParam(name = "city", required = false) String city) {

        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<Studio> studios = domainQueryService.queryStudioByStick(page, city);
        page.setRecords(studios);
        return SuccessTip.create(studios);
    }


    @GetMapping("/citys")
    @ApiOperation(value = "获取所有的工作室所在城市列表并返回",response = List.class)
    public Tip queryCity() {
        List<Studio> citys = domainQueryService.queryCity();
        return SuccessTip.create(citys);
    }

    /*
    *   查找店铺 by ServiceType
    * */
    @GetMapping("/types")
    @ApiOperation(value = "根据请求参数typeName,city配合pageNum与pageSize进行分页条件查询",response = Page.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前请求页"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录条数"),
            @ApiImplicitParam(name = "typeName",value = "类别名称"),
            @ApiImplicitParam(name = "city",value = "城市")
    })
    public Tip queryStudioByServiceType(Page page,
                                        @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                        @RequestParam(name = "typeName", required = true) String typeName,
                                        @RequestParam(name = "city", required = true) String city) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        List<Map<String, Object>> studios =
                domainQueryService.queryStudioByTypeName(page, typeName,city );
        page.setRecords(studios);

        return SuccessTip.create(page);
    }

    @GetMapping("/options")
    @Permission(AdminPermission.QUERY)
    @ApiOperation(value = "根据请求参数tname,name,city,stick配合pageNum与pageSize进行分页条件查询",response = Page.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前请求页"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录条数"),
            @ApiImplicitParam(name = "tname",value = "名称"),
            @ApiImplicitParam(name = "name",value = "名称"),
            @ApiImplicitParam(name = "city",value = "城市"),
            @ApiImplicitParam(name = "stick",value = "精选")
    })
    public Tip queryStudioByServiceType(Page page,
                                        @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                        @RequestParam(name = "tname", required = false) String tname,
                                        @RequestParam(name = "name", required = false) String name,
                                        @RequestParam(name = "city", required = false) String city,
                                        @RequestParam(name = "stick", required = false) String stick) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<Map<String, Object>> studios =
                domainQueryService.queryStudioByMultiple(page, tname,name,city ,stick);
        page.setRecords(studios);

        return SuccessTip.create(page);
    }

    /*
        *   查找店铺 by site
        * */
    @GetMapping("/sites")
    @ApiOperation(value = "根据请求参数city,typeName,name,latitude,longitude配合pageNum与pageSize进行分页条件查询",response = Page.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前请求页"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录条数"),
            @ApiImplicitParam(name = "city",value = "城市"),
            @ApiImplicitParam(name = "typeName",value = "类别名称"),
            @ApiImplicitParam(name = "name",value = "工作室名称"),
            @ApiImplicitParam(name = "latitude",value = "纬度"),
            @ApiImplicitParam(name = "longitude",value = "经度")
    })
    public Tip queryStudioBySite(Page page,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(name = "city", required = false) String city,
                                 @RequestParam(name = "typeName", required = false) String typeName,
                                 @RequestParam(name = "name", required = false) String name,
                                 @RequestParam(name = "latitude",required = true) String  lat,
                                 @RequestParam(name = "longitude",required = true)String lng) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        long userId = JWTKit.getUserId();
        /*BigDecimal latitude = null;
        BigDecimal longitude = null;
        Customer customer = pathService.queryCustomerByUserId(userId);
        if (customer != null) {
            latitude = customer.getLatitude();
            longitude = customer.getLongitude();
        }*/
        BigDecimal latitude = new BigDecimal(lat);
        BigDecimal longitude = new BigDecimal(lng);

        Customer customer = pathService.queryCustomerByUserId(userId);
        if (customer != null) {
            customer.setLatitude(latitude);
            customer.setLongitude(longitude);
            customerService.updateMaster(customer);
        }
        List<Map<String, Object>> studios = domainQueryService.queryStudioBySite(page, city, typeName,name, latitude, longitude);
        page.setRecords(studios);

        return SuccessTip.create(page);

    }

    /*
    *   CRUD about Studio
    * */
    @PostMapping
    @Permission(AdminPermission.CREATE)
    @ApiOperation(value = "根据Studio实体类创建工作室记录",response = Integer.class)
    @ApiParam(name = "studio",value = "工作室实体类")
    public Tip createStudio(@Valid @RequestBody Studio studio) {
        Integer result = sDservice.createMaster(studio);
        return SuccessTip.create(result);
    }

    @PutMapping
    @Permission(AdminPermission.EDIT)
    @ApiOperation(value = "根据Studio实体类更新工作室记录",response = Integer.class)
    @ApiParam(name = "studio",value = "工作室实体类")
    public Tip updateStudio(@Valid @RequestBody Studio studio) {
        Integer result = sDservice.updateMaster(studio);
        return SuccessTip.create(result);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据提供的工作室ID进行相关记录的查找操作",response = Integer.class)
    @ApiParam(name = "id",value = "工作室ID")
    public Tip showStudioModel(@PathVariable long id) {
        long userId = JWTKit.getUserId();
        Customer customer = pathService.queryCustomerByUserId(userId);
        StudioModel result = domainQueryService.showStudioModel(id);
        if(customer != null){
            result.setCollected(pathService.queryStudioCollect(id,customer.getId()));
        }
        return SuccessTip.create(result);
    }


    @DeleteMapping("/{id}")
//    @Permission(AdminPermission.DELETE)
    @ApiOperation(value = "根据提供的工作室ID进行相关记录的删除操作",response = Integer.class)
    @ApiParam(name = "id",value = "工作室ID")
    public Tip deleteStudio(@PathVariable long id) {

            return SuccessTip.create(sDservice.deleteMaster(id));

    }

    /*
    *   add or delete  photo for Studio
    * */
    @PostMapping("/{studioId}/photos")
    @ApiOperation(value = "根据提供的工作室ID和StudiosPhotos实体类进行相关记录的查找操作",response = Integer.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "工作室ID"),
            @ApiImplicitParam(name = "studiosPhotos",value = "工作室照片实体类")
    })
    public Tip addStudioPhotos(@PathVariable long studioId, @RequestBody StudiosPhotos studiosPhotos) {
        return SuccessTip.create(pathPhotoService.addStudioPhotos(studioId, studiosPhotos));
    }

    @PostMapping("/bulk/add")
    @ApiOperation(value = "根据提供的StudioPhotosModel模型实体类进行相关记录的添加操作",response = Integer.class)
    @ApiParam(name = "model",value = "工作室照片模型实体类")
    public Tip addStudioPhotos(@RequestBody StudioPhotosModel model) {
        Integer result = pathService.addStudioPhotos(model.getStudioId(), model.getUrls());
        return SuccessTip.create(result);
    }


    @DeleteMapping(("/{studioId}/photos/{id}"))
    @ApiOperation(value = "根据工作室id和工作室照片记录 id进行相关记录的删除操作",response = Integer.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studioId",value = "工作室ID"),
            @ApiImplicitParam(name = "id",value = "工作室照片记录ID")
    })
    public Tip deleteStudioPhotos(@PathVariable long studioId, @PathVariable long id) {
        return SuccessTip.create(pathPhotoService.deleteStudioPhotos(studioId, id));
    }

    @PostMapping("/{studioId}/photos/bulk/delete")
    @ApiOperation(value = "根据工作室id和id集合进行遍历删除记录",response = Integer.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studioId",value = "工作室ID"),
            @ApiImplicitParam(name = "ids",value = "ID集合")
    })
    public Tip bulkDeleteStudioPhotos(@PathVariable long studioId, @RequestBody Ids ids) {
        return SuccessTip.create(pathPhotoService.bulkDeleteStudioPhotos(studioId, ids.getIds()));
    }

    /*
    *   CRUD  Products
    *
    * */

    @PostMapping("/{studioId}/products")
    @ApiOperation(value = "根据工作室ID添加产品记录",response = Integer.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studioId",value = "工作室ID"),
            @ApiImplicitParam(name = "product",value = "产品实体类")
    })
    public Tip addStudioProduct(@PathVariable long studioId, @Valid @RequestBody StudioProduct product) {
        product.setStudioId(studioId);
        Integer result = sDservice.addSlaveItem(studioId, product);
        return SuccessTip.create(result);
    }

    @PutMapping("/{studioId}/products")
    @ApiOperation(value = "根据工作室ID更新产品记录",response = Integer.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studioId",value = "工作室ID"),
            @ApiImplicitParam(name = "product",value = "产品实体类")
    })
    public Tip updateStudioProduct(@PathVariable long studioId, @Valid @RequestBody StudioProduct product) {
        Integer result = sDservice.updateSlaveItem(studioId, product);
        return SuccessTip.create(result);
    }

    @GetMapping("/{studioId}/products/{id}")
    @ApiOperation(value = "根据工作室id和产品id进行相关记录的查找操作",response = Integer.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studioId",value = "工作室ID"),
            @ApiImplicitParam(name = "id",value = "产品ID")
    })
    public Tip showStudioProductModel(@PathVariable long studioId, @PathVariable long id) {
        return SuccessTip.create(domainQueryService.showStudioProductModel(studioId, id));

    }

    @GetMapping("/{studioId}/products/attributes")
    @ApiOperation(value = "根据请求参数studioId,attribute配合pageNum与pageSize进行分页条件查询",response = Page.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前请求页"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录条数"),
            @ApiImplicitParam(name = "studioId",value = "工作室ID"),
            @ApiImplicitParam(name = "attribute",value = "精选/次卡/团购/")
    })
    public Tip queryProductByAttribute(Page page,
                                       @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                       @PathVariable long studioId,
                                       @RequestParam String attribute) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<StudioProduct> products = domainQueryService.queryStudioProduct(page, studioId, attribute);
        page.setRecords(products);
        return SuccessTip.create(page);
    }

    @DeleteMapping("/{studioId}/products/{id}")
    @ApiOperation(value = "根据提供的工作室ID和产品ID进行相关记录的删除操作",response = Integer.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前请求页"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录条数"),
            @ApiImplicitParam(name = "studioId",value = "工作室ID"),
            @ApiImplicitParam(name = "id",value = "产品id")
    })
    public Tip deleteStudioProduct(@PathVariable long studioId, @PathVariable long id) {
        Integer result = sDservice.removeSlaveItem(studioId, id);
        return SuccessTip.create(result);
    }

    @PostMapping("/products/bulk/add")
    @ApiOperation(value = "根据提供的ProductPhotosModel实体类进行相关记录的添加操作",response = Integer.class)
    @ApiParam(name = "model",value = "产品照片模型类")
    public Tip addProductPhotos(@RequestBody ProductPhotosModel model) {
        Integer result = pathService.addProductPhotos(model.getProductId(), model.getUrls());
        return SuccessTip.create(result);
    }

    /*
    *   all of products
    * */

    @GetMapping("/products/lists")
    @ApiOperation(value = "根据分页参数查询工作室产品",response = Page.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前请求页"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录条数")
    })
    public Tip studioProductList(Page page,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<StudioProduct> productList = domainQueryService.studioProductList(page);
        page.setRecords(productList);
        return SuccessTip.create(page);
    }

    /*
    *   精选产品列·表
    * */
    @GetMapping("/products/stick")
    @ApiOperation(value = "根据工作室所在城市与分页参数查询工作室产品",response = Page.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前请求页"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录条数"),
            @ApiImplicitParam(name = "city", value = "工作室所在城市")
    })
    public Tip productStickList(Page page,
                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                @RequestParam(name = "city", required = true) String city
    ) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<StudioProduct> products = domainQueryService.productStickList(page,city);
        page.setRecords(products);
        return SuccessTip.create(page);
    }

    /*
    *   queryProductByName
    * */
    @GetMapping("/products")
    @ApiOperation(value = "根据产品名称与分页参数查询工作室产品",response = Page.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前请求页"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录条数"),
            @ApiImplicitParam(name = "name", value = "产品名称")
    })
    public Tip productList(Page page,
                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                @RequestParam(name = "name", required = false) String name
    ) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<StudioProduct> products = domainQueryService.queryProductByName(page, name);
        page.setRecords(products);
        return SuccessTip.create(page);
    }

    /*
   *   add or delete  photo for Product
   * */
    @PostMapping("/{studioId}/product/{productId}/photos")
    @ApiOperation(value = "根据工作室ID、产品ID、产品照片模型类与分页参数添加产品照片记录",response = Page.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studioId", value = "工作室id"),
            @ApiImplicitParam(name = "productId", value = "产品id"),
            @ApiImplicitParam(name = "productsPhotos", value = "产品照片实体类")
    })
    public Tip addProductPhotos(@PathVariable long studioId, @PathVariable long productId, @RequestBody ProductsPhotos productsPhotos) {
        return SuccessTip.create(pathPhotoService.addProductPhotos(studioId, productId, productsPhotos));
    }

    @DeleteMapping(("/{studioId}/product/{productId}/photos/{id}"))
    @ApiOperation(value = "根据工作室ID、产品ID、产品照片ID删除产品照片记录",response = Page.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studioId", value = "工作室id"),
            @ApiImplicitParam(name = "productId", value = "产品id"),
            @ApiImplicitParam(name = "id", value = "产品照片id")
    })
    public Tip deleteProductPhotos(@PathVariable long studioId, @PathVariable long productId, @PathVariable long id) {
        return SuccessTip.create(pathPhotoService.deleteProductPhotos(studioId, productId, id));
    }

    @PostMapping("/{studioId}/product/{productId}/photos/bulk/delete")
    @ApiOperation(value = "根据工作室ID、产品ID、产品照片ID集合批量删除产品照片记录",response = Page.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studioId", value = "工作室id"),
            @ApiImplicitParam(name = "productId", value = "产品id"),
            @ApiImplicitParam(name = "ids", value = "产品照片id集合")
    })
    public Tip bulkDeleteProductPhotos(@PathVariable long studioId, @PathVariable long productId, @RequestBody Ids ids) {
        return SuccessTip.create(pathPhotoService.bulkDeleteProductPhotos(studioId, productId, ids.getIds()));
    }

}
