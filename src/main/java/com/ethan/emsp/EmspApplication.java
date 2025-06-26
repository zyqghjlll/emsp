package com.ethan.emsp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@MapperScan("com.ethan.emsp.infrastructure.persistence.mapper")
public class EmspApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmspApplication.class, args);
    }
}
