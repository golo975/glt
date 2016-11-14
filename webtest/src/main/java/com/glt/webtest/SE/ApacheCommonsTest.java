package main.java.com.glt.webtest.SE;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

public class ApacheCommonsTest {

	/**
	 * ����ToStringBuilder.reflectionToString()����
	 * ��Ϊ��ʹ�õķ��䣬���Լ�ʹ�ֶ���private��Ҳ������ʾ����
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
