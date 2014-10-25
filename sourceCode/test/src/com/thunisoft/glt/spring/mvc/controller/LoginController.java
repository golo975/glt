package com.thunisoft.glt.spring.mvc.controller;

import java.io.IOException;
import java.io.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	/*
	 * @PathVariable ע������Զ���URI�еĲ���ע�뵽�����Ĳ����� ���URI�����Ƿ����ϵ�@PathVariable
	 * ��Ҳ���������ϵ�ָ���� ����Ҳ֧��������ʽ������{version:\\d\\.\\d\\.\\d}{extension:\\.[a-z]}��
	 * ����version��extension�ǲ��������֣������������ط�����
	 * URI�е�${...}�е�ռλ��������ֱ�ӻ�ȡ�����ļ��е�ֵ�����Կ��԰�controller��path���õ������ļ���
	 * �������PropertyPlaceholderConfigurer ���ﻹ����ʹ��Matrix variables�����ڻ�ûŪ���ף��Ժ��ٿ���
	 * 
	 * @RequestParam������ȡ�����еĲ�����
	 */
	@RequestMapping(value = "/login/{user}", method = RequestMethod.GET)
	public String login(@PathVariable("user") String userName,
			@RequestParam("gender") String gender) {
		System.out.println(userName);
		System.out.println(gender);
		return "target";
	}

	@RequestMapping(value = "/something", method = RequestMethod.GET)
	public void handle(@RequestBody String body, Writer writer)
			throws IOException {
		System.out.println(body);
//		writer.write(body);
	}

}
