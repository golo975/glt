package com.thunisoft.glt.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login/{user}", method = RequestMethod.GET)
	/*
	 * @PathVariable 注解可以自动将URI中的参数注入到方法的参数中 这个URI可以是方法上的@PathVariable
	 * ，也可以是类上的指定的 这里也支持正则表达式，例如{version:\\d\\.\\d\\.\\d}{extension:\\.[a-z]}，
	 * 其中version和extension是参数的名字，用于在其他地方引用
	 * URI中的${...}中的占位符还可以直接获取属性文件中的值，所以可以把controller的path配置到属性文件中
	 * 。详见：PropertyPlaceholderConfigurer 这里还可以使用Matrix variables，现在还没弄明白，以后再看。
	 */
	public String login(@PathVariable("user") String userName) {
		System.out.println(userName);
		System.out.println(1234);
		return null;
	}
}
