package com.fan.wang.mq.provider;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * rabbitmq消息生产者
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@Service
public class ProviderMsg {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 像交换机发送消息
     *
     * @param msg 发送的消息
     */
    // Todo register-mail交换机的模式是fanout，绑定到上边的所有队列都可以坚挺到消息，无需指定路由
    public void sendMsg(String msg) {
        rabbitTemplate.convertAndSend("register-mail", "", msg);
    }
}

