package com.gradle.java.base;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import com.gradle.java.utils.DateFormatUtil;


/**
 * @author Arison
 * Java 语法测试类
 */
public class MainUtils {
	 /** custom maximum value this axis represents */
    protected static float mCustomAxisMax = Float.NaN;

	public static void main(String[] args) {
	  HashSet<String> set=new HashSet<String>();
	  set.add(null);
	  set.add("你好");
	  set.add(" ");
	  set.add("");
	  set.add("你好");
	  set.add("中国");	  
	 Iterator<String> iter=  set.iterator();
	 while(iter.hasNext()){
		 System.out.println(iter.next());
	 }
//	Object[] datas= set.toArray();
//	for (Object object : datas) {
//		System.out.println(object.toString());
//	}
	 
	}



	/**
	 * 替换字符串
	 */
	public static void str_replace() {
		String str="申请流";
		  System.out.println(str.replace("流程",""));
	}



	/**
	 * 时间加法
	 */
	public static void timeAdd() {
		String millise="604800000";//7天  毫秒
		   System.out.println(DateFormatUtil.add(new Date(), "yyyy-MM-dd HH:mm:ss", Long.valueOf(millise)));
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
