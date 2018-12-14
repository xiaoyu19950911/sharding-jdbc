package com.xiaoyu.maoyao.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;
import io.shardingjdbc.core.keygen.DefaultKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Random;

@Component
public class ModuloDatabaseShardingAlgorithmByWeight implements SingleKeyDatabaseShardingAlgorithm<Long> {


    @Autowired
    DataSourceProperty dataSourceProperty;

    @Autowired
    DefaultKeyGenerator defaultKeyGenerator;

    public ModuloDatabaseShardingAlgorithmByWeight() {

    }


    private String getDataBaseName() {
        DataSourceProperty dataSourceProperty = MyApplicationContext.applicationContext.getBean(DataSourceProperty.class);
        String databaseName;
        int db_0_weight = dataSourceProperty.getDb_0().getWeight();
        int db_1_weight = dataSourceProperty.getDb_1().getWeight();
        int db_2_weight = dataSourceProperty.getDb_2().getWeight();
        int db_3_weight = dataSourceProperty.getDb_3().getWeight();
        Random random = new Random();
        int n = random.nextInt(100);
        int total = db_0_weight + db_1_weight + db_2_weight + db_3_weight;
        int db_0_probability = (db_0_weight * 100) / total;
        int db_1_probability = (db_1_weight * 100) / total;
        int db_2_probability = (db_2_weight * 100) / total;
        if (n < db_0_probability)
            databaseName = "0";
        else if (n < db_0_probability + db_1_probability)
            databaseName = "1";
        else if (n < db_0_probability + db_1_probability + db_2_probability)
            databaseName = "2";
        else
            databaseName = "3";
        return databaseName;
    }

    @Override
    public String doEqualSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
        String databaseNum=getDataBaseName();
        for (String databaseName : collection) {
            if (databaseName.endsWith(databaseNum))
                return databaseName;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(collection.size());
        String databaseNum=getDataBaseName();
        for (Long value : shardingValue.getValues()) {
            for (String databaseName : collection) {
                if (databaseName.endsWith(databaseNum)) {
                    result.add(databaseName);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(collection.size());
        String databaseNum=getDataBaseName();
        Range<Long> range = shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : collection) {
                if (each.endsWith(databaseNum)) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
