
package com.fan.wang.controller;

import javax.servlet.http.HttpServletRequest;

import com.fan.wang.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DemoController  extends BaseController {
	// index页面
	public static final String INDEX = "index";

	@RequestMapping("/index")
	public String index(HttpServletRequest request,String token) {
		log.info(" 我的web工程搭建成功啦!,userName:{}",getUserEntity(token).getUsername());
		return INDEX;
	}
}
