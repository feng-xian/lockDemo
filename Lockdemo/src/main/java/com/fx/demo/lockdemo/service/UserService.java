package com.fx.demo.lockdemo.service;

import com.fx.demo.lockdemo.entity.User;

public interface UserService {

    User getEntity(int id);

    /**
     * 更新金额，测试spring事务的传播行为
     */
    void updateMoney();

    void reduceMoney();

    void addMoney();

}
