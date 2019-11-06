package com.fan.wang.utils.token;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenUtils {

    public String getToken() {
        return UUID.randomUUID().toString();
    }

    public String getPayToken(){
        return "pay-"+UUID.randomUUID().toString();
    }
}
