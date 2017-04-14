package com.gradle.android.base;

import java.util.Map;

import com.gradle.android.base.HttpClient.Builder;
@Deprecated
public interface HttpUtils {
    
	//初始化网络库
	public void init();
	//常规操作
	public void sendGet(String url,Map<String,Object> params);
	public void sendPost(String url,Map<String,Object> params);
	
	public void send(Builder builder);
}
