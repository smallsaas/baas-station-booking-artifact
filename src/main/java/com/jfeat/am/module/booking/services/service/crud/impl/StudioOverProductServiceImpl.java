package com.jfeat.am.module.booking.services.service.crud.impl;



import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.booking.services.domain.model.StudioModel;
import com.jfeat.am.module.booking.services.persistence.mapper.StudioMapper;
import com.jfeat.am.module.booking.services.persistence.mapper.StudioProductMapper;
import com.jfeat.am.module.booking.services.persistence.model.Studio;
import com.jfeat.am.module.booking.services.persistence.model.StudioProduct;
import com.jfeat.am.module.booking.services.service.crud.StudioOverProductService;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.CRUDFilter;
import com.jfeat.crud.plus.CRUDObject;
import com.jfeat.crud.plus.model.IdVersions;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by J4cob on 2017/9/14.
 */
@Service
public class StudioOverProductServiceImpl implements StudioOverProductService {

    @Resource
    StudioMapper studioMapper;
    @Resource
    StudioProductMapper productMapper;


    /*
    *   Test
    * */
    /*public Integer addTest(Studio studio){
        Integer studios = studioMapper.insert(studio);
        return studios;
    }*/


    public void appendSlaveData(StudioModel studioModel) {

    }

    public void handleSlaveData(StudioModel studioModel) {

    }

    public CRUDObject<StudioModel> retrieveMaster(long l, CRUDFilter<Studio> crudFilter, Class<StudioModel> aClass) {
        return null;
    }

    public Integer updateMaster(StudioModel studioModel, boolean b, CRUDFilter<Studio> crudFilter) {
        return null;
    }

    @Override
    public Integer createMaster(Studio studio) {
        return studioMapper.insert(studio);
    }

    @Override
    public Integer createMaster(Studio studio, CRUDFilter<Studio> crudFilter) {
        return null;
    }

    @Override
    public Integer updateMaster(Studio studio) {
        return studioMapper.updateById(studio);
    }

    @Override
    public Integer updateMaster(Studio studio, boolean b) {
        return null;
    }

    @Override
    public Integer updateMaster(Studio studio, CRUDFilter<Studio> crudFilter) {
        return null;
    }

    @Override
    public Studio retrieveMaster(long id) {
        return studioMapper.selectById(id);
    }

    @Override
    public Integer deleteMaster(long id) {
        List<StudioProduct> studioProduct = productMapper.selectList(new EntityWrapper<StudioProduct>().eq("studio_id", id));
        if (studioProduct == null || studioProduct.size() == 0) {
            return studioMapper.deleteById(id);
        }else{
            throw new BusinessException(2000,"请先删除该店铺下面的所有店铺！");
        }

    }

    @Override
    public List<Studio> retrieveMasterList() {
        return null;
    }

    public List<Studio> queryMasterList(Map<String, Object> map) {
        return null;
    }

    public List<Studio> selectMasterList(String s, Object o) {
        return null;
    }

    @Override
    public Integer bulkDeleteMasterList(List<Long> list) {
        return null;
    }

    @Override
    public Integer bulkAppendMasterList(List<Studio> list) {
        return null;
    }

    @Override
    public Integer deleteMaster(long l, int i) {
        return null;
    }

    @Override
    public Integer deleteList(IdVersions idVersions) {
        return null;
    }


    public Integer addSlaveItem(long studioId, StudioProduct product) {
        product.setStudioId(studioId);
        return productMapper.insert(product);
    }

    public Integer updateSlaveItem(long id, StudioProduct product) {
        return productMapper.updateById(product);
    }

    public Integer removeSlaveItem(long masterId, long id) {
        return productMapper.deleteById(id);
    }

    public StudioProduct getSlaveItem(long masterId, long id) {
        return productMapper.selectById(id);
    }

    public List<StudioProduct> selectSlaveItemList(long l) {
        return null;
    }

    public Integer bulkRemoveSlaveItemList(long l, List<Long> list) {
        return productMapper.deleteBatchIds(list);
    }

}
