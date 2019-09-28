
package com.fan.wang.common.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class BaseRedisService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;
	/**
	 * 
	 * @methodDesc: 功能描述:(往redis添加信息)
	 * @param: @param
	 *             key
	 */
	public void setString(String key, String value) {
		set(key, value, null);
	}

	public void setString(String key, String value, Long timeOut) {
		set(key, value, timeOut);
	}

	public void set(String key, Object value, Long timeOut) {
		if (value != null) {
			// 判断是不是string类型，是的话进行强转
			if (value instanceof String) {
				String setValue = (String) value;
				redisTemplate.opsForValue().set(key, setValue);
			}
			// 封装其他类型
			// 设置有效期
			if (timeOut != null)
				redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);

		}

	}

	/**
	 * 
	 * @methodDesc: 功能描述:(使用key 查找redis信息)
	 * @param: @param
	 *             key
	 */
	public String get(String key) {
		return (String) redisTemplate.opsForValue().get(key);
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(使用key 删除redis信息)
	 * @param: @param
	 *             key
	 */
	public void delete(String key) {
		redisTemplate.delete(key);
	}
}
