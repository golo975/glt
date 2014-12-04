package com.thunisoft.glt.SE;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class TimeTest {

	@Test
	public void get(){
		Date d = null;
		try {
			d = DateUtils.parseDate("2014-12-20", "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		
		System.out.println(c.get(Calendar.YEAR)); // 四位
		System.out.println(c.get(Calendar.MONTH)); // 从0开始
		System.out.println(c.get(Calendar.DAY_OF_MONTH));
		
		c.add(Calendar.MONTH, 1);
		System.out.println();
		
		System.out.println(c.get(Calendar.YEAR)); // 四位
		System.out.println(c.get(Calendar.MONTH)); // 从0开始
		System.out.println(c.get(Calendar.DAY_OF_MONTH));
	}
}
