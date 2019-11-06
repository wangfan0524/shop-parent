package com.fan.wang.service.pay;

import com.fan.wang.pay.entity.PaymentInfo;
import com.fan.wang.pay.entity.PaymentType;

public interface PayAdaptService {
    public String pay(PaymentInfo paymentInfo, PaymentType paymentType);
}
