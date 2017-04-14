package com.gradle.android.base;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.gradle.android.retrofit.OkhttpUtils;
import com.gradle.java.rxjava.RxjavaUtils;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class OkhttpImpl extends HttpBase {
	
	public  String BASE_URL="http://192.168.253.200:8080/";
	
	public HttpClient mbuilder;
	private static OkhttpImpl instance;
	
//	public OkhttpImpl(HttpClient builder) {
//		this.BASE_URL=builder.getBaseUrl();
//	}

	public static OkhttpImpl getInstance(){
		if (instance==null) {
			synchronized (OkhttpImpl.class) {
				if (instance==null) {
					instance=new OkhttpImpl();
				}
			}
		}
		return instance;
	}
	
	@Override
	public void initClient() {
		//重连次数
		OkhttpUtils.maxLoadTimes=3;

	}

	@Override
	public void get(HttpClient builder, Subscriber<Object> s) {
		this.mbuilder=builder;
		OkhttpUtils.sendGetHttp(BASE_URL+ builder.getBaseUrl(), builder.getParams(), "", "");
	}

	@Override
	public void post(HttpClient builder, Subscriber<Object> s) {
		this.mbuilder=builder;
		OkhttpUtils.sendPostHttp(BASE_URL+ builder.getBaseUrl(), builder.getParams(), "", "");
	}
	
	private <T> void toSubscribe(Observable<T> o,Subscriber<T> s){
		  o.retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {

			@Override
			public Observable<?> call(Observable<? extends Throwable> t) {
				
				return t.flatMap(new Func1<Throwable, Observable<?>>() {
				   private int count=0;
					@Override
					public Observable<?> call(Throwable t) {
						OkhttpUtils.println("重连数："+mbuilder.getMaxRetryCount()+" 当前数："+count);
						if(++count<=mbuilder.getMaxRetryCount()){
							OkhttpUtils.println("网络请求异常："+t.getMessage());
							OkhttpUtils.println("网络请求重新连接: "+count);
							return Observable.timer(mbuilder.getRetryTimeout(), TimeUnit.MILLISECONDS);
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

	@Override
	public void setBuilder(HttpClient client) {
		// TODO Auto-generated method stub
		
	}

}
