package com.bootcamp.redisconfig;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class BDCacheConfig {

    public static final String CACHE_PRODUCT = "cacheproduct";

    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager(CACHE_PRODUCT);
    }
}
