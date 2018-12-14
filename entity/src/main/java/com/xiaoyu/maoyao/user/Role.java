package com.xiaoyu.maoyao.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "ROLE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(generator = "generateIdStrategy")
    @GenericGenerator(name = "generateIdStrategy",strategy = "com.xiaoyu.maoyao.config.IdGenerator")
    private Long id;

    @Column(columnDefinition = "varchar(255) COMMENT'角色名'")
    private String name;

    @Column(columnDefinition = "datetime(3) COMMENT'创建时间'")
    private Date createTime;

    @Column(columnDefinition = "datetime(3) COMMENT'更新时间'")
    private Date updateTime;
}
