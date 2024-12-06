package com.zhang.redis7.springboot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;


@EnableCaching  // 开启缓存
@Configuration  // 配置类
public class RedisConfig extends CachingConfigurerSupport {


    /**
     * 配置 Redis 连接工厂
     * 意义: LettuceConnectionFactory 是连接 Redis 服务器的入口，它使用了 Lettuce 客户端，并且配置了连接池来提高性能和资源管理
     *
     * @return LettuceConnectionFactory
     */
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        // 配置 Redis 服务器的连接信息
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName("192.168.10.102");
//        redisStandaloneConfiguration.setPort(6379);
//        redisStandaloneConfiguration.setPassword("123456"); // 取消注释以设置密码
//
//        // 配置连接池
//        GenericObjectPoolConfig<Object> poolConfig = new GenericObjectPoolConfig<>();
//        poolConfig.setMaxTotal(10);       // 连接池中的最大连接数
//        poolConfig.setMaxIdle(5);         // 连接池中的最大空闲连接数
//        poolConfig.setMinIdle(1);         // 连接池中的最小空闲连接数
//        poolConfig.setMaxWaitMillis(2000); // 连接池获取连接的最大等待时间
//
//        // 创建一个带有连接池配置的 Lettuce 客户端配置
//        LettucePoolingClientConfiguration lettucePoolingClientConfiguration =
//                LettucePoolingClientConfiguration.builder()
//                        .poolConfig(poolConfig)
//                        .build();
//
//        // 返回带有连接池配置的 Redis 连接工厂
//        return new LettuceConnectionFactory(redisStandaloneConfiguration, lettucePoolingClientConfiguration);
//    }
//
//    /**
//     * 配置并返回一个 RedisTemplate 实例，用于执行 Redis 操作
//     * 意义: RedisTemplate 提供了一种高级抽象，使得开发者可以通过模板方法操作 Redis，而无需直接处理底层的 Redis 命令。
//     * 它支持多种 Redis 操作，例如值操作、哈希操作、列表操作等
//     * @return RedisTemplate
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        /*
//            1.创建 RedisTemplate: 这是 Spring 用于与 Redis 交互的核心类，简化了与 Redis 的交互。
//            2.设置连接工厂: 使用前面定义的 LettuceConnectionFactory。
//            3.设置序列化器: 设置键和值的序列化器，这里使用 StringRedisSerializer 来将键和值序列化为字符串。
//         */
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory());  // 设置连接工厂
//        template.setKeySerializer(new StringRedisSerializer());  // 设置键的序列化器
//        template.setValueSerializer(new StringRedisSerializer()); // 设置值的序列化器
//        return template;
//    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();

        RedisSerializer<String> redisSerializer = new StringRedisSerializer();

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();

        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(om);

        template.setConnectionFactory(factory);

        //key序列化方式

        template.setKeySerializer(redisSerializer);

        //value序列化

        template.setValueSerializer(jackson2JsonRedisSerializer);

        //value hashmap序列化

        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        return template;

    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {

        RedisSerializer<String> redisSerializer = new StringRedisSerializer();

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        //解决查询缓存转换异常的问题

        ObjectMapper om = new ObjectMapper();

        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(om);

        // 配置序列化（解决乱码的问题）,过期时间600秒

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()

                .entryTtl(Duration.ofSeconds(600))

                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))

                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))

                .disableCachingNullValues();

        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)

                .cacheDefaults(config)

                .build();

        return cacheManager;

    }

}