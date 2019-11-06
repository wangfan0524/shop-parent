package com.fan.wang.utils;

import com.fan.wang.constants.BaseApiConstants;

import java.util.Map;

public class ResultUtils {

    public static Object getResultMap(Map<String, Object> reusltItemDesc) {
        Integer code = (Integer) reusltItemDesc.get(BaseApiConstants.HTTP_CODE_NAME);
        if (code.equals(BaseApiConstants.HTTP_200_CODE)) {
            Object object = reusltItemDesc.get("data");
            return object;
        }
        return null;
    }

}
