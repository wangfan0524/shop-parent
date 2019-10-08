
package com.fan.wang.controller;

import com.fan.wang.base.controller.BaseController;
import com.fan.wang.common.redis.BaseRedisService;
import com.fan.wang.common.redis.RedisConfig;
import com.fan.wang.constants.BaseApiConstants;
import com.fan.wang.constants.Constants;
import com.fan.wang.entity.UserEntity;
import com.fan.wang.feign.UserFeign;
import com.fan.wang.utils.web.CookieUtil;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户登录controller
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@Controller
@Slf4j
public class LoginConroller extends BaseController {
    private static final String LGOIN = "login";
    private static final String INDEX = "index";
    private static final String ERROR = "error";
    private static final String ASSOCIATEDACCOUNT = "associatedAccount";
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private BaseRedisService baseRedisService;

    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/locaLogin")
    public String locaLogin(String source, HttpServletRequest request) {
        request.setAttribute("source", source);
        return LGOIN;
    }

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public Map<String, Object> test( HttpServletRequest request) {
        String msg="中国人";


        Map<String, Object> result = new HashMap<String, Object>();
        result.put(BaseApiConstants.HTTP_CODE_NAME, "code");
        result.put(BaseApiConstants.HTTP_200_NAME, "msg");
        result.put(BaseApiConstants.HTTP_DATA_NAME, msg);
        return result;
    }

    @RequestMapping("/login")
    //表单提交不能使用@RequestBody 否则会报错There
    // was an unexpected error (type=Unsupported
    // Media Type, status=415). Content type
    // 'application/x-www-form-urlencoded;charset=UTF-8' not supported
    public String login(UserEntity userEntity, String source, HttpSession httpSession,HttpServletRequest request, HttpServletResponse response) {
        if (!StringUtils.isEmpty(source) && source.equals(Constants.USER_SOURCE_QQ)) {
            String openid = (String) httpSession.getAttribute(Constants.USER_SESSION_OPENID);
            userEntity.setOpenid(openid);
        }
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

    @RequestMapping("/qqlogincallback")
    public String qqLoginCallback(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,String code1,String state) throws QQConnectException {
        //Todo 1:获取授权码,从回调请求中可以拿到
        //Todo 2：通过授权码获取token
        AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);
        log.info("第一步");
        String accessToken = accessTokenObj.getAccessToken();
        if (StringUtils.isEmpty(accessToken)) {
            return setError(request, "QQ授权失败!", ERROR);
        }
        log.info("第二步");
        OpenID openidObj = new OpenID(accessToken);
        //Todo 3.拿到QQ返回的openID
        String userOpenId = openidObj.getUserOpenID();
        //Todo 3.在数据库查找，QQ返回的openId是否可以关联查询到本地的用户
        Map<String, Object> userLoginOpenId = userFeign.userLoginOpenId(userOpenId);
        log.info("第三步");
        Integer httpCcode = (Integer) userLoginOpenId.get(BaseApiConstants.HTTP_CODE_NAME);
        //Todo 4.如果根据openID查询到了结果，则说明已经关联到了本地账户，可以直接重定向到登录后的页面
        if (httpCcode.equals(BaseApiConstants.HTTP_200_CODE)) {
            log.info("第四步");
            String token = (String) userLoginOpenId.get("data");
            CookieUtil.addCookie(response, Constants.USER_TOKEN, token, Constants.WEBUSER_COOKIE_TOKEN_TERMVALIDITY);
            return INDEX;
        }

        //Todo 4.如果根据openID没有查询到结果，则说明没有关联用户，可以直接重定向到登录后的页面
        httpSession.setAttribute(Constants.USER_SESSION_OPENID, userOpenId);
        return ASSOCIATEDACCOUNT;
    }

    /**
     * @methodDesc: 功能描述:(生成qq授权)
     * @param: @param
     * request
     * @param: @return
     * @param: @throws
     * QQConnectException
     */
    /**
     * 生成QQ授权链接
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return String  授权链接
     */
    @RequestMapping("/qqauthorize")
    public String authorizeUrl(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) throws QQConnectException {
        //通过QQ SDK封装的方法，生成一个授权的链接
        String authorizeUrl = new Oauth().getAuthorizeURL(request);
        //将请求重定向,加/表示在当前域名下跳转 不加/表示可以任意跳转，因为授权是要指向QQ的，所以不能加/
        //return "redirect:/" + authorizeUrl;
        //生成的授权链接后有回调链接 ，回调指向的就是我们的/qqlogincallback请求
        return "redirect:" + authorizeUrl;
    }

    public static Map<String, List<String>> getQueryParams(String url) {
        try {
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            String[] urlParts = url.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = null;
                    try {
                        key = URLDecoder.decode(pair[0], "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }

                    List<String> values = params.get(key);
                    if (values == null) {
                        values = new ArrayList<String>();
                        params.put(key, values);
                    }
                    values.add(value);
                }
            }

            return params;
        } catch (UnsupportedEncodingException ex) {
            throw new AssertionError(ex);
        }
    }
}
