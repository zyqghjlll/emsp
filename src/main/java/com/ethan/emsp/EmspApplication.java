package com.ethan.emsp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@MapperScan(basePackages = {
        "com.ethan.emsp.infrastructure.persistence.domain.mapper",
        "com.ethan.emsp.infrastructure.persistence.event.mapper",
        "com.ethan.emsp.infrastructure.persistence.query.mapper"
})
public class EmspApplication {

    public static void main(String[] args) {
        System.out.println("当前JVM工作目录：" + System.getProperty("user.dir"));
        SpringApplication.run(EmspApplication.class, args);
    }
}
