package com.xiaoyu.maoyao.service.impl;

import com.xiaoyu.common.result.Result;
import com.xiaoyu.common.util.ResultUtils;
import com.xiaoyu.maoyao.config.DataSourceProperty;
import com.xiaoyu.maoyao.user.User;
import com.xiaoyu.maoyao.dao.UserDao;
import com.xiaoyu.maoyao.request.UserCreateRequest;
import com.xiaoyu.maoyao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    DataSourceProperty dataSourceProperty;


    @Override
    public Result createUser(UserCreateRequest request) {
        User user=User.builder().nickName(request.getNickName()).introduction(request.getIntroduction()).build();
        userDao.createUser(user);
        return ResultUtils.success();
    }

    @Override
    public void test() {
        System.out.println(111);
    }
}
