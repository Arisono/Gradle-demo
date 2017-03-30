package com.gradle.android.retrofit;

import java.awt.List;
import java.util.Map;

import com.gradle.android.utils.OkhttpUtils;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.subjects.Subject;

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
	
	public static RetrofitUtils getInstance(){
		return SingletonHolder.INSANCE;
	}
	
	
	public void  getApiPostData(Subscriber<Object> s,Map<String,Object> params){
		
	Observable<Object> o=paramService.postBodyByString("/postBodyByString", "retrofit2.0", params);
	toSubscribe(o, s);
	
	}
	
	private <T> void toSubscribe(Observable<T> o,Subscriber<T> s){
		  o.subscribe(s);
	}
	
}
