package com.xiaoyu.maoyao.repository;

import com.xiaoyu.maoyao.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
