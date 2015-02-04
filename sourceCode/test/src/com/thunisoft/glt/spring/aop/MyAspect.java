package com.thunisoft.glt.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {

	@Before("")
	public void myBeforeAdvice(){
		System.out.println("使用注解添加前置增强");
	}
}
