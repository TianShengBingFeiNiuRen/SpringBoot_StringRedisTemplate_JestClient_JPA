package com.blockchaindata.stockmarketmacdcalculate.service;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Andon
 * @date 2019/2/20
 */
@Service
public class RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private HashOperations<String, String, String> hashOperations;

    /**
     * 添加
     *
     * @param field  field
     * @param key    key
     * @param domain 对象
     * @param expire 过期时间(单位:秒),传入 -1 时表示不设置过期时间
     */
    public void put(String field, String key, String domain, long expire) {
        hashOperations.put(field, key, domain);
        if (expire != -1) {
            stringRedisTemplate.expire(field, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 删除
     */
    public void remove(String field, String key) {
        hashOperations.delete(field, key);
    }

    public void removeField(String field){
        hashOperations.delete(field);
    }

    /**
     * 查询
     */
    public String get(String field, String key) {
        return hashOperations.get(field, key);
    }

    /**
     * 获取当前redis库下所有对象
     */
    public List<String> getAll(String field) {
        return hashOperations.values(field);
    }

    /**
     * 查询查询当前redis库下所有key
     */
    public Set<String> getKeys(String field) {
        return hashOperations.keys(field);
    }

    /**
     * 判断key是否存在redis中
     */
    public boolean isKeyExists(String field, String key) {
        return hashOperations.hasKey(field, key);
    }

    /**
     * 查询当前key下缓存数量
     */
    public long count(String field) {
        return hashOperations.size(field);
    }

    /**
     * 清空redis
     */
    public void empty(String field) {
        Set<String> set = hashOperations.keys(field);
        set.forEach(key -> hashOperations.delete(field, key));
    }

}
