package com.gradle.test.http;

import com.android.base.net.HttpClient;
import com.android.base.net.HttpClient.Method;
import com.android.base.net.RetrofitImpl;

import com.android.retrofit.subscriber.NetResquestSubscriber;
import com.android.retrofit.subscriber.SubscriberOnNextListener;

/**
 * 测试网络请求 Created by Arison on 2017/5/31.
 */
public class testHttp {
	public static final String BASE_URL = "http://113.105.74.140:8092/";

	/**
	 * @desc:
	 * @author：Arison on 2017/5/31
	 */
	public static void main(String arg[]) {
		HttpClient httpClient = new HttpClient.Builder().url(BASE_URL).isDebug(true).httpBase(RetrofitImpl.getInstance()).build();
		httpClient.Api()
				.send(new HttpClient.Builder().url("http://113.105.74.140:8092/user/appStoreList")
						.add("type", "0")
						.add("pageIndex", "1")
						.method(Method.POST)
						.build(), new NetResquestSubscriber<>(new SubscriberOnNextListener<Object>() {
							@Override
							public void onNext(Object o) {
								System.out.println(o.toString());
							}
						}));
	}
}
