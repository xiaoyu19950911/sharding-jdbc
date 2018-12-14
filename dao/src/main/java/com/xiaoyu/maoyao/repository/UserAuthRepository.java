package com.xiaoyu.maoyao.repository;

import com.xiaoyu.maoyao.user.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth,Integer> {
    UserAuth findFirstByUsername(String username);

    UserAuth findFirstByUsernameAndLoginType(String username,Integer loginType);
}
