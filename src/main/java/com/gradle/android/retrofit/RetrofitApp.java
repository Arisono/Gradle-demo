package com.gradle.android.retrofit;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.gradle.android.subscriber.NetResquestSubscriber;
import com.gradle.android.subscriber.SubscriberOnNextListener;
import com.gradle.android.utils.OkhttpUtils;
import com.gradle.java.model.ErrorInfo;
import com.gradle.java.utils.ExceptionUtils;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

@SuppressWarnings("unused")
public class RetrofitApp {
	
	/**
	 * 
	 */
	public static Retrofit retrofit = new Retrofit.Builder()
		    .baseUrl("http://192.168.253.200:8080/")
		    .addConverterFactory(GsonConverterFactory.create())
		    .build();
	public static GitHubService service = retrofit.create(GitHubService.class);
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		runTask();
       
	}


	private static void runTask() {
		//demo01();
		
		//demo2();
		
		Map<String,Object> params=new HashMap<String, Object>();
	    params.put("id1", "1");
	    params.put("id2", "2");
	    params.put("id3", "3");
	    params.put("id4", "4");
//		RetrofitUtils.getInstance().getApiPostData(new Subscriber<Object>() {
//			
//			@Override
//			public void onNext(Object t) {
//				OkhttpUtils.println(t.toString());
//				
//			}
//			
//			@Override
//			public void onError(Throwable e) {
//				ExceptionUtils.printExceptionStack(e);
//				
//			}
//			
//			@Override
//			public void onCompleted() {
//				OkhttpUtils.println("onCompleted()");
//				
//			}
//		}, params);
		
		RetrofitUtils.getInstance().getApiPostData(new NetResquestSubscriber<Object>(new SubscriberOnNextListener<Object>() {

			@Override
			public void onNext(Object t) {
				OkhttpUtils.println(t.toString());
				
			}
		}),"/postParam", params);
	}


	/**
	 * 主要测试post方法
	 */
	private static void demo2() {
		Map<String,Object> param=new HashMap<String, Object>();
	    param.put("id1", "1");
	    param.put("id2", "2");
	    param.put("id3", "3");
	    param.put("id4", "4");
	    
	    ErrorInfo<String> data=new ErrorInfo<>();
	    data.setCode(500);
	    data.setData("data");
	    data.setMessage("msg");
	    data.setUrl("url");
	    
	    Call<Object> repos = service.postParam("/postParam", param);
	    //Call<Object> repos = service.postBodyByString("/postBodyByString", "retrofit2.0", param);
	    //Call<Object> repos = service.postBodyByObject("/postBodyByString", "retrofit2.0", param);
	    
	    //两种方式返回体有所区别，Object--->Model(具体实体类)
	    //Call<Object> repos = service.postBodyByObject("/postBodyByString", data, param);
	    //Call<Object> repos = service.postBodyByModel("/postBodyByModel", data, param);
	    
	    // Call<Object> repos = service.postBodyByMuli("/postBodyByMuli","test1", "test2", param);
	    
//	    MultipartBody.Part body = 
//        MultipartBody.Part.create(RequestBody.create(MediaType.parse("multipart/form-data; charset=utf-8"), "12"));
//MultipartBody.Part body2 = 
//MultipartBody.Part.create(RequestBody.create(MediaType.parse("multipart/form-data; charset=utf-8"), "12"));
//	    Call<Object> repos = service.postBodyByMulile("/postBodyByMuli",body, body2, param);
	    enqueueTask(repos);
	}

	
	
	/**
	 * Get，Post方法测试
	 */
	private static void demo01() {
		//get
	    Map<String,Object> param=new HashMap<String, Object>();
	    param.put("id1", "1");
	    param.put("id2", "2");
	    param.put("id3", "3");
	    param.put("id4", "4");
		//Call<Object> repos = service.listRepos();//get
		//Call<Object> repos = service.listMap();//get 
		//Call<Object> repos = service.listMap("/param","12",URLEncoder.encode("body","utf-8"));
		//Call<Object> repos = service.paramGet("0812");//get param ? paramGet
		
		//post
	    param=new HashMap<String, Object>();
	    param.put("id1", "1");
	    param.put("id2", "2");
	    param.put("id3", "3");
	    param.put("id4", "4");
		//Call<Object> repos = service.paramPostBodyField("liujie",param,19910812);
		//Call<Object> repos = service.paramPostField(param,19910812);
	    
	    ErrorInfo<String> data=new ErrorInfo<>();
	    data.setCode(500);
	    data.setData("data");
	    data.setMessage("msg");
	    data.setUrl("url");
	    Map<String,Object> header=new HashMap<String, Object>();
	    header.put("Content-type", "application/json;charset=UTF-8");
	    
//	    MultipartBody.Part body = 
//	            MultipartBody.Part.createFormData("body", "jstudy");
//	    MultipartBody.Part body2 = 
//	            MultipartBody.Part.createFormData("body2", "jstudy");
//        //多个请求体
//	    Call<Object> repos = service.paramModel(body,body2,"model");
	    Call<Object> repos = service.paramBody(data,"model");
		enqueueTask(repos);
	}

	private static void enqueueTask(Call<Object> repos) {
		repos.enqueue(new Callback<Object>() {
			
			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {
				OkhttpUtils.println(call.request().url().toString());
				OkhttpUtils.println(call.request().method());
				if(response.isSuccessful()){
					OkhttpUtils.println("isSuccessful()："+JSON.toJSONString(response.isSuccessful()));
					OkhttpUtils.println(JSON.toJSONString(response.body()));
				}else{
					try {
						OkhttpUtils.println("failure："+response.errorBody().string());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			@Override
			public void onFailure(Call<Object> call, Throwable t) {
				OkhttpUtils.println(call.request().url().toString());
				OkhttpUtils.println(ExceptionUtils.printExceptionStack(t));
		
			}
		});
	}

}
