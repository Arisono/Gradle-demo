package com.gradle.android.Interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.gradle.android.base.HttpClient;
import com.gradle.android.retrofit.OkhttpUtils;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;

import okhttp3.Request;
import okhttp3.Response;

public class LogInterceptor implements Interceptor {
	
	private HttpClient builder;

	@Override
	public Response intercept(Chain chain) throws IOException {
		  Request request = chain.request();
		  Map<String, Object> headers=new HashMap<>();
		  Map<String, Object> params=new HashMap<>();
		  Map<String,Object> postParm=new HashMap<>();
		  //添加公共Header,公共参数
		  if (builder!=null) {
			headers=builder.getHeaders();
			params=builder.getParams();
			if(!headers.isEmpty()){
			  for (Map.Entry<String,Object> entry : headers.entrySet()) {
				  request=request.newBuilder()
						  .addHeader(entry.getKey(), String.valueOf(entry.getValue()))
						  .build();
				  }
			}
			if (!params.isEmpty()) {
				  //get请求    添加公共参数
				  if(request.method().equals("GET")){
					  for (Map.Entry<String, Object> entry : params.entrySet()) {
						  HttpUrl httpUrl=request.url().newBuilder()
								  .addQueryParameter(entry.getKey(), String.valueOf(entry.getValue()))
								  .build();
						  request=request.newBuilder().url(httpUrl).build();
						
					} 
				  }
				  if(request.method().equals("POST")){
					  if (request.body() instanceof FormBody) {
						  FormBody.Builder bodyBuilder = new FormBody.Builder();
						  FormBody formBody = (FormBody) request.body();
						  for (int i = 0; i < formBody.size(); i++) {
								postParm.put(formBody.encodedName(i), formBody.encodedValue(i));
				                bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
				            }
						  for (Map.Entry<String, Object> entry : params.entrySet()) {
							  formBody = bodyBuilder
					                    .addEncoded(entry.getKey(), String.valueOf(entry.getValue()))
					                    .build();
						  }
						  request = request.newBuilder().post(formBody).build();
					  }
				  }
			}
		  }
		 
	
		  
		  Response response = chain.proceed(request);
		  okhttp3.MediaType mediaType = response.body().contentType();
          String content = response.body().string();
          
         
    	  OkhttpUtils.println("------------------------------------------");
		  OkhttpUtils.println("请求头:"+JSON.toJSONString( response.request().headers().toMultimap()));
		  OkhttpUtils.println("url:"+JSON.toJSONString(response.request().url().toString()));
		  OkhttpUtils.println("参数:"+JSON.toJSONString(postParm));
		  OkhttpUtils.println("结果:"+content);
		  OkhttpUtils.println("------------------------------------------");
		
		return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
	}

	public void setBuilder(HttpClient builder) {
		this.builder = builder;
	}
	
}
