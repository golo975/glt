package com.thunisoft.glt;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.thunisoft.glt.persistence.UserService;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) ctx.getBean("userService");
		
		SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
		HibernateTemplate ht = new HibernateTemplate(sf);
		userService.signIn("glt", ht);
	}
}
