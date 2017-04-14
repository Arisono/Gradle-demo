package com.gradle.android.base;

import com.gradle.android.base.HttpClient.Builder;
import com.gradle.android.base.HttpClient.Method;
import com.gradle.android.retrofit.OkhttpUtils;
import com.gradle.android.subscriber.NetResquestSubscriber;
import com.gradle.android.subscriber.SubscriberOnNextListener;

public class testHttp {

	public static final String BASE_URL="http://192.168.253.200:8080/";
	
	public static void main(String[] args) {	
	  HttpClient httpClient=new HttpClient.Builder(BASE_URL)
			  .add("param1", "value1")
			  .add("param2", "value2")
			  .header("Cookie", "abdclejdldj82jk23jfjd")
			  .maxRetryCount(18)
			  .isDebug(true)
			  .retryTimeout(1000)
			  .connectTimeout(5000)
			  .readTimeout(5000)
			  .writeTimeout(7000)
			  .httpBase(RetrofitImpl.getInstance())
			  .build();
       
       httpClient.Api().send(new HttpClient.Builder("json")
    		   .add("key1", "value1")
    		   .add("key2", "value2")
    		   .header("cookies", "cookies")
    		   .method(Method.GET)
    		   .build()
    		   ,new NetResquestSubscriber<Object>(
    				   new SubscriberOnNextListener<Object>() {

				@Override
				public void onNext(Object t) {
					OkhttpUtils.println(t.toString());
				}
			}));
       
       
       
       while(true){
    	   
       }
	}
	
	
	/**
	 * OkhttpImpl 实现的网络请求
	 * @param args
	 */
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
					  .add("param1", "value1")
					  .add("param2", "value2")
					  .header("Cookie", "abdclejdldj82jk23jfjd")
					  .maxRetryCount(3)
					  .isDebug(true)
					  .retryTimeout(8000)
					  .syn(false)
		              .httpBase(OkhttpImpl.getInstance());
		   httpClient.setmBuilder(mBuilder);
	}

}
