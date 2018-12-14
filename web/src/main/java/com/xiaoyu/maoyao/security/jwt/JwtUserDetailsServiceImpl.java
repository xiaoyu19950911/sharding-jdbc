package com.xiaoyu.maoyao.security.jwt;

import com.xiaoyu.common.enums.ProgramEnums;
import com.xiaoyu.maoyao.dao.RoleDao;
import com.xiaoyu.maoyao.dao.UserAuthsDao;
import com.xiaoyu.maoyao.dao.UserAuthsRolesDao;
import com.xiaoyu.maoyao.user.Role;
import com.xiaoyu.maoyao.user.UserAuth;
import com.xiaoyu.maoyao.user.UserAuthsRoles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserAuthsDao userAuthsDao;

    @Autowired
    UserAuthsRolesDao userAuthsRolesDao;

    @Autowired
    RoleDao roleDao;

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth userAuth = userAuthsDao.getUserAuthsByUserNameAndLoginType(username, ProgramEnums.LOGIN_ACCOUNT.getCode());
        if (userAuth == null)
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        log.debug("当前用户id为：{}", userAuth.getId());
        List<Long> roleIdList = userAuthsRolesDao.getUserAuthsRolesListByUserAuthsId(userAuth.getId()).stream().map(UserAuthsRoles::getRolesId).collect(Collectors.toList());
        List<String> roleNameList = roleDao.getRoleListByRoleIdList(roleIdList).stream().map(Role::getName).collect(Collectors.toList());
        return JwtUserFactory.create(userAuth,roleNameList);
    }
}
