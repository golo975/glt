package com.thunisoft.glt.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class MyBeforeAdvice implements MethodBeforeAdvice {
	
	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		System.out.println("���õķ����ǣ�" + method);
		System.out.println("����Ĳ����ǣ�" + args);
		System.out.println("lalala�� ����ǰ�����棡");
	}
}
