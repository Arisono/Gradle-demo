package com.gradle.java.singleton;

public class ApiUtils {
	public static ApiModel getApiModel(){
		int i=1;
		if(i==0){
			return new ApiUAS();
		}else{
			return new ApiPlatform();
		}
	}
}
