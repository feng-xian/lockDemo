package com.fx.demo.lockdemo;

import com.fx.demo.lockdemo.lock_demo.DistributedLockTest;
import com.fx.demo.lockdemo.lock_demo.RedisDistributedLock;
import com.fx.demo.lockdemo.lock_demo.RedissonLockUtil;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class LockdemoApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(LockdemoApplicationTests.class);
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RedisDistributedLock redisDistributedLock;

    @Resource
    private RedissonLockUtil redissonLockUtil;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("testKey", "testValue");

        Object key = redisTemplate.opsForValue().get("testKey");
        System.out.println(key);

    }

    @Test
    public void testLock() {
        DistributedLockTest.testLock(redisDistributedLock);
    }

    @Test
    public void doLock(){
        String key = "REPORT_GENERATE_ID_" + 123456;

        RLock lock = redissonLockUtil.lock(key, 120);
        lock.lock();
    }

    @Test
    public void getLock(){
        String key = "REPORT_GENERATE_ID_" + 123456;

        RLock lock = redissonLockUtil.lock(key);

        if (!lock.isLocked()) {
            System.out.println("获取报告成功！");
        }
        else {
            System.out.println("报告生成中，请稍后尝试！");
        }
    }

    @Test
    public void redisLockTest()
    {
        // 初始化秒杀库存数量
        redisUtil.set("seckill:goods:stock", "10");
        List<Future> futureList = new ArrayList<>();

        //多线程异步执行
        ExecutorService executors = Executors.newScheduledThreadPool(10);
        //
        for (int i = 0; i < 30; i++)
        {
            futureList.add(executors.submit(this::lockStock));

            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                System.out.println("redisLockTest error" + e);
            }
        }

        // 等待结果，防止主线程退出
        futureList.forEach(t -> {
            try
            {
                int stockNum =(int) t.get();
                System.out.println("库存剩余数量:" + stockNum);
            }
            catch (Exception e)
            {
                System.out.println("get stock num error" + e);
            }
        });
    }

    public int lockStock()
    {
        String clientId = UUID.randomUUID().toString();
        String lockKey="lock:stock";


        //加锁
        RLock lock = redissonLockUtil.lock(lockKey);


        boolean locked = lock.isLocked();
        System.out.println(clientId + "----isLock1-->" + locked);
        lock.lock();
         locked = lock.isLocked();
        System.out.println(clientId + "----isLock2-->" + locked);


        try
        {
            System.out.println("加锁成功 clientId：" + clientId);
            int stockNum= Integer.valueOf((String)redisUtil.get("seckill:goods:stock"));
            if(stockNum>0)
            {
                stockNum--;
                redisUtil.set("seckill:goods:stock",String.valueOf(stockNum));
                System.out.println("秒杀成功,剩余库存:" + stockNum);
//
                Thread.sleep(2000);

            }
            else
            {
                System.out.println("秒杀失败,剩余库存:" + stockNum);
            }
            //获取库存数量
            return stockNum;
        }
        catch (Exception e)
        {
            System.out.println("decry stock eror" );
        }
        finally
        {
            if(lock!=null)
            {
                lock.unlock();
            }
        }
        return 0;
    }

}
