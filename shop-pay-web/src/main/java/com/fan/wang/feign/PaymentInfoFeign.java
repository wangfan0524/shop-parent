package com.fan.wang.feign;

import com.fan.wang.pay.service.PaymentInfoService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("shop-pay")
public interface PaymentInfoFeign extends PaymentInfoService {

}
