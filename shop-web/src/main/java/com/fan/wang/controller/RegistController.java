
package com.fan.wang.controller;

import com.fan.wang.base.controller.BaseController;
import com.fan.wang.constants.BaseApiConstants;
import com.fan.wang.entity.UserEntity;
import com.fan.wang.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户注册controller
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@Controller
public class RegistController extends BaseController {
    private static final String LOCAREGIST = "locaRegist";
    private static final String LGOIN = "login";
    @Autowired
    private UserFeign userFeign;

    @RequestMapping("/locaRegist")
    public String locaRegist() {
        return LOCAREGIST;
    }

    @RequestMapping("/regist")
    public String regist(UserEntity userEntity, HttpServletRequest request) {
        try {
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
