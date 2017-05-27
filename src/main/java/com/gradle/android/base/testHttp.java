package com.gradle.android.base;

import java.io.File;

import com.alibaba.fastjson.JSON;
import com.gradle.android.base.HttpClient.CacheType;
import com.gradle.android.base.HttpClient.Method;
import com.gradle.android.retrofit.OkhttpUtils;
import com.gradle.android.subscriber.NetResquestSubscriber;
import com.gradle.android.subscriber.SubscriberOnNextListener;
@SuppressWarnings("unused")
public class testHttp {

	public static final String BASE_URL="http://192.168.253.200:8080/Chapter/";
	
	public static void main(String[] args) {
		testRetrofitImpl();
	  //testOkhttpImpl();
	}


	private static void testRetrofitImpl() {
		HttpClient httpClient=new HttpClient.Builder(BASE_URL)//根路径
				  .add("CommonParam1", "公共请求参数1")//公共参数  //局部可累加
				  .add("CommonParam2", "公共请求参数2")
				  .header("Cookie", "abdclejdldj82jk23jfjd")//全局请求头   //局部可累加
				  .maxRetryCount(2)//局部可覆盖
				  .isDebug(false)//局部可覆盖
				  .retryTimeout(1000)//局部可覆盖
				  .cacheFile(new File("C:/Cache"))//局部可覆盖
				  .cacheFileSize(10240*1024)//局部可覆盖
				  .cacheType(CacheType.ONLY_NETWORK)//局部可覆盖
				  .cacheTime(60*200)//设置10分钟 //局部可覆盖
				  .connectTimeout(5000)//局部可覆盖
				  .readTimeout(5000)//局部可覆盖
				  .writeTimeout(7000)//局部可覆盖
				  .httpBase(RetrofitImpl.getInstance())//局部可覆盖
				  .build(true);//保持单例
		 
		   //new Builder 不应该忘记之前的配置属性---解决配置记忆问题
//		   httpClient=httpClient.newBuilder()
//				   .add("CommonParam3", "公共请求参数3")
//				   .add("CommonParam4", "公共请求参数4")
//				   .isDebug(true)
//				   .connectTimeout(30000)
//				   .readTimeout(30000)
//				   .writeTimeout(30000)
//				   .maxRetryCount(88)
//				   .retryTimeout(1000)
//				   .header("newHeader", "add new header")
//				   .httpBase(RetrofitImpl.getInstance())
//				   .build(true);
	
		   httpClient.Api().send(new HttpClient.Builder()
				   .url("getParam")//子路径
				   .add("param1", "value1")//局部参数
				   .add("param2", "value2")
				   .header("cookies", "cookies")//局部请求头
				   .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				   .header("Cookie", "android")//局部请求头
				   .header("Cookie", "java")//局部请求头---同名请求会覆盖
				   .header("header1", "header1")//局部请求头
				   .header("header2", "header2")//局部请求头
				   .method(Method.GET)
				   .build()
				   ,new NetResquestSubscriber<Object>(
						   new SubscriberOnNextListener<Object>() {

					@Override
					public void onNext(Object t) {
						OkhttpUtils.println(t.toString());
					}
				}));
		   
//	  while (true) {
//	//保证main主线程一直运行
//		}
	}
	
	
	/**
	 * OkhttpImpl 实现的网络请求
	 * @param args
	 */
	private static void testOkhttpImpl() {
		 HttpClient httpClient=new HttpClient.Builder()
				  .url(BASE_URL)
				  .add("param1", "value1")
				  .add("param2", "value2")
				  .header("Cookie2", "abdclejdldj82jk23jfjd")
				  .header("Cookie1", "abdclejdldj82jk23jfjd")
				  .maxRetryCount(3)
				  .isDebug(false)
				  .retryTimeout(8000)
				  .syn(false)
				  .httpBase(OkhttpImpl.getInstance())
				  .build(true);
		httpClient= httpClient.newBuilder()
				.maxRetryCount(8)
				.httpBase(OkhttpImpl.getInstance())
		        .build(true);
		 httpClient.Api().send(new HttpClient.Builder()
				    .url("getParam")
				    .add("username", "xiaomi")
				    .add("password", "111111")
				    .header("header", "new header")
				    .method(Method.GET)
				    .build(),
				    new NetResquestSubscriber<>(new SubscriberOnNextListener<Object>() {

						@Override
						public void onNext(Object t) {
							OkhttpUtils.println(t);
							
						}
					}));

	}

}
