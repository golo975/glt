package com.thunisoft.glt.spring.aop.book.advisor;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor {

	private static final long serialVersionUID = 3002892240196307020L;
	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return "greetTo".equals(method.getName());
	}	
	public ClassFilter getClassFilter(){
		return new ClassFilter(){
			public boolean matches(Class<?> clazz){
				return Waiter.class.isAssignableFrom(clazz);
			}
		};
		
	}
}
