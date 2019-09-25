package com.fan.wang.common.mybatis;

import com.fan.wang.utils.PackingSQLUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * 具体BaseProvider类
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
public class BaseProvider {

    /**
     * 新增方法(通过InsertProvider SQL工厂类及对应的方法生产SQL语句)
     *
     * @param map  需要做新增操作的数据实体
     */
    public String save(Map<String,Object> map){
        //从map中获取obj，也就是BaseDao中传入的数据实体中@param指定的参数
        Object object=map.get("obj");
        //获取表明
        String tableName= PackingSQLUtils.getTableName(object);
        // 通过反射机制，生成sql语句
        SQL sql=new SQL(){{
            INSERT_INTO(tableName);
            VALUES(PackingSQLUtils.getFatherAndSonFields(object), PackingSQLUtils.getFatherAndSonFieldsValues(object));
        }};

        return sql.toString();
    }
}
