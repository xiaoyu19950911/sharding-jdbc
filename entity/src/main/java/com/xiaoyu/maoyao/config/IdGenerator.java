package com.xiaoyu.maoyao.config;

import io.shardingjdbc.core.keygen.DefaultKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Slf4j
@Component
public class IdGenerator implements IdentifierGenerator {

    @Autowired
    DefaultKeyGenerator defaultKeyGenerator;//此处无法自动装配，原因未知

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        //return defaultKeyGenerator.generateKey();//此处无法自动装配，原因未知
        //return new DefaultKeyGenerator().generateKey();
        return MyApplicationContext.applicationContext.getBean(DefaultKeyGenerator.class).generateKey();
    }
}
