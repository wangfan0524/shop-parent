package com.fan.wang.service.pay.impl;

import com.alibaba.fastjson.JSONObject;
import com.fan.wang.feign.PaymentTypeFeign;
import com.fan.wang.pay.entity.PaymentInfo;
import com.fan.wang.pay.entity.PaymentType;
import com.fan.wang.service.pay.PayAdaptService;
import com.fan.wang.service.pay.PayService;
import com.fan.wang.service.pay.yinlian.YinLianPay;
import com.fan.wang.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private PaymentTypeFeign paymentTypeFeign;
    @Autowired
    private YinLianPay yinLianPay;

    @Override
    public String pay(PaymentInfo paymentInfo) {
        Long typeId = paymentInfo.getTypeId();
        Map<String, Object> paymentTypeMap = paymentTypeFeign.getPaymentType(typeId);
        if (paymentTypeMap == null) {
            log.error("####pay() typeId:{},paymentTypeMap is null");
            return null;
        }
        Map<String, Object> data = (Map<String, Object>) ResultUtils.getResultMap(paymentTypeMap);
        String json = new JSONObject().toJSONString(data);
        PaymentType paymentType = new JSONObject().parseObject(json, PaymentType.class);
        if (paymentType == null) {
            return null;
        }
        String typeName = paymentType.getTypeName();
        PayAdaptService payAdaptService = null;
        switch (typeName) {
            case "yinlianPay":
                payAdaptService = yinLianPay;
                break;

            default:
                break;
        }
        return payAdaptService.pay(paymentInfo, paymentType);
    }

}
