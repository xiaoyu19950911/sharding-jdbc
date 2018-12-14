package com.xiaoyu.maoyao.dao;

import com.xiaoyu.maoyao.user.UserAuthsRoles;

import java.util.List;

public interface UserAuthsRolesDao {
    List<UserAuthsRoles> createUserAuthsRolesByBatch(List<UserAuthsRoles> userAuthsRolesList);

    List<UserAuthsRoles> getUserAuthsRolesListByUserAuthsId(Long id);
}
