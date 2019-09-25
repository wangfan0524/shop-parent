package com.fan.wang.msg.event;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;


/**
 * 用户注册事件
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
public class UserRegisterEvent extends ApplicationContextEvent {

    private JSONObject message;

    public UserRegisterEvent(ApplicationContext source, JSONObject msg) {
        super(source);
        this.message = msg;
    }

    public JSONObject getMessage() {
        return message;
    }
}

