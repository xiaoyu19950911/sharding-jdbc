package com.xiaoyu.maoyao.security.util;

import com.xiaoyu.maoyao.repository.UserRepository;
import com.xiaoyu.maoyao.security.jwt.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: rry
 * @description: token相关转换
 * @author: XiaoYu
 * @create: 2018-03-20 15:39
 **/
@Component
@Slf4j
public class TokenUtil {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String Header;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    HttpServletRequest request;

    /*public Integer getUserId(){
        String token = request.getHeader(Header).substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        Integer userId=Integer.parseInt(user.getId());
        return userId;
    }*/

    public Integer getUserId(){
        String token = request.getHeader(Header).substring(tokenHead.length());
        return jwtTokenUtil.getUserIdFromToken(token);
    }

    public String getUserName() {
        String token = request.getHeader(Header).substring(tokenHead.length());
        String username=jwtTokenUtil.getUsernameFromToken(token);
        log.debug("当前用户的用户名为：{}",username);
        return username;
    }




}
