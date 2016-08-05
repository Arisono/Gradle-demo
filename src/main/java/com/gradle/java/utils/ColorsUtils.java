package com.gradle.java.utils;

import java.util.Random;

public class ColorsUtils {

	/**
	 * 获取十六进制的颜色代码.例如 "#6E36B4" , For HTML ,
	 * 
	 * @return String
	 */
	public static String getRandColorCode() {
		String r, g, b;
		Random random = new Random();
		r = Integer.toHexString(random.nextInt(256)).toUpperCase();
		g = Integer.toHexString(random.nextInt(256)).toUpperCase();
		b = Integer.toHexString(random.nextInt(256)).toUpperCase();

		r = r.length() == 1 ? "0" + r : r;
		g = g.length() == 1 ? "0" + g : g;
		b = b.length() == 1 ? "0" + b : b;

		return r + g + b;
	}
	
	public static void main(String[] args) {
		System.out.println("#"+getRandColorCode());
	}
}
