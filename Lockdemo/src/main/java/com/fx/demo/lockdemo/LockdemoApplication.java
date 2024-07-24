package com.fx.demo.lockdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fx.demo.lockdemo.mapper")
public class LockdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LockdemoApplication.class, args);
    }

}
