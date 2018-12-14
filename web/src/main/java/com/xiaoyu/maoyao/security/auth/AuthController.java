package com.xiaoyu.maoyao.security.auth;

import com.xiaoyu.common.result.Result;
import com.xiaoyu.common.util.ResultUtils;
import com.xiaoyu.maoyao.annotation.RequestValid;
import com.xiaoyu.maoyao.response.LoginResponse;
import com.xiaoyu.maoyao.response.TokenGetResponse;
import com.xiaoyu.maoyao.security.jwt.JwtAuthenticationRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@Api(value = "auth", description = "授权相关接口")
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @ApiOperation("注册账号")
    @ResponseBody
    @PostMapping(value = "/registeredAccount")
    @RequestValid
    public Result registeredAccount(@Valid @RequestBody AccountRegisteredRequest request, BindingResult bindingResult) throws Exception {
        return authService.register(request);
    }

    @ApiOperation("登陆并获取token")
    @ResponseBody
    @PostMapping(value = "/login")
    @RequestValid
    public Result<LoginResponse> login(@Valid @RequestBody JwtAuthenticationRequest authenticationRequest, BindingResult bindingResult) throws Exception {
        return authService.login(authenticationRequest);
    }




    /**
     * 微信网页授权流程:
     * 1. 用户同意授权,获取 code
     * 2. 通过 code 换取网页授权 access_token
     * 3. 使用获取到的 access_token 和 openid 拉取用户信息
     *
     * @param code  用户同意授权后,获取到的code
     * @param state 重定向状态参数
     * @return
     */
    @GetMapping(value = "/callback")
    @ApiOperation("授权回调接口")
    public void wechatLogin(@RequestParam(name = "code", required = false) String code, @RequestParam(name = "state") String state, HttpServletResponse response, String url) throws Exception {
        authService.wechatLogin(code, state, response, url);
    }

    @GetMapping("/wechatLogin")
    @ApiOperation("web_app微信授权")
    public void weixinLogin(@RequestParam String url, @ApiParam("1、登陆授权；2、提现授权") @RequestParam Integer type, HttpServletResponse response) throws IOException {
        authService.weixinLogin(url, type, response);
    }

    @GetMapping("/QQLogin")
    @ApiOperation("web_app QQ/微信登陆")
    public void QQLogin(@RequestParam String redirect_url, @ApiParam("登陆类型（1、pc网站；2、wap网站；3、微信登陆）") @RequestParam Integer type, @ApiParam("仅PC网站接入时使用。用于展示的样式。不传则默认展示为PC下的样式。如果传入“mobile”，则展示为mobile端下的样式。") @RequestParam(required = false) String display, @ApiParam("仅WAP网站接入时使用。QQ登录页面版本（1：wml版本； 2：xhtml版本），默认值为1。") @RequestParam(required = false) Integer g_ut, HttpServletResponse response) throws IOException {
        authService.QQLogin(redirect_url, type, display, g_ut, response);
    }

    @ApiOperation("退出并更新token")
    @ResponseBody
    @RequestMapping(value = "${jwt.route.authentication.logout}", method = RequestMethod.POST)
    @ApiIgnore
    public Result deleteAuthenticationToken(HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        authService.refresh(token);
        return ResultUtils.success();
    }

    @ApiOperation("刷新token")
    @ResponseBody
    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public Result<TokenGetResponse> refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        return authService.refresh(token);
    }




    @ApiOperation("获取签名")
    @ResponseBody
    @GetMapping("/getResSign")
    public Result<WxJsTicketGetResponse> getWxJsTicket(WxJsTicketGetRequest request,BindingResult bindingResult) {
        return authService.getWxJsTicket(request.getUrl());
    }
}
