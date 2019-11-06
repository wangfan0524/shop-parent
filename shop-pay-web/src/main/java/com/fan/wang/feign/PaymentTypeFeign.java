package com.fan.wang.feign;

import com.fan.wang.pay.service.PaymentTypeService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("shop-pay")
public interface PaymentTypeFeign extends PaymentTypeService {

}
