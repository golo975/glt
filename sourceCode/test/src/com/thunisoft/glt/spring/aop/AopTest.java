package com.thunisoft.glt.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.thunisoft.glt.persistence.UserDao;


public class AopTest {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao dao = (UserDao) context.getBean("userDaoProxy");
//		dao.batchUpdate_insert();
	}
}
