package com.xiaoyu.maoyao.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuth {

    @Id
    @GeneratedValue(generator = "generateIdStrategy")
    @GenericGenerator(name = "generateIdStrategy",strategy = "com.xiaoyu.maoyao.config.IdGenerator")
    private Long id;

    private Integer loginType;

    private String username;

    private String password;

    /*@ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "user_auths_roles")
    private List<Role> roles;*/

    private Date createTime;

    private Date updateTime;

}
