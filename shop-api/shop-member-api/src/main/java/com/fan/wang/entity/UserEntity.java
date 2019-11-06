package com.fan.wang.entity;

import com.fan.wang.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotBlank(message = "用户名不能为空")
    private String phone;
    @NotNull
    private String email;
    private String openid;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }
}
