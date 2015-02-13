package com.thunisoft.glt.spring.aop.book.advisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestStaticMethodAdvisor {
	public static void main(String[] args) {
		String configPath = "com/thunisoft/glt/spring/aop/book/advisor/beans.xml";
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
		Waiter waiter = (Waiter)ctx.getBean("waiter");
		Seller seller = (Seller)ctx.getBean("seller");
		waiter.greetTo("John");
		waiter.serveTo("John");
		seller.greetTo("John");	
	}
}