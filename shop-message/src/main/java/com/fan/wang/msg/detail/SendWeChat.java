package com.fan.wang.msg.detail;

import com.fan.wang.msg.event.UserRegisterEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 微信通知类
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@Component
public class SendWeChat implements ApplicationListener<UserRegisterEvent> {
    @Override
    public void onApplicationEvent(UserRegisterEvent userRegisterEvent) {
        //解析消息进行发送
        System.out.println(userRegisterEvent.getMessage());
    }
}
