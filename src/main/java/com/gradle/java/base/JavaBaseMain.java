package com.gradle.java.base;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;

import com.gradle.java.utils.DateFormatUtil;

/**
 * @author Arison
 * Java 语法测试类
 */
public class JavaBaseMain {

	public static void main(String[] args) {
		
		//String str= URLDecoder.decode("%");
		String str1=URLEncoder.encode("%");
		 
		System.out.println(URLDecoder.decode(str1));
	}
	
	
	/**
	 * Java基础篇
	 */

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
	
	/**
	 * Java Json解析篇
	 */

}
