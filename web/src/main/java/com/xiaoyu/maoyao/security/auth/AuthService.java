package com.xiaoyu.maoyao.security.auth;


import com.xiaoyu.common.result.Result;
import com.xiaoyu.maoyao.response.*;
import com.xiaoyu.maoyao.security.jwt.JwtAuthenticationRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthService {
    Result register(AccountRegisteredRequest request) throws Exception;

    Result<LoginResponse> login(JwtAuthenticationRequest authenticationRequest) throws Exception;

    Result<TokenGetResponse> refresh(String oldToken);

    Result updateUser(InvitationCodeUploadRequest request);

    void wechatLogin(String code, String state, HttpServletResponse response, String url) throws Exception;

    Result createWxUserRelation(WxUserRelationCreateRequest request);

    Result<LoginResponse> webLogin(WebLoginRequest request);

    String weixinLogin(String url, Integer type, HttpServletResponse response) throws IOException;

    String QQLogin(String redirect_url, Integer type, String display, Integer g_ut, HttpServletResponse response) throws IOException;

    Result registerAgents(AgentsRegisterRequest request);

    Result updateAgents(AgentsUpdateRequest request);

    Result<WxJsTicketGetResponse> getWxJsTicket(String url);
}
