package com.fx.demo.lockdemo.lock_demo;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Lock2
 * @Author: 凤仙
 * @Date: 2023/8/24 0024 13:38
 * @Description TODO
 * @Version: 1.0
 */
@Component
public class RedisDistributedLock {

    @Resource
    public RedisTemplate<String, String> redisTemplate;

    /**
     * 获取锁
     * @param lockKey 锁键
     * @param requestId 锁id
     * @param expirationTime 锁过期时间 秒
     * @return
     */
    public boolean  acquireLock(String lockKey, String requestId, long expirationTime) {
        String scriptStr =  "if redis.call('setNx',KEYS[1],ARGV[1])  then "   +
                "   if redis.call('get',KEYS[1])==ARGV[1] then "   +
                "      return redis.call('expire',KEYS[1],ARGV[2]) "   +
                "   else "   +
                "      return 0 "   +
                "   end "   +
                "end" ;

        RedisScript<Boolean> script = new DefaultRedisScript<>(
                scriptStr,
                Boolean.class
        );

        Boolean result = redisTemplate.execute(script, Collections.singletonList(lockKey), requestId, expirationTime);
        return result != null && result;
    }

    /**
     * 获取锁，自旋等待
     * @param lockKey 锁键
     * @param requestId 锁id
     * @param expirationTime 锁过期时间 秒
     * @param maxWaitMillis 最大等待时间 秒
     * @return
     */
    public boolean acquireLockWithSpinWait(String lockKey, String requestId, long expirationTime, long maxWaitMillis) {
        maxWaitMillis = maxWaitMillis * 1000;
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < maxWaitMillis) {
            if (acquireLock(lockKey, requestId, expirationTime)) {
                return true;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }

    /**
     * 释放锁
     * @param lockKey
     * @param requestId
     * @return 1:释放成功
     *         0:释放失败
     *         3:释放失败，不是自己的锁
     *         4:释放失败，锁已经过期
     */
    public int releaseLock(String lockKey, String requestId) {
        RedisScript<Long> script = new DefaultRedisScript<>(
                "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end",
                Long.class
        );

        Long execute = redisTemplate.execute(script, Collections.singletonList(lockKey), requestId);
        if (execute != null && execute == 1) {
            return 1;
        }
        else if (execute != null && execute == 0) {
            if (Objects.isNull(redisTemplate.opsForValue().get(lockKey))){
                forceReleaseLock(lockKey);
                return 1;
            }
            if (requestId.equals(redisTemplate.opsForValue().get(lockKey))) {
                forceReleaseLock(lockKey);
                return 1;
            } else {
                return 3;
            }
        }
        return 0;
    }

    /**
     * 强制释放锁
     * @param lockKey
     */
    public void forceReleaseLock(String lockKey) {
        redisTemplate.delete(lockKey);
    }

}
