package com.fan.wang.utils.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenUtils {

    public String getToken(){
        return UUID.randomUUID().toString();
    }
}
