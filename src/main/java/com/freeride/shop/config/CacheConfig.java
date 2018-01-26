package com.freeride.shop.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        List<Cache> cacheList = new ArrayList<>();
        Cache brandsCache = new CaffeineCache("brands", Caffeine.newBuilder()
                .maximumSize(1)
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build());

        Cache categoriesCache = new CaffeineCache("categories", Caffeine.newBuilder()
                .maximumSize(1)
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build());

        Cache sizesCache = new CaffeineCache("sizes", Caffeine.newBuilder()
                .maximumSize(1)
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build());
        Cache sizeTypesCache = new CaffeineCache("sizeTypes", Caffeine.newBuilder()
                .maximumSize(1)
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build());

        cacheList.add(brandsCache);
        cacheList.add(categoriesCache);
        cacheList.add(sizesCache);
        cacheList.add(sizeTypesCache);

        manager.setCaches(cacheList);

        return manager;
    }
}