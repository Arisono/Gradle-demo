package com.gradle.java.utils;


/**
 * @author Arison
 * 类型转换
 */
public class TypeUtils {
	
	
	public static int  floatToInt(float f){	
		return Math.round(f);
	}
	
	public static int floatToIntCeil(float f){
		
		return (int)Math.ceil(f);
	}
	
	public static float intToFloat(int i){	
		return i;
	}
	
	public static String intToString(int i){
		return String.valueOf(i);
	}
	
	public static int StringToInt(String str){
		return Integer.valueOf(str);
				
	}
	
	public static void main(String[] args) {
		System.out.println(floatToIntCeil(132.2f));
	}

}
