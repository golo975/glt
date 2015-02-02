package com.thunisoft.glt.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import com.thunisoft.glt.persistence.UserDao;

public class MyAdvisor extends StaticMethodMatcherPointcutAdvisor{

	private static final long serialVersionUID = 1540614395226147031L;

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return "batchUpdate_insert".equals(method.getName());
	}
	
	@Override
	public ClassFilter getClassFilter() {
		return new ClassFilter(){

			@Override
			public boolean matches(Class<?> clazz) {
				return clazz.equals(UserDao.class);
			}
			
		};
	}

	
}
