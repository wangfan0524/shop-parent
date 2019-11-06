
package com.fan.wang.controller;

import com.fan.wang.base.controller.BaseController;
import com.fan.wang.constants.BaseApiConstants;
import com.fan.wang.entity.UserEntity;
import com.fan.wang.feign.UserFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class RegistController extends BaseController {
    private static final String LOCAREGIST = "locaRegist";
    private static final String LGOIN = "login";
    @Autowired
    private UserFeign userFeign;

    @RequestMapping("/locaRegister")
    public String locaRegist(String source, HttpServletRequest request) {
        request.setAttribute("source", source);

        String str="{\"name\":\"wangfan\"}";
        return LOCAREGIST;
    }

    @RequestMapping("/regist")
    public String regist(UserEntity userEntity, HttpServletRequest request,String source) {
        try {
            if (StringUtils.isNotEmpty(source)){
                request.setAttribute("source", source);
            }
            Map<String, Object> registMap = userFeign.regist(userEntity);
            Integer code = (Integer) registMap.get(BaseApiConstants.HTTP_CODE_NAME);
            if (!code.equals(BaseApiConstants.HTTP_200_CODE)) {
                String msg = (String) registMap.get("msg");
                return setError(request, msg, LOCAREGIST);
            }
            // 注册成功
            return LGOIN;

        } catch (Exception e) {
            return setError(request, "注册失败!"+e.toString(), LOCAREGIST);
        }
    }

}
