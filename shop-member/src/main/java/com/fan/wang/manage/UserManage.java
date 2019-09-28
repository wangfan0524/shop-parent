package com.fan.wang.manage;

import com.fan.wang.entity.UserEntity;

import java.util.Map;

/**
 * 用户服务业务层接口
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
public interface UserManage {
    /**
     * 用户注册
     *
     * @param userEntity 用户实体
     */
    Map<String,Object> register(UserEntity userEntity);

    /**
     * 将用户号码作为salt对密码进行MD5加密
     *
     * @param phone    用户手机号码
     * @param password 密码
     * @return String 返回加密后的密码
     */
    String md5PasswordAddSalt(String phone, String password);

    /**
     * 用户登录服务
     *
     * @param userEntity 用户实体
     * @return 通用返回结果
     */
    Map<String, Object> login(UserEntity userEntity);

    /**
     * 获取用户信息
     *
     * @param token 用户token
     * @return 通用返回结果(将用户信息封装在了MAP)中
     */
    Map<String, Object> getUser(String token);

    Map<String, Object> userLoginOpenId(String openid);
}

