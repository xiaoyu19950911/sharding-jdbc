package com.xiaoyu.maoyao.dao;

import com.xiaoyu.maoyao.user.Role;

import java.util.List;

public interface RoleDao {
    Role getFirstRoleByName(String name);

    List<Role> getRoleListByRoleIdList(List<Long> idList);
}
