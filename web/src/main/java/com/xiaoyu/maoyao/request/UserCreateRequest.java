package com.xiaoyu.maoyao.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UserCreateRequest {

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("简介")
    private String introduction;
}
