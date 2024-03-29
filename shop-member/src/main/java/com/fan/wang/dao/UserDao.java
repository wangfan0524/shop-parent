package com.fan.wang.dao;

import com.fan.wang.common.mybatis.BaseDao;
import com.fan.wang.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.List;

/**
 * 用户dao
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@Mapper
public interface UserDao extends BaseDao {

    /**
     * 登录时查询用户信息
     *
     * @param userEntity 用户实体
     * @return UserEntity 用户信息
     */
    @Select("SELECT id,username,password,phone,email from mb_user where phone=#{userEntity.phone} and password=#{userEntity.password}")
    UserEntity getUserPhoneAndPass(@Param("userEntity") UserEntity userEntity);

    /**
     * 通过用户ID查询用户信息
     *
     * @param id 用户ID（通过Token从redis中获取）
     * @return UserEntity 用户信息
     */
    @Select("SELECT id,username,password,phone,email from mb_user where id =#{id}")
    UserEntity getUserInfo(@Param("id") Long id);

    @Select("SELECT id,username,password,phone,email from mb_user where username =#{userEntity.username} or phone=#{userEntity.phone}")
    List<UserEntity> checkUserRepeat(@Param("userEntity")UserEntity userEntity);

    @Select("select ID,USERNAME,PASSWORD,phone,EMAIL, created,updated from mb_user  WHERE openid=#{openid}")
    UserEntity findUserOpenId(@Param("openid") String openid);

    @Update("update mb_user set openid=#{user.openid} ,updated=#{user.updated} where id=#{user.id}")
    void updateUserOpenId(@Param("user") UserEntity user);
}
