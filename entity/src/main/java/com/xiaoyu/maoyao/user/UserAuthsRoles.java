package com.xiaoyu.maoyao.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: rry
 * @description:
 * @author: XiaoYu
 * @create: 2018-08-03 14:18
 **/
@Entity
@Data
@Table(name = "user_auths_roles")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthsRoles {

    @Id
    @GeneratedValue(generator = "generateIdStrategy")
    @GenericGenerator(name = "generateIdStrategy",strategy = "com.xiaoyu.maoyao.config.IdGenerator")
    private Long id;

    @Column(name = "user_auths_id", columnDefinition = "bigint(20) COMMENT'用户id'")
    private Long userAuthsId;

    @Column(name = "roles_id", columnDefinition = "bigint(20) COMMENT'角色id'")
    private Long rolesId;

    @Column(columnDefinition = "datetime(3) COMMENT'用户注册时间'")
    private Date createTime;

    @Column(columnDefinition = "datetime(3) COMMENT'更新时间'")
    private Date updateTime;
}
