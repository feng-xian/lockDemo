package com.fx.demo.lockdemo.service.impl;

import com.fx.demo.lockdemo.entity.User;
import com.fx.demo.lockdemo.mapper.UserMapper;
import com.fx.demo.lockdemo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getEntity(int id) {
        return userMapper.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMoney() {
        System.out.println("开始修改金额！");
        this.reduceMoney();

        this.addMoney();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void reduceMoney() {
        userMapper.reduce(500, 1);
        System.out.println("扣减金额成功");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addMoney() {
        userMapper.add(500, 1);
        System.out.println("添加金额成功");
        if (true) {
            throw new RuntimeException("自定义的异常！");
        }
    }
}
