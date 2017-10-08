package com.jfeat.am.module.booking.api;

import com.baomidou.mybatisplus.plugins.Page;

import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.booking.api.bean.Ids;
import com.jfeat.am.module.booking.services.domain.definition.AdminPermission;
import com.jfeat.am.module.booking.services.domain.definition.StudioStick;
import com.jfeat.am.module.booking.services.domain.model.StudioModel;
import com.jfeat.am.module.booking.services.domain.service.DomainQueryService;
import com.jfeat.am.module.booking.services.domain.service.PathPhotoService;
import com.jfeat.am.module.booking.services.persistence.model.*;
import com.jfeat.am.module.booking.services.service.crud.CustomerService;
import com.jfeat.am.module.booking.services.service.crud.StudioOverProductService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * Created by J4cob on 2017/9/14.
 */
@RequestMapping("/api/studios")
@RestController
public class StudioEndpoint extends BaseController {
    @Resource
    StudioOverProductService sDservice;
    @Resource
    DomainQueryService domainQueryService;
    @Resource
    CustomerService customerService;

    @Resource
    PathPhotoService pathPhotoService;
    /*
    *   queryStudioByStick
    * */
    @GetMapping("/stick")
    public Tip queryStudioByStick(Page page,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                  @RequestParam(name = "city", required = false) String city){

        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<Studio> studios = domainQueryService.queryStudioByStick(page,city);
        page.setRecords(studios);
        return SuccessTip.create(studios);
    }

    /*
    *   queryCity
    * */
    @GetMapping("/citys")
    public Tip queryCity(){
        List<Studio> citys = domainQueryService.queryCity();
        return SuccessTip.create(citys);
    }


    /*
    *   查找店铺 by ServiceType
    * */
    @GetMapping("/options")
    public Tip queryStudioByServiceType(Page page,
                                        @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                        @RequestParam(name = "tname", required = false) String tname,
                                        @RequestParam(name = "name", required = false) String name) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        long userId = JWTKit.getUserId(getHttpServletRequest());
        Customer customer = customerService.retrieveMaster(userId);
        if (customer.getLatitude() == null && customer.getLongitude() == null) {
            customer.setLatitude(BigDecimal.valueOf(114.1238523));
            customer.setLongitude(BigDecimal.valueOf(25.1235203));
            List<Map<String, Object>> studios = domainQueryService.queryStudioByMultiple(page, tname,name, customer.getLatitude(), customer.getLongitude());
            page.setRecords(studios);
            return SuccessTip.create(page);
        }
        List<Map<String,Object>> studios = domainQueryService.queryStudioByMultiple(page, tname, name,customer.getLatitude(), customer.getLongitude());
        page.setRecords(studios);
        return SuccessTip.create(page);
    }

    /*
        *   查找店铺 by site
        * */
    @GetMapping("/sites")
    public Tip queryStudioBySite (Page page,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(name = "site", required = false) String site) {
         page.setCurrent(pageNum);
        page.setSize(pageSize);
        long userId = JWTKit.getUserId(getHttpServletRequest());
        Customer customer = customerService.retrieveMaster(userId);
        if (customer.getLatitude() == null && customer.getLongitude() == null) {
            customer.setLatitude(BigDecimal.valueOf(114.1238523));
            customer.setLongitude(BigDecimal.valueOf(25.1235203));
            List<Map<String, Object>> studios = domainQueryService.queryStudioBySite(page, site, customer.getLatitude(), customer.getLongitude());
            page.setRecords(studios);
            return SuccessTip.create(page);
        }
        List<Map<String, Object>> studios = domainQueryService.queryStudioBySite(page, site, customer.getLatitude(), customer.getLongitude());
        page.setRecords(studios);

        return SuccessTip.create(page);

    }
    /*
    *   CRUD about Studio
    * */
    @PostMapping
    @Permission(AdminPermission.CREATE)
    public Tip createStudio(@Valid @RequestBody Studio studio) {


        Integer result = sDservice.createMaster(studio);
        return SuccessTip.create(result);
    }

    @PutMapping
    @Permission(AdminPermission.EDIT)
    public Tip updateStudio(@Valid @RequestBody Studio studio) {
        Integer result = sDservice.updateMaster(studio);
        return SuccessTip.create(result);
    }

    @GetMapping("/{id}")
    public Tip showStudioModel(@PathVariable long id) {
        StudioModel result = domainQueryService.showStudioModel(id);
        return SuccessTip.create(result);
    }

    @DeleteMapping("/{id}")
    @Permission(AdminPermission.DELETE)
    public Tip deleteStudio(@PathVariable long id) {

        Integer result = sDservice.deleteMaster(id);
        return SuccessTip.create(result);
    }

    /*
    *   add or delete  photo for Studio
    * */
    @PostMapping("/{studioId}/photos")
    public Tip addStudioPhotos(@PathVariable long studioId, @RequestBody StudiosPhotos studiosPhotos) {
        return SuccessTip.create(pathPhotoService.addStudioPhotos(studioId, studiosPhotos));
    }

    @DeleteMapping(("/{studioId}/photos/{id}"))
    public Tip deleteStudioPhotos(@PathVariable long studioId, @PathVariable long id) {
        return SuccessTip.create(pathPhotoService.deleteStudioPhotos(studioId, id));
    }

    @PostMapping("/{studioId}/photos/bulk/delete")
    public Tip bulkDeleteStudioPhotos(@PathVariable long studioId, @RequestBody Ids ids) {
        return SuccessTip.create(pathPhotoService.bulkDeleteStudioPhotos(studioId, ids.getIds()));
    }

    /*
    *   CRUD  Products
    *
    * */

    @PostMapping("/{studioId}/products")
    public Tip addStudioProduct(@PathVariable long studioId, @Valid @RequestBody StudioProduct product) {
        product.setStudioId(studioId);
        Integer result = sDservice.addSlaveItem(studioId, product);
        return SuccessTip.create(result);
    }

    @PutMapping("/{studioId}/products")
    public Tip updateStudioProduct(@PathVariable long studioId, @Valid @RequestBody StudioProduct product) {
        Integer result = sDservice.updateSlaveItem(studioId, product);
        return SuccessTip.create(result);
    }

    @GetMapping("/{studioId}/products/{id}")
    public Tip showStudioProductModel(@PathVariable long studioId, @PathVariable long id) {

        return SuccessTip.create(domainQueryService.showStudioProductModel(studioId, id));

    }

    @GetMapping("/{studioId}/products/attributes")
    public Tip queryProductByAttribute(Page page,
                                       @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                       @PathVariable long studioId,
                                       @RequestParam String attribute){
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<StudioProduct>  products = domainQueryService.queryStudioProduct(page,studioId,attribute);
        page.setRecords(products);
        return SuccessTip.create(page);
    }
    @DeleteMapping("/{studioId}/products/{id}")
    public Tip deleteStudioProduct(@PathVariable long studioId, @PathVariable long id) {
        Integer result = sDservice.removeSlaveItem(studioId, id);
        return SuccessTip.create(result);
    }

    /*
    *   all of products
    * */

    @GetMapping("/products/lists")
    public Tip studioProductList() {
        return SuccessTip.create(domainQueryService.studioProductList());
    }

    /*
    *   精选产品列·表
    * */
    @GetMapping("/products/stick")
    public Tip  productStickList(Page page,
                                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
                                                ){
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<StudioProduct>  products = domainQueryService.productStickList(page);
        page.setRecords(products);
        return SuccessTip.create(page);
    }

    /*
    *   queryProductByName
    * */
    @GetMapping("/products")
    public Tip  productStickList(Page page,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(name = "name", required = false) String name
    ){
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<StudioProduct>  products = domainQueryService.queryProductByName(page,name);
        page.setRecords(products);
        return SuccessTip.create(page);
    }

    /*
   *   add or delete  photo for Product
   * */
    @PostMapping("/{studioId}/product/{productId}/photos")
    public Tip addProductPhotos(@PathVariable long studioId, @PathVariable long productId, @RequestBody ProductsPhotos productsPhotos) {
        return SuccessTip.create(pathPhotoService.addProductPhotos(studioId, productId, productsPhotos));
    }

    @DeleteMapping(("/{studioId}/product/{productId}/photos/{id}"))
    public Tip deleteProductPhotos(@PathVariable long studioId, @PathVariable long productId, @PathVariable long id) {
        return SuccessTip.create(pathPhotoService.deleteProductPhotos(studioId, productId, id));
    }

    @PostMapping("/{studioId}/product/{productId}/photos/bulk/delete")
    public Tip bulkDeleteProductPhotos(@PathVariable long studioId, @PathVariable long productId, @RequestBody Ids ids) {
        return SuccessTip.create(pathPhotoService.bulkDeleteProductPhotos(studioId, productId, ids.getIds()));
    }

}
