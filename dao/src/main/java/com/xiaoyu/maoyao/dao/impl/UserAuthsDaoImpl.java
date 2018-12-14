package com.xiaoyu.maoyao.dao.impl;

import com.xiaoyu.maoyao.dao.UserAuthsDao;
import com.xiaoyu.maoyao.repository.UserAuthRepository;
import com.xiaoyu.maoyao.user.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class UserAuthsDaoImpl implements UserAuthsDao {

    @Autowired
    UserAuthRepository userAuthRepository;


    @Override
    public UserAuth getUserAuthsByUserNameAndLoginType(String username, Integer loginType) {
        return userAuthRepository.findFirstByUsernameAndLoginType(username,loginType);
    }

    @Override
    public UserAuth createUserAuth(UserAuth userAuth) {
        Date now=new Date();
        if (userAuth.getCreateTime()==null)
            userAuth.setCreateTime(now);
        if (userAuth.getUpdateTime()==null)
            userAuth.setUpdateTime(now);
        return userAuthRepository.save(userAuth);
    }
}
