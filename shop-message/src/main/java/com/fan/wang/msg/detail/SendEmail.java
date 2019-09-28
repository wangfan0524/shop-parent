package com.fan.wang.msg.detail;

import com.alibaba.fastjson.JSONObject;
import com.fan.wang.msg.config.EmailConfig;
import com.fan.wang.msg.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * 邮件通知类
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@Component
@Slf4j
public class SendEmail implements ApplicationListener<UserRegisterEvent> {
    @Autowired
    private JavaMailSender mailSender; // 自动注入的Bean

    @Autowired
    EmailConfig emailConfig;
    @Override
    public void onApplicationEvent(UserRegisterEvent userRegisterEvent) {
        //解析消息进行发送
        System.out.println(userRegisterEvent.getMessage());
        JSONObject jsonObject= (JSONObject) userRegisterEvent.getMessage().get("content");
        String mail=jsonObject.getString("email");
        String userName=jsonObject.getString("username");
        log.info("###消费者收到消息... mail:{},userName:{}",mail,userName);
        // 发送邮件
        // 开始发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getFrom());
        message.setTo(mail);
        message.setSubject("商城注册成功");
        message.setText(userName+ "欢迎您,成为新用户!");
        log.info("###发送短信邮箱 mail:{}", mail);
        mailSender.send(message);
    }
}
