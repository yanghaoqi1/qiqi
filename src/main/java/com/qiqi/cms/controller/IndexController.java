package com.qiqi.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	/**
	 * 
	 * @Title: toIndex 
	 * @Description: 进入首页
	 * @return
	 * @return: String
	 */
	@GetMapping({"/","index","toIndex"})
	public String toIndex() {
		
		return "index/index";
	}

}
