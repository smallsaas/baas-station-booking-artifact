package com.jfeat.am.module.booking.services.service.crud.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.booking.services.persistence.mapper.UserMapper;
import com.jfeat.am.module.booking.services.persistence.model.User;
import com.jfeat.am.module.booking.services.service.crud.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/14.
 */
@Service
public class UserServiceImpl extends CRUDServiceOnlyImpl<User> implements UserService{
    @Resource
    UserMapper userMapper;

    @Override
    protected BaseMapper<User> getMasterMapper() {
        return userMapper;
    }
}
