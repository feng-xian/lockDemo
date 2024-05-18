package com.fx.demo.lockdemo.lock_demo;


import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: DistributedLockTest
 * @Author: 凤仙
 * @Date: 2023/8/24 0024 13:46
 * @Description TODO
 * @Version: 1.0
 */
public class DistributedLockTest {

    public static void testLockExpireTime(RedisDistributedLock distributedLock){

        boolean locak = distributedLock.acquireLock("locak", "120", 10);
        System.out.println(locak);

        int count = 1;
        while (true){
            try {
                Thread.sleep(1 * 1000);
                Object locak1 = distributedLock.redisTemplate.opsForValue().get("locak");
                System.out.println("count:" + count);
                if (Objects.isNull(locak1)){
                    System.out.println("count=>>" + count);
                    break;
                }
                count += 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int releaseLock = distributedLock.releaseLock("locak", "120");
        System.out.println("releaseLock = " + releaseLock);

    }

    public static void testLock(RedisDistributedLock distributedLock){
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 定义共享的锁键和请求标识符
        String lockKey2 = "resource_lock";
        String requestId2 = null;

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                String lockKey = lockKey2;
                String requestId = String.valueOf(Thread.currentThread().getId());
                if (distributedLock.acquireLock(lockKey, requestId, 3)) {
                    try {
                        // 在锁内执行需要同步的操作
                        System.out.println("Thread " + Thread.currentThread().getId() + " acquired the lock.");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        int releaseLock = distributedLock.releaseLock(lockKey, requestId);
                        System.out.println("releaseLock = " + releaseLock);
                        if (1 != releaseLock){
                            System.out.println(requestId + "释放锁失败");
                        }
                        System.out.println("Thread " + Thread.currentThread().getId() + " released the lock.");
                        System.out.println();
                    }
                } else {
                    System.out.println("Thread " + Thread.currentThread().getId() + " failed to acquire the lock.");
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }



}
