package com.jfeat.am.module.booking.services.service.path;

import com.jfeat.am.module.booking.api.bean.Ids;
import com.jfeat.am.module.booking.services.persistence.model.StudioService;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */
public interface PathService {
    boolean addStudioService(Long studioId, List<Long> ids);
    boolean addStudioPhotos(Long studioId, List<String> urls);


    /*
    *   add ProductPhotos
    * */
    public boolean addProductPhotos(Long productId, List<String> urls);
}
