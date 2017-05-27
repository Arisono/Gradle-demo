package com.gradle.java.base;

/**
 * @author Arison
 * http://blog.csdn.net/t_alon/article/details/51752431
 * 
 */
public class SuperClass {
	
  /**
   * 如Animals d1 = new Dogg();
	d1即是一个Animals的对象，也是一个Dogg的对象，
	那么调用方法时，是根据对象的实际类型调用的，
	实际类型是Dogg，所以永远调用子类的方法。
	而访问成员变量就不同了，它是Animals时，访问的是父类的成员变量，
	转型为Dogg的话，访问的就是子类的成员变量了。
   * 
   * 
   * @param args
   */
public static void main(String[] args) {
	        Animals a=new SuperClass().new Animals();
	        a.enjoy();  
	        System.out.println(a.age);  
	          
	        Dogg d = new SuperClass().new Dogg();  
	        d.enjoy();  
	        System.out.println(d.age);  
	          
	        Animals d1 = new SuperClass().new  Dogg();  
	        d1.enjoy(); //子类的方法      
	        System.out.println(d1.age);//子类的变量  
	        Dogg s = (Dogg)d1;  
	        System.out.println(s.age);  
	          
}
  
  class Animals {  
	    int age = 10;  
	    void enjoy() {  
	        System.out.println("Animals enjoy!");  
	    }  
	      
	}  
	  
	class Dogg extends Animals {  
	    int age = 20;  
	    int weight;  
	    void enjoy() {  
	        System.out.println("Dog enjoy!");  
	    }  
	}  
}
