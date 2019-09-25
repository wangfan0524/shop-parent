package com.fan.wang.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 通用数据实体
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@Getter
@Setter
//使用lombok要保证安装了lombok插件
public class BaseEntity {

    /**
     * 创建时间
     */
    private Timestamp created;
    /**
     * 修改时间
     */
    private Timestamp updated;

}
