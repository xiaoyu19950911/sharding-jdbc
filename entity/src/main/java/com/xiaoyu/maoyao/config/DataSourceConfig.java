package com.xiaoyu.maoyao.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.NoneTableShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import io.shardingjdbc.core.keygen.DefaultKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.assertj.core.util.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: rry
 * @description: 分库分表时使用，暂时不用该配置
 * @author: XiaoYu
 * @create: 2018-06-15 10:52
 **/
@Configuration
@Slf4j
public class DataSourceConfig {

/*    @Autowired
    DataSourceProperty dataSourceProperty;*/

    @Bean
    public DataSource getDataSource( DataSourceProperty dataSourceProperty) throws SQLException {
        return buildDataSource(dataSourceProperty);
    }

    @Bean
    public DefaultKeyGenerator defaultKeyGenerator(){
        return new DefaultKeyGenerator();
    }



    private DataSource buildDataSource(DataSourceProperty dataSourceProperty) throws SQLException {
        //设置分库映射
        Map<String, DataSource> dataSourceMap = new HashMap<>(4);
        //添加两个数据库ds_0,ds_1到map里
        dataSourceMap.put("ds_0", createDataSource(dataSourceProperty.getDb_0()));
        dataSourceMap.put("ds_1", createDataSource(dataSourceProperty.getDb_1()));
        dataSourceMap.put("ds_2", createDataSource(dataSourceProperty.getDb_2()));
        dataSourceMap.put("ds_3", createDataSource(dataSourceProperty.getDb_3()));
        //设置默认db为ds_0，也就是为那些没有配置分库分表策略的指定的默认库
        //如果只有一个库，也就是不需要分库的话，map里只放一个映射就行了，只有一个库时不需要指定默认库，但2个及以上时必须指定默认库，否则那些没有配置策略的表将无法操作数据
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, "ds_0");


        TableRule userTableRule = TableRule.builder("user")
                .dataSourceRule(dataSourceRule)
                .build();

        //具体分库分表策略，按什么规则来分
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Lists.newArrayList(userTableRule))
                .tableShardingStrategy(new TableShardingStrategy("id",new NoneTableShardingAlgorithm()))
                .databaseShardingStrategy(new DatabaseShardingStrategy("id", new ModuloDatabaseShardingAlgorithmByWeight())).build();

        DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);

        return dataSource;
    }

    private static DataSource createDataSource(DataBaseInfo dataBaseInfo){
        //使用dbcp2连接数据库
        BasicDataSource result=new BasicDataSource();
        // DruidDataSource result = new DruidDataSource();
        result.setDriverClassName("com.mysql.jdbc.Driver");
        result.setUrl(dataBaseInfo.getUrl());
        result.setUsername(dataBaseInfo.getUsername());
        result.setPassword(dataBaseInfo.getPassword());
        result.setDefaultAutoCommit(true);
        result.setInitialSize(30);
        result.setMaxTotal(120);
        result.setMaxIdle(120);
        result.setMinIdle(30);
        result.setMaxWaitMillis(10000);
        result.setValidationQuery("SELECT 1");
        result.setValidationQueryTimeout(3);
        result.setTestOnBorrow(true);
        result.setTestWhileIdle(true);
        result.setTimeBetweenEvictionRunsMillis(10000);
        result.setNumTestsPerEvictionRun(10);
        result.setMinEvictableIdleTimeMillis(120000);
        result.setRemoveAbandonedOnBorrow(true);
        result.setRemoveAbandonedTimeout(120);
        result.setPoolPreparedStatements(true);
        return result;
    }


    private static DataSource createDataSource(final String url,final String username,final String password) {
        //使用dbcp2连接数据库
        BasicDataSource result=new BasicDataSource();
        // DruidDataSource result = new DruidDataSource();
        result.setDriverClassName("com.mysql.jdbc.Driver");
        result.setUrl(url);
        result.setUsername(username);
        result.setPassword(password);
        result.setDefaultAutoCommit(true);
        result.setInitialSize(30);
        result.setMaxTotal(120);
        result.setMaxIdle(120);
        result.setMinIdle(30);
        result.setMaxWaitMillis(10000);
        result.setValidationQuery("SELECT 1");
        result.setValidationQueryTimeout(3);
        result.setTestOnBorrow(true);
        result.setTestWhileIdle(true);
        result.setTimeBetweenEvictionRunsMillis(10000);
        result.setNumTestsPerEvictionRun(10);
        result.setMinEvictableIdleTimeMillis(120000);
        result.setRemoveAbandonedOnBorrow(true);
        result.setRemoveAbandonedTimeout(120);
        result.setPoolPreparedStatements(true);
        return result;
    }

}
