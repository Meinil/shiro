package com.meinil.demo.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class RedisCacheManager implements CacheManager {
    // cacheName 统一授权的缓存名称
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        return new RedisCache<>(cacheName);
    }
}
