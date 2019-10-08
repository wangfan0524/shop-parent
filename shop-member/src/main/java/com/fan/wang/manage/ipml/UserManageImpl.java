package com.fan.wang.manage.ipml;

import com.alibaba.fastjson.JSONObject;
import com.fan.wang.common.api.BaseApiService;
import com.fan.wang.common.redis.BaseRedisService;
import com.fan.wang.constants.Constants;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    public Map<String, Object> register(UserEntity userEntity) {
        if (!CollectionUtils.isEmpty(userDao.checkUserRepeat(userEntity))) {
            return setResutError("用户名重复！");
        }
        userEntity.setCreated(DateUtils.getTimestamp());
        userEntity.setUpdated(DateUtils.getTimestamp());
        String phoneNum = userEntity.getPhone();
        String password = userEntity.getPassword();
        userEntity.setPassword(md5PasswordAddSalt(phoneNum, password));
        userDao.save(userEntity);
        String emailMsg = getMailMessage(userEntity.getEmail(), userEntity.getUsername(), userEntity.getPhone());
        providerMsg.sendMsg(emailMsg);
        log.info("###用户：" + userEntity + "注册成功");
        return setResutSuccess();
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
        //登录成功以后，可以进行绑定，此时将openID，写入userEntity，一并更新到数据库
        String openId = userEntity.getOpenid();
        if (!StringUtils.isEmpty(openId)) {
            UserEntity temp = new UserEntity();
            temp.setOpenid(openId);
            temp.setUpdated(DateUtils.getTimestamp());
            temp.setId(userEntity1.getId());
            userDao.updateUserOpenId(temp);
        }
        //登陆成功，生成token
        String token = setUsertoken(Long.valueOf(userEntity1.getId()));
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

    @Override
    public Map<String, Object> userLoginOpenId(String openid) {
        log.info("第六步");
        UserEntity userEntity = userDao.findUserOpenId(openid);
        log.info("第七步");
        if (userEntity == null) {
            return setResutError("没有关联用户");
        }
        log.info("第八步");
        // 生成对应的token
        String token = setUsertoken(Long.valueOf(userEntity.getId()));
        return setResutSuccessData(token);

    }

    private String setUsertoken(Long id) {
        String token = tokenUtils.getToken();
        // key为自定义令牌,用户的userId作作为value 存放在redis中
        redisService.set(token, id + "", Constants.USER_TOKEN_TERMVALIDITY);
        return token;
    }
}
