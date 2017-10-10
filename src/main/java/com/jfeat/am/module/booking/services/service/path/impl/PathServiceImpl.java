package com.jfeat.am.module.booking.services.service.path.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.booking.services.persistence.mapper.ProductsPhotosMapper;
import com.jfeat.am.module.booking.services.persistence.mapper.StudioServiceMapper;
import com.jfeat.am.module.booking.services.persistence.mapper.StudiosPhotosMapper;
import com.jfeat.am.module.booking.services.persistence.model.ProductsPhotos;
import com.jfeat.am.module.booking.services.persistence.model.StudioService;
import com.jfeat.am.module.booking.services.persistence.model.StudiosPhotos;
import com.jfeat.am.module.booking.services.service.path.PathService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */
@Service
public class PathServiceImpl implements PathService {
    @Resource
    StudioServiceMapper studioServiceMapper;
    @Resource
    StudiosPhotosMapper studiosPhotosMapper;
    @Resource
    ProductsPhotosMapper productsPhotosMapper;

    public boolean addStudioService(Long studioId, List<Long> ids) {
        for (Long id : ids) {
            StudioService studioService = new StudioService();
            studioService.setStudioId(studioId);
            studioService.setTypeId(id);
            studioServiceMapper.insert(studioService);
        }
        return true;
    }

    public boolean addStudioPhotos(Long studioId, List<String> urls) {
        List<StudiosPhotos> photos = studiosPhotosMapper.selectList(new EntityWrapper<StudiosPhotos>().eq("studio_id",studioId));
        if (photos != null || photos.size() != 0) {
            studiosPhotosMapper.delete(new EntityWrapper<StudiosPhotos>().eq("studio_id", studioId));
        }
        for (String url : urls) {
            StudiosPhotos studiosPhotos = new StudiosPhotos();
            studiosPhotos.setStudioId(studioId);
            studiosPhotos.setPhoto(url);
            studiosPhotosMapper.insert(studiosPhotos);
        }
        return true;
    }

    /*
*   add ProductPhotos
* */
    public boolean addProductPhotos(Long productId, List<String> urls) {
        List<ProductsPhotos> photos = productsPhotosMapper.selectList(new EntityWrapper<ProductsPhotos>().eq("product_id", productId));
        if (photos != null || photos.size() != 0) {
            productsPhotosMapper.delete(new EntityWrapper<ProductsPhotos>().eq("product_id", productId));
        }
        for (String url : urls) {
            ProductsPhotos productsPhotos = new ProductsPhotos();
            productsPhotos.setProductId(productId);
            productsPhotos.setPhoto(url);
            productsPhotosMapper.insert(productsPhotos);
        }

        return true;
    }
}
