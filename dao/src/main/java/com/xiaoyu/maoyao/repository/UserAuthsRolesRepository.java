package com.xiaoyu.maoyao.repository;

import com.xiaoyu.maoyao.user.UserAuthsRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAuthsRolesRepository extends JpaRepository<UserAuthsRoles,Long> {

    List<UserAuthsRoles> findAllByUserAuthsId(Long id);
}
