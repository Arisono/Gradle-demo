package com.gradle.java.base;

/**
 * Java基本语法知识
 * @author Arison
 *
 */
public class GrammarUtils {

	public static void main(String[] args) {
	
		String testPick=pick("", "美国人");
		System.out.println(testPick);
		


	}
	
	
	/**
	 * 异常之后是否会执行之后的代码块
	 */
	public static void exceptionTest01(){
		String test="12+";
		try {
			int i=Integer.parseInt(test);
		} catch (NumberFormatException e) {
		   //异常之后，会执行try catch之后的代码
			return;
		}
		System.out.println("1231");
	}
	
	
	

	/**
	 * 泛型方法
	 * 
	 * @param a1
	 * @param a2
	 * @return
	 */
	public static <T> T pick(T a1, T a2) {
		return a2;
	}

}
