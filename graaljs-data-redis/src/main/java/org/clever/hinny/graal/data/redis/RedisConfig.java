package org.clever.hinny.graal.data.redis;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/11/11 17:23 <br/>
 */
public class RedisConfig {

    public RedisProperties getRedisProperties() {
        RedisProperties redisProperties = new RedisProperties();

        return redisProperties;
    }
}
