package com.gradle.android.base;

import java.util.Map;

public interface HttpUtils {
   
	public void init();//初始化网络库
	public void sendGet(String url,Map<String,Object> params);
	public void sendPost(String url,Map<String,Object> params);
}
