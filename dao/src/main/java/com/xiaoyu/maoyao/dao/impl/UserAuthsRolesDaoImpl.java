package com.xiaoyu.maoyao.dao.impl;

import com.xiaoyu.maoyao.dao.UserAuthsRolesDao;
import com.xiaoyu.maoyao.repository.UserAuthsRolesRepository;
import com.xiaoyu.maoyao.user.UserAuthsRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserAuthsRolesDaoImpl implements UserAuthsRolesDao {

    @Autowired
    UserAuthsRolesRepository userAuthsRolesRepository;

    @Override
    public List<UserAuthsRoles> createUserAuthsRolesByBatch(List<UserAuthsRoles> userAuthsRolesList) {
        Date now=new Date();
        return userAuthsRolesList.stream().map(userAuthsRoles -> {
            if (userAuthsRoles.getCreateTime()==null)
                userAuthsRoles.setCreateTime(now);
            if (userAuthsRoles.getUpdateTime()==null)
                userAuthsRoles.setUpdateTime(now);
            return userAuthsRolesRepository.save(userAuthsRoles);
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserAuthsRoles> getUserAuthsRolesListByUserAuthsId(Long id) {
        return userAuthsRolesRepository.findAllByUserAuthsId(id);
    }
}
