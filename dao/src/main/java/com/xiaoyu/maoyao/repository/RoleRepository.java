package com.xiaoyu.maoyao.repository;

import com.xiaoyu.maoyao.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findFirstByName(String name);
}
