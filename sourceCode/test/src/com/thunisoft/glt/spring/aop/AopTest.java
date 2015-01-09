package com.thunisoft.glt.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.thunisoft.glt.persistence.IUserDao;


public class AopTest {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserDao dao = (IUserDao) context.getBean("userDaoProxy");
		dao.batchUpdate_insert();
	}
}
