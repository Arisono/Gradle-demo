package com.gradle.android.retrofit;

import java.util.HashMap;
import java.util.Map;

import com.gradle.java.rxjava.RxBus;

import rx.Subscriber;

public class OkhttpApp {
	
	public static final String BASE_URL="http://192.168.253.200:8080/";

	public static void main(String[] args) {
		//重连次数
		OkhttpUtils.maxLoadTimes=3;
		
		RxBus.getInstance().toObservable().subscribe(new Subscriber<Object>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onError(Throwable e) {
//				   if (e instanceof SocketTimeoutException) {
//					   OkhttpUtils.println("服务器响应超时");
//			        } else if (e instanceof ConnectException) {
//			           OkhttpUtils.println("服务器拒绝访问");
//			        } else {
//			           OkhttpUtils.println("error:" + e.getMessage());
//			        }
				
			}

			@Override
			public void onNext(Object t) {
				OkhttpUtils.println("请求结果:"+t.toString());
				
			}
		});
		
		Map<String,Object> params=new HashMap<String, Object>();
	    params.put("id1", "1");
	    params.put("id2", "2");
	    params.put("id3", "3");
	    params.put("id4", "4");
		OkhttpUtils.sendGetHttp(BASE_URL+"hello", params, "", "");

	}

}
