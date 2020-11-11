package org.clever.hinny.graal.data.redis;

import java.util.Map;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/11/11 17:21 <br/>
 */
public abstract class AbstractRedisDatabase {

    public RedisConfig getRedisConfig(Map<String, Object> config) {
        RedisConfig redisConfig = new RedisConfig();

        return redisConfig;
    }
}
