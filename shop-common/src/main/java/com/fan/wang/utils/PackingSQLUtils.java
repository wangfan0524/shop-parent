package com.fan.wang.utils;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Timestamp;

/**
 * 组装SQL工具类
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@Slf4j
public class PackingSQLUtils {

    /**
     * 获取子类和父类属性组装后的结果
     *
     * @param obj 需要写入数据库的实体
     * @return String   数据库INSERT字段拼接结果
     */
    public static String getFatherAndSonFields(Object obj) {
        if (obj == null) {
            return null;
        }
        //获取class文件
        Class clazz = obj.getClass();
        //获取父类属性
        Field[] sonFields = clazz.getDeclaredFields();
        String s1 = getFields(sonFields);
        Field[] fatherFields = clazz.getSuperclass().getDeclaredFields();
        String s2 = getFields(fatherFields);
        return s1 + "," + s2;
    }

    /**
     * 获取子类和父类属性值组装后的结果
     *
     * @param object         需要写入数据库的实体
     * @param declaredFields 属性集合
     * @return String   数据库VALUES对应的结果
     */
    public static String getFieldsValues(Object object, Field[] declaredFields) {
        StringBuffer sf = new StringBuffer();
        int length = declaredFields.length;
        Field field;
        for (int i = 0; i < length; i++) {
            try {
                field = declaredFields[i];
                field.setAccessible(true);
                Object value = field.get(object);
                //防止字符串类型的数据，拼接逗号
                boolean strFlag = false;
                if (value != null && (value instanceof String || value instanceof Timestamp)) {
                    strFlag = true;
                }
                if (strFlag) {
                    sf.append("'");
                    sf.append(value);
                    sf.append("'");
                } else {
                    sf.append(value);
                }
                if (i < declaredFields.length - 1) {
                    sf.append(",");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                log.error(e.toString());
            }

        }
        return sf.toString();
    }

    /**
     * 对当前实体的属性进行拼接
     *
     * @param declaredFields 属性集合
     * @return String   拼装完的结果
     */
    public static String getFields(Field[] declaredFields) {
        StringBuffer sf = new StringBuffer();
        int length = declaredFields.length;
        for (int i = 0; i < length; i++) {
            sf.append(declaredFields[i].getName());
            if (i < declaredFields.length - 1) {
                sf.append(",");
            }
        }
        return sf.toString();
    }

    /**
     * 获取父类和子类的实体属性的值拼接结果
     *
     * @param obj 需要写入数据库的实体
     * @return String   拼装完的结果
     */
    public static String getFatherAndSonFieldsValues(Object obj) {
        if (obj == null) {
            return null;
        }
        //获取class文件
        Class clazz = obj.getClass();
        //获取父类属性
        Field[] sonFields = clazz.getDeclaredFields();
        String s1 = getFieldsValues(obj, sonFields);
        Field[] fatherFields = clazz.getSuperclass().getDeclaredFields();
        String s2 = getFieldsValues(obj, fatherFields);
        return s1 + "," + s2;
    }

    /**
     * 反射获取实体上的表名
     *
     * @param obj 需要写入数据库的实体
     * @return String   表名
     */
    public static String getTableName(Object obj) {
        if (obj == null) {
            return null;
        }
        //获取class文件
        Class clazz = obj.getClass();
        Table table = (Table) clazz.getAnnotation(Table.class);
        String tableName = null;
        if (table != null) {
            tableName = table.name();
        }
        return tableName;
    }
}
