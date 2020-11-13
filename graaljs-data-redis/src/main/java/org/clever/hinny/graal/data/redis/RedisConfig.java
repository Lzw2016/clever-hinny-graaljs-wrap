package org.clever.hinny.graal.data.redis;

import lombok.Data;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.util.List;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/11/11 17:23 <br/>
 */
@Data
public class RedisConfig {
    /**
     * 数据库索引
     */
    private int database = 0;

    /**
     * Redis服务器主机
     */
    private String host = "localhost";

    /**
     * redis服务器登录密码
     */
    private String password;

    /**
     * Redis服务器端口
     */
    private int port = 6379;

    /**
     * 是否启用SSL支持
     */
    private boolean ssl;

    /**
     * 连接超时(毫秒)
     */
    private Long timeoutMillis;

    /**
     * 哨兵配置
     */
    private SentinelConfig sentinel;

    /**
     * 集群配置
     */
    private ClusterConfig cluster;

    /**
     * jedis 客户端配置
     */
    private JedisConfig jedis;

    /**
     * lettuce 客户端配置
     */
    private LettuceConfig lettuce;

    @Data
    public static class SentinelConfig {
        /**
         * Redis服务器的名称
         */
        private String master;

        /**
         * 逗号分隔列表 "host:port" 对
         */
        private List<String> nodes;
    }

    @Data
    public static class ClusterConfig {
        /**
         * 群集节点的“初始”列表，要求至少有一个条目
         */
        private List<String> nodes;

        /**
         * 跨群集执行命令时要遵循的最大重定向数
         */
        private Integer maxRedirects;
    }

    @Data
    public static class PoolConfig {

        /**
         * 池中“空闲”连接的最大数量。使用负值表示空闲连接的数量不受限制
         */
        private int maxIdle = 8;

        /**
         * 池中要维护的最小空闲连接数的目标。此设置只有在它和逐出运行之间的时间均为正值时才有效
         */
        private int minIdle = 0;

        /**
         * 池在给定时间可以分配的最大连接数。使用负值表示无限制
         */
        private int maxActive = 8;

        /**
         * 当池耗尽时，连接分配在引发异常之前应阻塞的最长时间。使用负值可无限期阻止
         */
        private Long maxWaitMillis = -1L;

        /**
         * 空闲对象逐出器线程的运行间隔时间。为正值时，空闲对象逐出线程启动，否则不执行空闲对象逐出
         */
        private Long timeBetweenEvictionRunsMillis;
    }

    @Data
    public static class JedisConfig {
        /**
         * 连接池配置
         */
        private PoolConfig pool;
    }

    @Data
    public static class LettuceConfig {
        /**
         * 关机超时
         */
        private long shutdownTimeoutMillis = 100L;
        /**
         * 连接池配置
         */
        private PoolConfig pool;
    }

    public RedisProperties getRedisProperties() {
        RedisProperties redisProperties = new RedisProperties();

        return redisProperties;
    }
}
