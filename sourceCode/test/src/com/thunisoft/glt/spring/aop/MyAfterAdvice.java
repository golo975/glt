package com.thunisoft.glt.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class MyAfterAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		System.out.println("���ص�ֵ�ǣ�"+returnValue);
		System.out.println("���Ǻ�����ǿ");
	}

}
