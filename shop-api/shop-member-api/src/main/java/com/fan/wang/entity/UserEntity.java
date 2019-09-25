package com.fan.wang.entity;

import com.fan.wang.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@Table(name="mb_user")
/**
 * 用户(会员)数据实体
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
public class UserEntity extends BaseEntity {
    private String id;
    private String username;
    private String password;
    private String phone;
    private String email;


}
