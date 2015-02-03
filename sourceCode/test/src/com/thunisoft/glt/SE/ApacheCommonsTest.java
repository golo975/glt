package com.thunisoft.glt.SE;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

public class ApacheCommonsTest {

	/**
	 * 测试ToStringBuilder.reflectionToString()方法
	 * 因为是使用的反射，所以即使字段是private的也可以显示出来
	 */
	@Test
	public void toStringTest(){
		@SuppressWarnings("unused")
		class User{
			private Integer id = 1;
			private String name = "Monica";
		}
		User user = new User();
		System.out.println(ToStringBuilder.reflectionToString(user));
	}
}
