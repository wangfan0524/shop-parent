
package com.fan.wang.common.api;

import java.util.HashMap;
import java.util.Map;

import com.fan.wang.constants.BaseApiConstants;

/**
 * 通用BaseApi类
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
public class BaseApiService {

	/**
	 * 返回错误
	 *
	 * @param msg  错误消息
	 * @return Map<String,Object> 结果
	 */
	public Map<String, Object> setResutError(String msg) {
		return setResut(BaseApiConstants.HTTP_500_CODE, msg, null);
	}

	/**
	 * 返回成功
	 *
	 * @param data  成功数据
	 * @return Map<String,Object> 结果
	 */
	public Map<String, Object> setResutSuccessData(Object data) {
		return setResut(BaseApiConstants.HTTP_200_CODE, BaseApiConstants.HTTP_SUCCESS_NAME, data);
	}
	/**
	 * 返回成功不带数据
	 *
	 * @return Map<String,Object> 结果
	 */
	public Map<String, Object> setResutSuccess() {
		return setResut(BaseApiConstants.HTTP_200_CODE, BaseApiConstants.HTTP_SUCCESS_NAME, null);
	}

	/**
	 * 返回成功
	 *
	 * @param msg  成功消息
	 * @return Map<String,Object> 结果
	 */
	public Map<String, Object> setResutSuccess(String msg) {
		return setResut(BaseApiConstants.HTTP_200_CODE, msg, null);
	}

	/**
	 * 返回自定义结果
	 *
	 * @param data 数据
	 * @param code 状态码
	 * @param msg 消息
	 * @return Map<String,Object> 结果
	 */
	public Map<String, Object> setResut(Integer code, String msg, Object data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(BaseApiConstants.HTTP_CODE_NAME, code);
		result.put(BaseApiConstants.HTTP_200_NAME, msg);
		if (data != null)
			result.put(BaseApiConstants.HTTP_DATA_NAME, data);
		return result;
	}

}
