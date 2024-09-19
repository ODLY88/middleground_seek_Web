package com.msw;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 *主类（项目启动入口）
 * 如需开启 Redis，须移除 exclude 中的内容
 */
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@MapperScan("com.msw.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@Log4j2
public class mswApplication {
    public static void main(String[] args) {
        SpringApplication.run(mswApplication.class, args);
        log.info("middleground_seek_Web Successfully started");
    }
}