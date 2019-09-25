
package com.fan.wang.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.fan.wang.constants.BaseApiConstants;
import com.fan.wang.entity.UserEntity;
import com.fan.wang.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @classDesc: 功能描述:()
 * @author: 蚂蚁课堂创始人-余胜军
 * @QQ: 644064779
 * @QQ粉丝群: 116295598
 * @createTime: 2017年10月26日 下午10:50:39
 * @version: v1.0
 * @copyright:每特学院(蚂蚁课堂)上海每特教育科技有限公司
 */
@Controller
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
