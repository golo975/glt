package com.thunisoft.glt.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {
//	public void batchUpdate_insert()
	@Before("execution(* com.thunisoft.glt.persistence.UserDao.batchUpdate_insert(..))")
	public void myBeforeAdvice(){
		System.out.println("使用注解添加前置增强");
	}
}
