
package com.fan.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//启用feign客户端调用
@EnableFeignClients
public class ShopMobileServer {

    public static void main(String[] args) {
        SpringApplication.run(ShopMobileServer.class, args);
    }

}
