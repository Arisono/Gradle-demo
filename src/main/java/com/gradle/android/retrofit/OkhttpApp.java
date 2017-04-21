package com.gradle.android.retrofit;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.gradle.java.rxjava.RxBus;

import okhttp3.Call;
import okhttp3.Response;
import rx.Subscriber;

public class OkhttpApp {
	
	public static final String BASE_URL="http://192.168.253.200:8080/";

	public static void main(String[] args) {
		initOkhttp();
		
		Map<String,Object> params=new HashMap<String, Object>();
	    params.put("id1", "1");
	    params.put("id2", "2");
	    params.put("id3", "3");
	    params.put("id4", "4");
	    //访问get请求 -----> 404,500  走onResponse(Call call, Response response)
//		OkhttpUtils.sendGetHttp(BASE_URL+"exception04", params, "", "");
	    //上传单个文件
//		OkhttpUtils.uploadFile(BASE_URL+"uploadImage",
//				params, 
//				"C://Users//Arison//Downloads//JJPlayer_2.8.2.1_setup_jjvod.1444617961.exe");
		
		File f1=new File("C://Users//Arison//Downloads//JJPlayer_2.8.2.1_setup_jjvod.1444617961.exe");
		File f2=new File("C://Users//Arison//Downloads//JJPlayer_2.8.2.1_setup_jjvod.1444617961.exe");
		File f3=new File("C://Users//Arison//Downloads//JJPlayer_2.8.2.1_setup_jjvod.1444617961.exe");
		params.put("file1", f1);
		params.put("file2", f2);
		params.put("file3", f3);
		OkhttpUtils.uploadFile(BASE_URL+"uploadFiles",params);
	}

	
	
	
	
	private static void initOkhttp() {
		//重连次数
		OkhttpUtils.maxLoadTimes=0;
		
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
	}

}
