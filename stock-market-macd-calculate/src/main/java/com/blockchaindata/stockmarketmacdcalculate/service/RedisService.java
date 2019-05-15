package com.blockchaindata.stockmarketmacdcalculate.service;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Andon
 * @date 2019/5/15
 */
@Service
public class RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private HashOperations<String, String, String> hashOperations;

    @Resource
    private SetOperations<String, Object> setOperations;

    /**
     * 设置超时时间
     *
     * @param key    key
     * @param expire 过期时间(单位:秒)
     */
    public void expireKey(String key, long expire) {
        stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * hash操作
     */
    public void addHashValue(String key, String field, String value) {
        hashOperations.put(key, field, value);
    }

    public void deleteHashValue(String key, String field) {
        hashOperations.delete(key, field);
    }

    public void deleteHashKey(String key) {
        hashOperations.delete(key);
    }

    public String getHashValue(String key, String field) {
        return hashOperations.get(key, field);
    }

    public List<String> getHashValues(String key) {
        return hashOperations.values(key);
    }

    public Set<String> getHashFields(String key) {
        return hashOperations.keys(key);
    }

    public boolean isHashFieldExists(String key, String field) {
        return hashOperations.hasKey(key, field);
    }

    public void emptyHashKey(String key) {
        Set<String> set = hashOperations.keys(key);
        set.forEach(field -> hashOperations.delete(key, field));
    }

    /**
     * set操作
     */
    public void addSetValue(String key, Object value) {
        setOperations.add(key, value);
    }

    public void addSetValues(String key, Object[] set) {
        setOperations.add(key, set);
    }

    public void removeSetValue(String key, Object value) {
        setOperations.remove(key, value);
    }

    public void removeSetValue(String key, Object[] value) {
        setOperations.remove(key, value);
    }

    public Set<Object> getSetValues(String key) {
        return setOperations.members(key);
    }
}
