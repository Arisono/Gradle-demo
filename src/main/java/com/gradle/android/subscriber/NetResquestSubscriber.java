package com.gradle.android.subscriber;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import com.gradle.android.retrofit.OkhttpUtils;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * @author Arison
 * 网络订阅者
 * @param <T>
 */
public class NetResquestSubscriber<T> extends Subscriber<T> {

	private SubscriberOnNextListener<T> mSubscriberOnNextListener;
	
	public NetResquestSubscriber(SubscriberOnNextListener<T> listener) {
		this.mSubscriberOnNextListener=listener;
	}
	
	
	
	@Override
	public void onStart() {
		super.onStart();
		OkhttpUtils.println("网络请求开始...");
		OkhttpUtils.println("显示进度条");
	}
	
	
	@Override
	public void onCompleted() {
		OkhttpUtils.println("网络请求结束...");
		OkhttpUtils.println("关闭进度条");
	}

	@Override
	public void onError(Throwable e) {
		   if (e instanceof SocketTimeoutException) {
			   OkhttpUtils.println("服务器响应超时");
	        } else if (e instanceof ConnectException) {
	           OkhttpUtils.println("服务器拒绝访问");
	        } else {
	           OkhttpUtils.println("error:" + e.getMessage());
	           HttpException he=(HttpException) e;
			   OkhttpUtils.println("状态码："+ he.response().code());
			   try {
				OkhttpUtils.println("响应数据："+he.response().errorBody().string());
			  } catch (IOException e1) {
				e1.printStackTrace();
			  }
	       }
		   OkhttpUtils.println("关闭进度条");
	}

	@Override
	public void onNext(T t) {
	if(mSubscriberOnNextListener!=null){
		mSubscriberOnNextListener.onNext(t);
	}	
	}

}
