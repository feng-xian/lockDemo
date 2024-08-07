package com.fx.demo.lockdemo.mapper;

import com.fx.demo.lockdemo.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User queryById(String id);

    int insert(User sysUser);

    int update(User sysUser);


    int reduce(@Param("num") int num, @Param("id")int id);
    int add(@Param("num") int num, @Param("id")int id);

    User getById(@Param("id")int id);
}
