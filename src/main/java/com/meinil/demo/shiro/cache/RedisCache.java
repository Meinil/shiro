package com.meinil.demo.shiro.cache;

import com.meinil.demo.utils.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Collection;
import java.util.Set;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class RedisCache<K, V> implements Cache<K, V> {

    private final static Duration DURATION = Duration.ofMinutes(30);
    private RedisTemplate redisTemplate;
    private String cacheName;

    public RedisCache() {
    }

    public RedisCache(String cacheName) {
        this.cacheName = cacheName;
    }

    @Override
    public V get(K k) throws CacheException {
        RedisTemplate redisTemplate = getRedisTemplate();
        Object o = redisTemplate.opsForHash().get(this.cacheName, k);
        return o == null ? null : (V)o;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.opsForHash().put(this.cacheName, k.toString(), v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    public RedisTemplate getRedisTemplate() {
        if (redisTemplate == null) {
            this.redisTemplate = (RedisTemplate)ApplicationContextUtils.get("redisTemplate");
            redisTemplate.setStringSerializer(new StringRedisSerializer());
            redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        }
        return redisTemplate;
    }
}
