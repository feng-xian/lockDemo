package com.fx.demo.lockdemo.rocketmq;

import com.fx.demo.lockdemo.entity.User;
import com.fx.demo.lockdemo.utils.JacksonUtils;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
        consumerGroup = "SYNC-USER-GROUP"
        , topic = "SYNC-USER"
        , consumeMode = ConsumeMode.ORDERLY
        , consumeThreadMax = 4 // 设置最大线程数为4
)
public class CustomerUser  implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        User user = null;
        try {
            user = JacksonUtils.json2pojo(s, User.class);
        } catch (Exception e) {
            System.out.println("ThreadId=" + Thread.currentThread().getId() + "error");
            throw new RuntimeException(e);
        }
        System.out.println("ThreadId=" + Thread.currentThread().getId() + ": 收到消息：" + user);
    }

}
