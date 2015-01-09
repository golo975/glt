package com.thunisoft.glt.spring.aop.book.advice;

public class NaiveWaiter implements Waiter {

	public void greetTo(String name) {
		System.out.println("greet to "+name+"...");
	}
	
	public void serveTo(String name){
		System.out.println("serving "+name+"...");
	}
}
