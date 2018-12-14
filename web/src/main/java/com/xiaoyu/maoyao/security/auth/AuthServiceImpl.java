package com.xiaoyu.maoyao.security.auth;

import com.google.common.collect.Lists;
import com.xiaoyu.common.enums.ProgramEnums;
import com.xiaoyu.common.enums.ResultEnums;
import com.xiaoyu.common.exception.BusinessException;
import com.xiaoyu.common.result.Result;
import com.xiaoyu.common.util.ResultUtils;
import com.xiaoyu.maoyao.dao.RoleDao;
import com.xiaoyu.maoyao.dao.UserAuthsDao;
import com.xiaoyu.maoyao.dao.UserAuthsRolesDao;
import com.xiaoyu.maoyao.dao.UserDao;
import com.xiaoyu.maoyao.response.*;
import com.xiaoyu.maoyao.security.jwt.JwtAuthenticationRequest;
import com.xiaoyu.maoyao.security.jwt.JwtTokenUtil;
import com.xiaoyu.maoyao.security.jwt.JwtUser;
import com.xiaoyu.maoyao.user.Role;
import com.xiaoyu.maoyao.user.User;
import com.xiaoyu.maoyao.user.UserAuth;
import com.xiaoyu.maoyao.user.UserAuthsRoles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserAuthsDao userAuthsDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserAuthsRolesDao userAuthsRolesDao;

    @Override
    @Transactional
    public Result register(AccountRegisteredRequest request) throws Exception {
        String username = request.getUsername();
        String password = request.getPassword();
        String nickName = request.getNickName();
        String remark = request.getRemark();
        String introduction = request.getIntroduction();
        String avatar = request.getAvatar();
        UserAuth DBUserAuth = userAuthsDao.getUserAuthsByUserNameAndLoginType(username, ProgramEnums.LOGIN_ACCOUNT.getCode());
        if (DBUserAuth != null)
            throw new BusinessException(-1, ResultEnums.USERNAME_EXIT.getMsg());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
        List<Role> roleList = request.getRoleList().stream().map(name -> roleDao.getFirstRoleByName(name)).collect(Collectors.toList());
        if (roleList.isEmpty())
            roleList= Lists.newArrayList(roleDao.getFirstRoleByName(ProgramEnums.ROLE_USER.getMessage()));
        UserAuth userAuth = UserAuth.builder().loginType(ProgramEnums.LOGIN_ACCOUNT.getCode()).password(password).username(username).build();
        userAuth = userAuthsDao.createUserAuth(userAuth);
        Long userId=userAuth.getId();
        User user = User.builder().id(userId).nickName(nickName).remark(remark).introduction(introduction).avatar(avatar).build();
        userDao.createUser(user);
        List<UserAuthsRoles> userAuthsRolesList=roleList.stream().map(role -> UserAuthsRoles.builder().rolesId(role.getId()).userAuthsId(userId).build()).collect(Collectors.toList());
        userAuthsRolesDao.createUserAuthsRolesByBatch(userAuthsRolesList);
        return ResultUtils.success();
    }

    @Override
    public Result<LoginResponse> login(JwtAuthenticationRequest authenticationRequest) throws Exception {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication=authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);//保存当前用户信息
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);//校验用户是否存在
        String token = jwtTokenUtil.generateToken(jwtUser);
        UserAuth userAuth=userAuthsDao.getUserAuthsByUserNameAndLoginType(username,ProgramEnums.LOGIN_ACCOUNT.getCode());
        List<Long> roleIdList=userAuthsRolesDao.getUserAuthsRolesListByUserAuthsId(userAuth.getId()).stream().map(UserAuthsRoles::getRolesId).collect(Collectors.toList());
        List<Role> roleList=roleDao.getRoleListByRoleIdList(roleIdList);
        List<String> roleNameList=roleList.stream().map(Role::getName).collect(Collectors.toList());
        LoginResponse result=LoginResponse.builder().roleNameList(roleNameList).token(token).userId(userAuth.getId()).build();
        return ResultUtils.success(result);
    }

    @Override
    public Result<TokenGetResponse> refresh(String oldToken) {
        return null;
    }

    @Override
    public Result updateUser(InvitationCodeUploadRequest request) {
        return null;
    }

    @Override
    public void wechatLogin(String code, String state, HttpServletResponse response, String url) throws Exception {

    }

    @Override
    public Result createWxUserRelation(WxUserRelationCreateRequest request) {
        return null;
    }

    @Override
    public Result<LoginResponse> webLogin(WebLoginRequest request) {
        return null;
    }

    @Override
    public String weixinLogin(String url, Integer type, HttpServletResponse response) throws IOException {
        return null;
    }

    @Override
    public String QQLogin(String redirect_url, Integer type, String display, Integer g_ut, HttpServletResponse response) throws IOException {
        return null;
    }

    @Override
    public Result registerAgents(AgentsRegisterRequest request) {
        return null;
    }

    @Override
    public Result updateAgents(AgentsUpdateRequest request) {
        return null;
    }

    @Override
    public Result<WxJsTicketGetResponse> getWxJsTicket(String url) {
        return null;
    }
}
