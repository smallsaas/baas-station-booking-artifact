package com.jfeat.am.module.booking.services.service.path;

import com.jfeat.am.module.booking.api.bean.Ids;
import com.jfeat.am.module.booking.services.persistence.model.StudioService;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */
public interface PathService {
    public Integer addStudioService(StudioService studioService, List<Long> ids);
}
