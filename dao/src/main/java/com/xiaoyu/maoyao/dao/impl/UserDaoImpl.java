package com.xiaoyu.maoyao.dao.impl;

import com.xiaoyu.maoyao.user.User;
import com.xiaoyu.maoyao.dao.UserDao;
import com.xiaoyu.maoyao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        Date now=new Date();
        if (user.getCreateTime()==null)
            user.setCreateTime(now);
        if (user.getUpdateTime()==null)
            user.setUpdateTime(now);
        return userRepository.save(user);
    }
}
