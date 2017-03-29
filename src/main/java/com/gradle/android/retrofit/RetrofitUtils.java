package com.gradle.android.retrofit;

import com.gradle.android.utils.OkhttpUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @desc:封装统一的网络请求类
 * @method:单例类+Retrofit+Okhttp+Rxjava
 * @author Arison
 * 
 */
public class RetrofitUtils {
	
	public static final String BASE_URL="http://192.168.253.200:8080/";
	public Retrofit retrofit;
	
	//api service
	private ParamService paramService;
	private GitHubService gitHubService;

	public RetrofitUtils() {
		super();
		
		//init okhttp
		    //省略
		
		//init retrofit
		retrofit = new Retrofit.Builder()
				.client(OkhttpUtils.client)
			    .baseUrl(BASE_URL)
			    .addConverterFactory(GsonConverterFactory.create())
			    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
			    .build();
		
		//init api service
		
		paramService=retrofit.create(ParamService.class);
		
	}

	private static class SingletonHolder{
		 private static final  RetrofitUtils INSANCE=new RetrofitUtils();
	}
	
	public RetrofitUtils getInstance(){
		return SingletonHolder.INSANCE;
	}
	
	
	
}
