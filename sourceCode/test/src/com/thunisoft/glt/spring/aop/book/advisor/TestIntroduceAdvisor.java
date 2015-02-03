package com.thunisoft.glt.spring.aop.book.advisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.thunisoft.glt.spring.aop.book.introduce.ForumService;
import com.thunisoft.glt.spring.aop.book.introduce.Monitorable;

public class TestIntroduceAdvisor {
	public static void main(String[] args) {
		String configPath = "com/baobaotao/advisor/beans.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ForumService forumService = (ForumService)ctx.getBean("forumService");
        forumService.removeForum(10);
        Monitorable moniterable = (Monitorable)forumService;
        moniterable.setMonitorActive(true);
        forumService.removeForum(10);
	}
}
