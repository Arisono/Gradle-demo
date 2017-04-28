package com.gradle.android.retrofit;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.gradle.android.converter.StringConverterFactory;
import com.gradle.java.model.HttpResult;
import com.gradle.java.rxjava.RxjavaUtils;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * @desc:封装统一的网络请求类
 * @method:单例类+Retrofit+Okhttp+Rxjava
 * @author Arison
 * 
 */
public class RetrofitUtils {
	
	public static final String BASE_URL="http://192.168.253.200:8080/Chapter/";
	public Retrofit retrofit;
	private static int retryCount=2;
	
	//api service
	private ParamService paramService;
	@SuppressWarnings("unused")
	private GitHubService gitHubService;

	public RetrofitUtils() {
		super();
		
		//init okhttp
		    //省略
		
		
		
 
		//init retrofit
		retrofit = new Retrofit.Builder()
				.client(OkhttpUtils.client)
			    .baseUrl(BASE_URL)
			    .addConverterFactory(StringConverterFactory.create())
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
	
	
	public void uploads(Subscriber<Object> s,String url,Map<String,Object> params){
		 MultipartBody.Builder builder = new MultipartBody.Builder();
         builder.setType(MultipartBody.FORM);
         //追加参数
         for (String key : params.keySet()) {
             Object object = params.get(key);
             if (!(object instanceof File)) {
                 builder.addFormDataPart(key, object.toString());
             } else {
                 File file = (File) object;
                 //其中参数“file”和服务器接收的参数 一一对应,保证多文件上传唯一key不变
                 builder.addFormDataPart("file", file.getName(), RequestBody.create(null, file));
             }
         }
       //创建RequestBody
         RequestBody body = builder.build();
		 Observable<Object> o=paramService.uploads(url, body);
	     toSubscribe(o, s);
	}
	
	public void  getApiPostData(Subscriber<Object> s,String url,Map<String,Object> params){
		Observable<Object> o=
				paramService.postBodyByString("postBodyByString", "retrofit2.0", params);
		//paramService.postParam(url, params);
		toSubscribe(o, s);
	}
	
	public void getApiGetData(Subscriber<Object> s,String url,Map<String,Object> params){
		Observable<Object> o=
		paramService.getParam(url, params);
		toSubscribe(o, s);
	}
	
	public void getApiGetExceptionData(Subscriber<Object> s,String url){
		Observable<Object> o=
		paramService.getException(url);
		toSubscribe(o, s);
	}
	
	private <T> void toSubscribe(Observable<T> o,Subscriber<T> s){
		  o.retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {

			@Override
			public Observable<?> call(Observable<? extends Throwable> t) {
				
				return t.flatMap(new Func1<Throwable, Observable<?>>() {
				   private int count=0;
					@Override
					public Observable<?> call(Throwable t) {
						if(++count<=retryCount){
							OkhttpUtils.println("网络请求异常："+t.getMessage());
							OkhttpUtils.println("网络请求重新连接: "+count);
							return Observable.timer(10000, TimeUnit.MILLISECONDS);
						}
						return Observable.error(t);
					}
				});
			}
		}).map(new Func1<T, T>() {

			@Override
			public  T call(T t) {
				return (T) t;
			}
		}).
		  subscribeOn(RxjavaUtils.getNamedScheduler("线程1"))
		  .subscribe(s);
	}
	
	
	public void getApiGetException(Subscriber<HttpResult<Object>> s,String url){
		Observable<Object> o=
		paramService.getException(url);
		toSubscribeBy(o, s);
	}
	
	
	private <T> void toSubscribeBy(Observable<T> o,Subscriber<HttpResult<T>> s){
		  o
		  .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {

			@Override
			public Observable<?> call(Observable<? extends Throwable> t) {
				
				return t.flatMap(new Func1<Throwable, Observable<?>>() {
				   private int count=0;
					@Override
					public Observable<?> call(Throwable t) {
						if(++count<=retryCount){
							OkhttpUtils.println("网络请求异常："+t.getMessage());
							OkhttpUtils.println("网络请求重新连接: "+count);
							return Observable.timer(10000, TimeUnit.MILLISECONDS);
						}
						return Observable.error(t);
					}
				});
			}
		})
		  .map(new Func1<T, HttpResult<T>>() {

			@Override
			public HttpResult<T> call(T t) {
				HttpResult<T>  o=new HttpResult<>();
				o.setSubjects(t);
				o.setMessage("123456");
				o.setPath("/json");
				o.setStatus(200);
				return o;
			}
		})
		  .subscribeOn(RxjavaUtils.getNamedScheduler("线程1"))
		  .subscribe(s);
	}
}
