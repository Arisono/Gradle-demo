package com.gradle.java.demos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * @author Arison
 * 正则表达式
 */
public class RegexUtil {
   public static void main(String[] args) {
	   Pattern pattern = Pattern.compile("^Java.*");
	   Matcher matcher = pattern.matcher("Java不是人");
	   boolean b= matcher.matches();
	   //当条件满足时，将返回true，否则返回false
	   System.out.println(b);
  
  }
}
