package com.xiaoyu.maoyao.security.jwt;

import com.xiaoyu.maoyao.user.UserAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JwtUserFactory {

    public JwtUserFactory() {

    }

    public static JwtUser create(UserAuth user,List<String> roleNameList) {
        return new JwtUser(
                user.getId().toString(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(roleNameList)
        );
    }

    /*private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }*/

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> roleNameList) {
        roleNameList.forEach(a-> log.debug("当前用户角色为"+a));
        return roleNameList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
