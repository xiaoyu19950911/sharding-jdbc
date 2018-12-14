package com.xiaoyu.maoyao.security.auth;

import com.xiaoyu.maoyao.user.UserAuth;

import java.util.Date;

public class AuthFactory {

    public static UserAuth createUserAuths(AccountRegisteredRequest request) {
        return UserAuth.builder().username(request.getUsername()).password(request.getPassword()).loginType(1).updateTime(new Date()).createTime(new Date()).build();
    }
}
