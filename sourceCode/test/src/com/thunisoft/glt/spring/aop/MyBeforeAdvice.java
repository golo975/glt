package com.thunisoft.glt.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class MyBeforeAdvice implements MethodBeforeAdvice {
	
	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		System.out.println("调用的方法是：" + method);
		System.out.println("传入的参数是：" + args);
		System.out.println("lalala， 我是前置切面！");
	}
}
