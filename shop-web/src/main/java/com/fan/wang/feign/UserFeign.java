
package com.fan.wang.feign;

import com.fan.wang.api.service.UserService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Feign客户端
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
//指向eureka中的member服务
@FeignClient("member")
public interface UserFeign extends UserService {

}
