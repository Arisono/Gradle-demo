package com.gradle.java.singleton;

/**
 * Created by Arison on 2017/3/2.
 */
public class ApiUAS extends ApiBase implements ApiModel {
	public String login="uas api login";//登录
	
	public ApiUAS() {
		 super.login=login;
	}
	
//    @Override
//    public void init() {
//     super.login=login;
//    }

    @Override
    public void setCookie(String cookie) {
        
    }

    @Override
    public String getCookie() {
        return null;
    }
    
   
    
}
