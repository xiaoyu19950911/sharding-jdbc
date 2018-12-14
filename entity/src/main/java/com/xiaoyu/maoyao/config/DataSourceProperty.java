package com.xiaoyu.maoyao.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "database")
public class DataSourceProperty {

    private DataBaseInfo db_0;

    private DataBaseInfo db_1;

    private DataBaseInfo db_2;

    private DataBaseInfo db_3;

    public DataBaseInfo getDb_0() {
        return db_0;
    }

    public void setDb_0(DataBaseInfo db_0) {
        this.db_0 = db_0;
    }

    public DataBaseInfo getDb_1() {
        return db_1;
    }

    public void setDb_1(DataBaseInfo db_1) {
        this.db_1 = db_1;
    }

    public DataBaseInfo getDb_2() {
        return db_2;
    }

    public void setDb_2(DataBaseInfo db_2) {
        this.db_2 = db_2;
    }

    public DataBaseInfo getDb_3() {
        return db_3;
    }

    public void setDb_3(DataBaseInfo db_3) {
        this.db_3 = db_3;
    }

}
