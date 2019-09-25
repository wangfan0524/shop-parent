package com.fan.wang.msg.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮件服务聘任制封装类
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@ConfigurationProperties(prefix="spring.mail")
@Component
@Getter
@Setter
public class EmailConfig {

    private String host;
    private String username;
    private String password;
    private String from;
}
