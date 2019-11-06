package com.fan.wang.service.pay;

import com.fan.wang.common.api.BaseApiService;
import com.fan.wang.dao.PaymentTypeDao;
import com.fan.wang.pay.entity.PaymentType;
import com.fan.wang.pay.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Service
@RestController
@RequestMapping("/pay")
public class PaymentTypeServiceImpl extends BaseApiService implements PaymentTypeService {
    @Autowired
    private PaymentTypeDao paymentTypeDao;

    @RequestMapping("/getPaymentType")
    public Map<String, Object> getPaymentType(@RequestParam("id") Long id) {
        PaymentType paymentType = paymentTypeDao.getPaymentType(id);
        if (paymentType == null) {
            return setResutError("未查找到类型");
        }
        return setResutSuccessData(paymentType);
    }

}
