package com.gradle.java.singleton;

import com.gradle.api.uas.OkhttpUtils;

/**
 * Created by Arison on 2017/3/2.
 */
public class ApiPlatform extends ApiBase implements ApiModel{
    //登录
    private  String cookie="";

    private static ApiPlatform instance;
    
    
   
    public ApiPlatform() {
		super();
		
	}


	public static ApiPlatform getInstance(){
    	if (instance==null) {
			synchronized (ApiPlatform.class) {
				if (instance==null) {
					instance=new ApiPlatform();
				}
			}
		}
    	
    	return instance;
    }

    
    @Override
    public void init() {
         login="";
        //OkhttpUtils.println("init():"+this.getClass().getName());
    }
    
    public String getLogin() {
   
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

}
