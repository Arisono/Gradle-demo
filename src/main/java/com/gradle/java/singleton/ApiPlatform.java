package com.gradle.java.singleton;

/**
 * Created by Arison on 2017/3/2.
 */
public class ApiPlatform extends ApiBase implements ApiModel{
    //登录
    private  String cookie="";
    public String login="b2b api login";//登录
    
    private static ApiPlatform instance;
    
    
   
    public ApiPlatform() {
		super();
//		init();
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

    
//    @Override
//    public void init() {
//      setLogin(login);
//    }
    
    public String getLogin() {
   
        return "b2b login api";
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
