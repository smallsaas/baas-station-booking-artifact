package com.jfeat.am.module.booking.services.domain.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.booking.services.domain.model.StudioCollectModel;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Administrator on 2017/10/18.
 */
public interface CollectDao {

    List<StudioCollectModel>  queryUserCollects(Page<StudioCollectModel> page, @Param("customerId") long customerId);
}
