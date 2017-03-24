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
import com.gradle.android.utils.OkhttpUtils;
import com.gradle.java.utils.ExceptionUtils;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@SuppressWarnings("unused")
public class RetrofitApp {
	public static Retrofit retrofit = new Retrofit.Builder()
		    .baseUrl("http://192.168.253.200:8080/")
		    .addConverterFactory(GsonConverterFactory.create())
		    .build();
	public static GitHubService service = retrofit.create(GitHubService.class);
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		
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
	    
	    MultipartBody.Part body = 
	            MultipartBody.Part.createFormData("body", "jstudy");
	    MultipartBody.Part body2 = 
	            MultipartBody.Part.createFormData("body2", "jstudy");


	    Call<Object> repos = service.paramModel(body,body2,"model");
	    
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
