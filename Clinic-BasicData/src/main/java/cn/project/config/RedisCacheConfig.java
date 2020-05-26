package cn.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisCacheConfig {
    @Resource
    RedisConnectionFactory conFactory;
    @Resource
    RedisTemplate redisTemplate;
    @Bean
    RedisCacheManager redisCacheManager(){
        Map<String, RedisCacheConfiguration> configMap = new HashMap<String, RedisCacheConfiguration>();
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .prefixKeysWith("c1:")
                .disableCachingNullValues()
                .entryTtl(Duration.ofMinutes(30));
        configMap.put("c1",redisCacheConfiguration);


        RedisCacheWriter cacheWriter =
                RedisCacheWriter.nonLockingRedisCacheWriter(conFactory);
        RedisCacheManager redisCacheManager =
                new RedisCacheManager(
                        cacheWriter,
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(60)),
                        configMap
                );
        return redisCacheManager;
    }
}
