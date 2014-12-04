package com.thunisoft.glt;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		Random r1 = new Random(47);
		Random r2 = new Random(47);
		
		System.out.println(r1.nextInt());
		System.out.println(r2.nextInt());
		
		System.out.println(r1.nextInt());
		System.out.println(r2.nextInt());
		
		System.out.println(r1.nextInt());
		System.out.println(r2.nextInt());
	}
}
