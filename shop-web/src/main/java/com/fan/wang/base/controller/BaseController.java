
package com.fan.wang.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.fan.wang.constants.BaseApiConstants;
import com.fan.wang.entity.UserEntity;
import com.fan.wang.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 封装前端调用通用方法
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@Component
public class BaseController {
    @Autowired
    private UserFeign userFeign;

    public UserEntity getUserEntity(String token) {
        Map<String, Object> userMap = userFeign.getUser(token);
        Integer code = (Integer) userMap.get(BaseApiConstants.HTTP_CODE_NAME);
        if (!code.equals(BaseApiConstants.HTTP_200_CODE)) {
            return null;
        }
        // 获取data参数
        LinkedHashMap linkedHashMap = (LinkedHashMap) userMap.get(BaseApiConstants.HTTP_DATA_NAME);
        String json = new JSONObject().toJSONString(linkedHashMap);
        UserEntity userEntity = new JSONObject().parseObject(json, UserEntity.class);
        return userEntity;

    }

    public String setError(HttpServletRequest request, String msg, String addres) {
        request.setAttribute("error", msg);
        return addres;
    }

}
