package com.thunisoft.glt.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		System.out.println(1234);
		return null;
	}
}
