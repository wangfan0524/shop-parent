package com.fan.wang.manage.ipml;

import com.alibaba.fastjson.JSONObject;
import com.fan.wang.common.api.BaseApiService;
import com.fan.wang.common.redis.BaseRedisService;
import com.fan.wang.dao.UserDao;
import com.fan.wang.entity.UserEntity;
import com.fan.wang.manage.UserManage;
import com.fan.wang.mq.provider.ProviderMsg;
import com.fan.wang.utils.DateUtils;
import com.fan.wang.utils.MD5Util;
import com.fan.wang.utils.token.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户服务业务层实现类
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@Service
@Slf4j
public class UserManageImpl extends BaseApiService implements UserManage {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProviderMsg providerMsg;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private BaseRedisService redisService;

    @Override
    public void register(UserEntity userEntity) {
        userEntity.setCreated(DateUtils.getTimestamp());
        userEntity.setUpdated(DateUtils.getTimestamp());
        String phoneNum = userEntity.getPhone();
        String password = userEntity.getPassword();
        userEntity.setPassword(md5PasswordAddSalt(phoneNum, password));
        userDao.save(userEntity);
        String emailMsg = getMailMessage(userEntity.getEmail(), userEntity.getUsername(), userEntity.getPhone());
        providerMsg.sendMsg(emailMsg);
        log.info("###用户：" + userEntity + "注册成功");
    }

    @Override
    public String md5PasswordAddSalt(String phone, String password) {
        return MD5Util.MD5(phone + password);
    }

    @Override
    public Map<String, Object> login(UserEntity userEntity) {
        userEntity.setPassword(md5PasswordAddSalt(userEntity.getPhone(), userEntity.getPassword()));
        UserEntity userEntity1 = userDao.getUserPhoneAndPass(userEntity);
        if (userEntity1 == null) {
            return setResutError("账号或密码错误");
        }
        //登陆成功，生成token
        String token = tokenUtils.getToken();
        redisService.set(token, userEntity1.getId(), 600000L);
        return setResutSuccessData(token);
    }

    @Override
    public Map<String, Object> getUser(String token) {
        //从redis中查找userId
        String id = redisService.get(token);
        if (id == null) {
            //用户失效，
            return setResutError("用户已经过期,请重新登录！");
        }
        UserEntity userEntity = userDao.getUserInfo(Long.valueOf(id));
        userEntity.setPassword(null);
        return setResutSuccessData(userEntity);
    }

    public String getMailMessage(String email, String username, String phone) {
        JSONObject rootElement = new JSONObject();
        JSONObject headerElement = new JSONObject();
        JSONObject contentElement = new JSONObject();
        headerElement.put("interface", "email");
        contentElement.put("email", email);
        contentElement.put("phone", phone);
        contentElement.put("username", username);
        rootElement.put("header", headerElement);
        rootElement.put("content", contentElement);
        return rootElement.toJSONString();
    }

}
