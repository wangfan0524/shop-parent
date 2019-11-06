package com.fan.wang.controller;

import com.alibaba.fastjson.JSONObject;
import com.fan.wang.base.controller.BaseController;
import com.fan.wang.feign.PaymentInfoFeign;
import com.fan.wang.pay.entity.PaymentInfo;
import com.fan.wang.service.pay.impl.PayServiceImpl;
import com.fan.wang.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
public class PayController extends BaseController {
    @Autowired
    private PaymentInfoFeign paymentInfoFeign;
    @Autowired
    private PayServiceImpl payService;

    @RequestMapping("/payGateway")
    public void payGateway(HttpServletRequest request, String token, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        Map<String, Object> payInfoToken = paymentInfoFeign.getPayInfoToken(token);
        Map<String, Object> resultMap = (Map<String, Object>) ResultUtils.getResultMap(payInfoToken);
        PrintWriter out = resp.getWriter();
        if (resultMap == null) {
            resp.getWriter().write("系统错误!");
            return;
        }
        String json = new JSONObject().toJSONString(resultMap);
        PaymentInfo paymentInfo = new JSONObject().parseObject(json, PaymentInfo.class);
        String html = payService.pay(paymentInfo);
        out.println(html);
        out.close();
    }

}
