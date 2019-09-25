
package com.fan.wang.controller;

import com.fan.wang.base.controller.BaseController;
import com.fan.wang.constants.BaseApiConstants;
import com.fan.wang.constants.Constants;
import com.fan.wang.entity.UserEntity;
import com.fan.wang.utils.web.CookieUtil;
import com.fan.wang.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginConroller extends BaseController {
    private static final String LGOIN = "login";
    private static final String INDEX = "index";
    @Autowired
    private UserFeign userFeign;

    @RequestMapping("/locaLogin")
    public String locaLogin() {
        return LGOIN;
    }

    @RequestMapping("/login")
    //表单提交不能使用@RequestBody 否则会报错There
    // was an unexpected error (type=Unsupported
    // Media Type, status=415). Content type
    // 'application/x-www-form-urlencoded;charset=UTF-8' not supported
    public String login(UserEntity userEntity, HttpServletRequest request, HttpServletResponse response) {
        //通过feign调用member服务的login接口
        Map<String, Object> login = userFeign.login(userEntity);
        Integer code = (Integer) login.get(BaseApiConstants.HTTP_CODE_NAME);
        if (!code.equals(BaseApiConstants.HTTP_200_CODE)) {
            String msg = (String) login.get("msg");
            return setError(request, msg, LGOIN);
        }
        // 登录成功之后,获取token.将这个token存放在cookie
        String token = (String) login.get("data");
        CookieUtil.addCookie(response, Constants.USER_TOKEN, token, Constants.WEBUSER_COOKIE_TOKEN_TERMVALIDITY);
        return INDEX;

    }

}
