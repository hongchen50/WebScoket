package com.websocket.websocketdemo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import sun.rmi.runtime.Log;

import java.util.logging.Logger;

@SpringBootApplication
@Slf4j(topic = "webSocket")
@MapperScan("com.websocket.websocketdemo.mapper")
public class WebsocketDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebsocketDemoApplication.class, args);
        log.info("点击链接登录："+"http://localhost:8080/login.html");
    }

}
