package com.thunisoft.glt.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class MyThrowsAdvice implements ThrowsAdvice {

	public void afterThrowing(Method method, Object[] args, Object target, Exception ex){
		System.out.println("正在执行异常处理切面");
	}
}
