package com.xiaoyu.maoyao.dao.impl;

import com.xiaoyu.maoyao.dao.RoleDao;
import com.xiaoyu.maoyao.repository.RoleRepository;
import com.xiaoyu.maoyao.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role getFirstRoleByName(String name) {
        return roleRepository.findFirstByName(name);
    }

    @Override
    public List<Role> getRoleListByRoleIdList(List<Long> idList) {
        return idList.stream().map(roleRepository::getOne).collect(Collectors.toList());
    }
}
