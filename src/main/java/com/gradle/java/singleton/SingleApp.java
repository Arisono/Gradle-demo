package com.gradle.java.singleton;

import com.gradle.android.retrofit.OkhttpUtils;

public class SingleApp {
  
	private static SingleApp singleApp;
	
	
	public static  SingleApp getInstance(){
		if(singleApp==null){
			OkhttpUtils.println("SingleApp init");
			singleApp=new SingleApp();
		}
		return singleApp;
	}
}
