package com.xiaoyu.maoyao.controller;


import com.xiaoyu.common.enums.ProgramEnums;
import com.xiaoyu.common.result.Result;
import com.xiaoyu.common.util.ResultUtils;
import com.xiaoyu.maoyao.repository.RoleRepository;
import com.xiaoyu.maoyao.user.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("/role")
@RestController
@Api(value = "role", description = "角色相关接口")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("createRole")
    @ApiOperation("新增角色")
    public Result createRole(){
        Date now=new Date();
        Role role1=Role.builder().name(ProgramEnums.ROLE_USER.getMessage()).createTime(now).updateTime(now).build();
        roleRepository.save(role1);
        Role role2=Role.builder().name(ProgramEnums.ROLE_AGENTS.getMessage()).createTime(now).updateTime(now).build();
        roleRepository.save(role2);
        Role role3=Role.builder().name(ProgramEnums.ROLE_ADMIN.getMessage()).createTime(now).updateTime(now).build();
        roleRepository.save(role3);
        Role role4=Role.builder().name(ProgramEnums.ROLE_ROOT.getMessage()).createTime(now).updateTime(now).build();
        roleRepository.save(role4);
        //List<Role> roleList= Lists.newArrayList(role1,role2,role3,role4);
        //roleRepository.saveAll(roleList);
        return ResultUtils.success();
    }
}
