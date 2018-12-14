package com.xiaoyu.maoyao.controller;

import com.xiaoyu.common.result.Result;
import com.xiaoyu.common.util.ResultUtils;
import com.xiaoyu.maoyao.config.DataSourceProperty;
import com.xiaoyu.maoyao.repository.UserRepository;
import com.xiaoyu.maoyao.request.UserCreateRequest;
import com.xiaoyu.maoyao.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/user")
@RestController
@Api(value = "user", description = "用户相关接口")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DataSourceProperty dataSourceProperty;

    @PostMapping("createUser")
    @ApiOperation("新增用户")
    public Result createUser(@Valid @RequestBody UserCreateRequest request, BindingResult bindingResult){
        return userService.createUser(request);
    }


    @PostMapping("test")
    @ApiOperation("test")
    public Result test(){
        Integer db_0=dataSourceProperty.getDb_0().getWeight();
        Integer db_1=dataSourceProperty.getDb_0().getWeight();
        Integer db_2=dataSourceProperty.getDb_0().getWeight();
        Integer db_3=dataSourceProperty.getDb_0().getWeight();

        userService.test();
        return ResultUtils.success(db_3);
    }


}
