package com.fan.wang.dao;

import com.fan.wang.pay.entity.PaymentType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

@Mapper
public interface PaymentTypeDao {

    @Select("select * from payment_type where id = #{id}")
    public PaymentType getPaymentType(@Param("id") Long id);

}
