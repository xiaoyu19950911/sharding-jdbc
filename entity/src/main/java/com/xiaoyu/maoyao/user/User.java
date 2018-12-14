package com.xiaoyu.maoyao.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Id
    private Long id;

    @Column(columnDefinition = "varchar(255) COMMENT'用户昵称'")
    private String nickName;

    @Column(columnDefinition = "varchar(2048) COMMENT'用户简介'")
    private String introduction;

    @Column(columnDefinition = "varchar(255) COMMENT'用户头像URL'")
    private String avatar;

    @Column(columnDefinition = "varchar(255) COMMENT'备注'")
    private String remark;

    @Column(columnDefinition = "datetime(3) COMMENT'用户注册时间'")
    private Date createTime;

    @Column(columnDefinition = "datetime(3) COMMENT'更新时间'")
    private Date updateTime;
}
