package com.xiaoyu.maoyao.service;

import com.xiaoyu.common.result.Result;
import com.xiaoyu.maoyao.request.UserCreateRequest;

public interface UserService {
    Result createUser(UserCreateRequest request);

    void test();

}
