package com.fan.wang.api.service;

import com.fan.wang.entity.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 用户(会员)相关服务对外暴露接口定义
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@RequestMapping("/member")
public interface UserService {
    /**
     * 用户注册对外暴露接口
     *
     * @param userEntity  用户实体
     * @return Map<String,Object> 注册结果
     */
    @PostMapping("/regist")
    Map<String,Object> regist(@RequestBody UserEntity userEntity);

    /**
     * 用户登录对外暴露接口，登陆成功后将用户的userid存放到redis中，
     * 生成对应的token做为key，将用户userid作为value存放在redis中，
     * 返回token返回给客户端
     *
     * @param userEntity  用户实体
     * @return Map<String,Object> 注册结果
     */
    @PostMapping("/login")
    Map<String,Object> login(@RequestBody UserEntity userEntity);

    /**
     *
     * 使用token查找用户信息
     *
     * @param token  用户token
     * @return Map<String,Object> 注册结果
     */
    @PostMapping("/getuser")
    Map<String,Object> getUser(@RequestParam(value = "token",required=false) String  token);

}
