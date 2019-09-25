package com.fan.wang.msg.mq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.fan.wang.msg.event.UserRegisterEvent;
import com.fan.wang.utils.ApplicationContextUtil;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * rabbitmq消息消费者
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@Service
public class ConsumerMsg {
    /**
     * 监听队列消息
     *
     * @param message 监听到的消息
     */
    @RabbitListener(queues = "user-msg-queue")
    public void recevie(String message, Channel  channel) {
        JSONObject jsonObject = JSONObject.parseObject(message);
        //Todo 这里采用发布订阅模式，进行解耦，需要做相应通知的接口只需要实现ApplicationListener<UserRegisterEvent>就可以
        //Todo 通过ApplicationContextUtil获取到上下文
        ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
        //Todo 生成事件
        UserRegisterEvent userRegisterEvent = new UserRegisterEvent(applicationContext, jsonObject);
        //Todo 通过上下文进行用户注册事件的发布，实现了ApplicationListener接口对UserRegisterEvent进行监听的监听都可以收到这个事件
        applicationContext.publishEvent(userRegisterEvent);
    }

}
