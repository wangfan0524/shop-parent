package com.fan.wang.api.service.impl;

import com.fan.wang.api.service.UserService;
import com.fan.wang.common.api.BaseApiService;
import com.fan.wang.entity.UserEntity;
import com.fan.wang.manage.UserManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户相关操作实现类
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@RestController
@Slf4j
public class UserServiceImpl extends BaseApiService implements UserService {
    @Autowired
    private UserManage userManage;

    @Override
    public Map<String, Object> regist(@RequestBody UserEntity userEntity) {
        try {
            return userManage.register(userEntity);
        } catch (Exception e) {
            log.error("###register()失败");
            return setResutError(e.toString());
        }

    }

    @Override
    public Map<String, Object> login(@RequestBody UserEntity userEntity) {
        //在数据库查找数据，查找到
        //生成对应token
        //将用户的userid作为value，token做为key，存放在redis中，返回token
        return userManage.login(userEntity);
    }

    @Override
    public Map<String, Object> getUser(@RequestParam("token") String token) {
        if (null == token) {
            return setResutError("token不能为空");
        }
        return userManage.getUser(token);
    }
}
