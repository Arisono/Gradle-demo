package com.gradle.java.base;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * @author Arison
 * Java 语法测试类
 */
public class JavaBaseMain {
	 /** custom maximum value this axis represents */
    protected static float mCustomAxisMax = Float.NaN;

	public static void main(String[] args) {
		str_replaceAll();
	}



	/**
	 * 取代所有空格
	 */
	public static void str_replaceAll() {
		String str="  你  好   你好  你好    ";
		System.out.println(str.replaceAll(" ", ""));
	}

    

	/**
	 * 数字格式化
	 */
	public static void test_DecimalFormat() {
		System.out.println(new DecimalFormat("###,###,##0").format(12.0));
	}
	
	

	/**
	 * 测试for循环
	 */
	public static void test_forCase() {
	 ArrayList<Integer> mMoneys=new ArrayList<Integer>();
	   mMoneys.add(1000);
	   mMoneys.add(2000);
	   mMoneys.add(3000);
	   mMoneys.add(4000);
	   mMoneys.add(67000);
	   mMoneys.add(6000);
	   mMoneys.add(34000);
	   for (Integer i:mMoneys){
          System.out.println(i);
        }
	}
	
}
