package com.xiaoyu.maoyao.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 分库算法，取random随机分配到所有数据库
 */
@Slf4j
public class ModuloDatabaseShardingAlgorithmByRandom implements SingleKeyDatabaseShardingAlgorithm<Long> {

    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        Random random = new Random();
        List<String> databaseNameList = new ArrayList<>(availableTargetNames);
        return databaseNameList.get(random.nextInt(databaseNameList.size()));
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Random random = new Random();
        for (int i = 0; i < shardingValue.getValues().size(); i++) {
            for (int j = 0; j < availableTargetNames.size(); j++) {
                List<String> databaseNameList = new ArrayList<>(availableTargetNames);
                result.add(databaseNameList.get(random.nextInt(databaseNameList.size())));
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Long> range = shardingValue.getValueRange();
        Random random = new Random();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (int j = 0; j < availableTargetNames.size(); j++) {
                List<String> databaseNameList = new ArrayList<>(availableTargetNames);
                result.add(databaseNameList.get(random.nextInt(databaseNameList.size())));
            }
        }
        return result;
    }
}
