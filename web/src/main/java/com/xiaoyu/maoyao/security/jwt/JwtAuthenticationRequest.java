package com.xiaoyu.maoyao.security.jwt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel
@Data
public class  JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    @ApiModelProperty(value ="登陆标志" ,required =true)
    @NotBlank(message = "登陆名不能为空！")
    @NotNull(message = "登陆名不能为空！")
    private String username;

    @ApiModelProperty(value = "登陆凭证")
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
