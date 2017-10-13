package com.jfeat.am.module.booking.services.domain.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.booking.services.persistence.model.StudioCollect;
import com.jfeat.am.module.booking.services.persistence.model.StudioProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/27.
 */
public interface StudioProductDao {
        List<StudioProduct>  queryProductByAttribute(Page<StudioProduct> page,
                                                     @Param("attribute") String attribute);

        List<StudioProduct>  productStickList(Page<StudioProduct> page,@Param("city")String city);

        List<StudioProduct> queryProductByName(Page<StudioProduct> page,
                                               @Param("name") String name);
}
