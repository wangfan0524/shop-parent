
package com.fan.wang.feign;

import com.fan.wang.api.service.UserService;
import org.springframework.cloud.netflix.feign.FeignClient;


//指向eureka中的member服务
@FeignClient("member")
public interface UserFeign extends UserService {
//	/**
//	 * 
//	 * @methodDesc: 功能描述:(使用token查找用户信息)
//	 * @param: @param
//	 *             token
//	 * @param: @return
//	 */
//	@PostMapping("/member/getUser")
//	public Map<String, Object> getUser(@RequestParam("token") String token);
}
