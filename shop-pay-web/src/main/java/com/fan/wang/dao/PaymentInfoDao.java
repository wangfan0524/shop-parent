package com.fan.wang.dao;


import com.fan.wang.common.mybatis.BaseDao;
import com.fan.wang.pay.entity.PaymentInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;

@Mapper
public interface PaymentInfoDao extends BaseDao {

    @Select("select * from payment_info where  id=#{id}")
     PaymentInfo getPaymentInfo(@Param("id") Long id);

    @Insert("insert into payment_info ( id,typeid,orderid,platformorderid,price,source,state,created,updated) value(null,#{typeId},#{orderId},#{platformorderId},#{price},#{source},#{state},#{created},#{updated})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 添加该行，product中的id将被自动添加
     Integer savePaymentType(PaymentInfo paymentInfo);

    @Select("select * from payment_info where  orderId=#{orderId}")
     PaymentInfo getByOrderIdPayInfo(@Param("orderId") String orderId);

    @Update("update payment_info set state =#{state},payMessage=#{payMessage},platformorderId=#{platformorderId},updated=#{updated} where orderId=#{orderId} ")
     void updatePayInfo(PaymentInfo paymentInfo);


}
