package com.thunisoft.glt.spring.mvc.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thunisoft.glt.bean.User;
import com.thunisoft.glt.persistence.UserService;

@Controller
public class LoginController {
	
	private static final Log logger = LogFactory.getLog(LoginController.class);
	@Autowired
	private UserService userService;

	/*
	 * @PathVariable 注解可以自动将URI中的参数注入到方法的参数中 这个URI可以是方法上的@PathVariable
	 * ，也可以是类上的指定的 这里也支持正则表达式，例如{version:\\d\\.\\d\\.\\d}{extension:\\.[a-z]}，
	 * 其中version和extension是参数的名字，用于在其他地方引用
	 * URI中的${...}中的占位符还可以直接获取属性文件中的值，所以可以把controller的path配置到属性文件中
	 * 。详见：PropertyPlaceholderConfigurer 这里还可以使用Matrix variables，现在还没弄明白，以后再看。
	 * 
	 * @RequestParam用来获取请求中的参数。
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.GET )
	public ModelAndView login(@RequestParam("username") String username) {
//		userService.signIn(username);
		userService.insertUsert(username);
		return showUserList();
	}
	
	private ModelAndView showUserList(){
		List<User> users = userService.getUsers();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("target");
		mv.addObject("users", users);
		return mv;
	}
	
	@RequestMapping(value = "/something", method = RequestMethod.GET)
	public void handle(@RequestBody String body, Writer writer)
			throws IOException {
		logger.info(body);
	}
	
	@RequestMapping(value="/deleteUser")
	public ModelAndView deleteUser(@RequestParam Integer userid){
		userService.deleteUser(userid);
		return showUserList();
	}
	
	@RequestMapping("/searchUser")
	public ModelAndView searchUser(String username){
		List<User> users = userService.getUser(username);
		ModelAndView mv = new ModelAndView("target");
		
		mv.addObject("users", users);
		return mv;
	}

}