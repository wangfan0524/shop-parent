package com.fan.wang.common.mybatis;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
/**
 * 通用dao接口
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
public interface BaseDao {

    /**
     * 新增方法(通过InsertProvider SQL工厂类及对应的方法生产SQL语句)
     *
     * @param obj  新增的数据实体
     */
    @InsertProvider(type = BaseProvider.class,method = "save")
    void save(@Param("obj") Object obj);
}
