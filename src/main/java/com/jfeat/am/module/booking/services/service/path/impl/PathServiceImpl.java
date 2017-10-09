package com.jfeat.am.module.booking.services.service.path.impl;

import com.jfeat.am.module.booking.services.persistence.mapper.StudioServiceMapper;
import com.jfeat.am.module.booking.services.persistence.mapper.StudiosPhotosMapper;
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
public class PathServiceImpl implements PathService{
    @Resource
    StudioServiceMapper studioServiceMapper;
    @Resource
    StudiosPhotosMapper studiosPhotosMapper;

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
        for (String url : urls) {
            StudiosPhotos studiosPhotos = new StudiosPhotos();
            studiosPhotos.setStudioId(studioId);
            studiosPhotos.setPhoto(url);
            studiosPhotosMapper.insert(studiosPhotos);
        }
        return true;
    }
}
