package com.gradle.android.subscriber;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import com.gradle.android.retrofit.OkhttpUtils;

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
			   OkhttpUtils.println("网络中断，请检查您的网络状态");
	        } else if (e instanceof ConnectException) {
	           OkhttpUtils.println("网络中断，请检查您的网络状态");
	        } else {
	           OkhttpUtils.println("error:" + e.getMessage());
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
