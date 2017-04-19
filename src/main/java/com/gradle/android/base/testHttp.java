package com.gradle.android.base;

import com.gradle.android.base.HttpClient.Builder;
import com.gradle.android.base.HttpClient.Method;
import com.gradle.android.retrofit.OkhttpUtils;
import com.gradle.android.subscriber.NetResquestSubscriber;
import com.gradle.android.subscriber.SubscriberOnNextListener;

public class testHttp {

	public static final String BASE_URL="http://192.168.253.200:8080/";
	
	public static void main(String[] args) {	
		
	  HttpClient httpClient=new HttpClient.Builder(BASE_URL)//根路径
			  .add("CommonParam1", "公共请求参数1")//公共参数
			  .add("CommonParam2", "公共请求参数2")
			  .header("Cookie", "abdclejdldj82jk23jfjd")//全局请求头
			  .maxRetryCount(2)
			  .isDebug(true)
			  .retryTimeout(1000)
			  .connectTimeout(5000)
			  .readTimeout(5000)
			  .writeTimeout(7000)
			  .httpBase(RetrofitImpl.getInstance())
			  .build();
       
       httpClient.Api().send(new HttpClient.Builder("postParam")//子路径
    		   .add("param1", "value1")//局部参数
    		   .add("param2", "value2")
    		   .header("cookies", "cookies")//局部请求头
    		   .header("Cookie", "android")//局部请求头
    		   .header("Cookie", "java")//局部请求头---同名请求会覆盖
    		   .header("header1", "header1")//局部请求头
    		   .header("header2", "header2")//局部请求头
    		   .method(Method.POST)
    		   .build()
    		   ,new NetResquestSubscriber<Object>(
    				   new SubscriberOnNextListener<Object>() {

				@Override
				public void onNext(Object t) {
					OkhttpUtils.println(t.toString());
				}
			}));
       
       
       
     
	}
	
	
	/**
	 * OkhttpImpl 实现的网络请求
	 * @param args
	 */
	@SuppressWarnings("unused")
	private static void testOkhttpImpl() {
		 HttpClient httpClient=new HttpClient.Builder(BASE_URL)
				  .add("param1", "value1")
				  .add("param2", "value2")
				  .header("Cookie", "abdclejdldj82jk23jfjd")
				  .maxRetryCount(3)
				  .isDebug(true)
				  .retryTimeout(8000)
				  .syn(false)
				  .httpBase(RetrofitImpl.getInstance())
				  .build();
		   Builder mBuilder= httpClient.newBuilder(BASE_URL)
					  .add("param3", "value3s")
					  .add("param4", "value4")
					  .header("Cookie", "123213213")
					  .maxRetryCount(3)
					  .isDebug(true)
					  .retryTimeout(8000)
					  .syn(false)
		              .httpBase(OkhttpImpl.getInstance());
		   httpClient.setmBuilder(mBuilder);
	}

}
