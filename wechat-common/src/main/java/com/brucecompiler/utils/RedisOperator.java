package com.brucecompiler.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * RedisOperator is a utility class which provides convenient methods
 * for interacting with Redis.
 * <p>
 * This class supports the following functionalities:
 * - Check if a key exists in Redis
 * - Retrieving the Time-To-Live(ttl) of a key
 * - Setting expiration times for keys
 * - Incrementing the value of a key or a hash field by a specified delta
 * <p>
 *
 * Usage:
 * 1. Inject this class into your service or component.
 * 2. Use the provided methods to perform Redis operations
 */
@Component
public class RedisOperator {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisOperator(StringRedisTemplate stringRedisTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * Check if a specific key exists in Redis
     *
     * @param key The key to check in Redis
     * @return true if the key exists, otherwise false
     */
    public boolean KeyIsExist(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * Get the Time-To-Live(TTL) of a key in Redis
     *
     * @param key The key whose TTL is to be retrieved.
     * @return The TTL of the key in seconds
     *              -1 if the key does not have an expiration.
     *              -2 if the key does not exist
     */
    public long ttl(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    /**
     * Set the expiration time for a specific key.
     *
     * @param key The key whose time is to be set
     * @param timeout The expiration time in seconds
     */
    public void expire(String key, long timeout) {
        stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * Increment the value of a key by a specified delta
     *
     * @param key The key whose value is to be incremented
     * @param delta The amount by which the key's value should be incremented
     * @return The new value after incrementing
     */
    public long increment(String key, long delta) {
        Long result = stringRedisTemplate.opsForValue().increment(key, delta);
        return (result == null) ? 0 : result;
    }

    /**
     * Increment the value of a hash field by a specified delta
     *
     * @param name The name of the hash
     * @param key The field within the hash to be incremented
     * @param delta The amount by which the field's value should be incremented
     * @return The new value of the hash field after incrementing
     */
    public long incrementHash(String name, String key, long delta) {
        return stringRedisTemplate.opsForHash().increment(name, key, delta);
    }


}
