package com.xiaoyu.maoyao.dao;

import com.xiaoyu.maoyao.user.UserAuth;

public interface UserAuthsDao {
    UserAuth getUserAuthsByUserNameAndLoginType(String userName, Integer loginType);

    UserAuth createUserAuth(UserAuth userAuth);
}
