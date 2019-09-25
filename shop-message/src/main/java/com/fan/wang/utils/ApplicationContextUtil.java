package com.fan.wang.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 上下文工具类
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (applicationContext != null) {
            ApplicationContextUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取上下文
     *
     * @return ApplicationContext  上下文
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
