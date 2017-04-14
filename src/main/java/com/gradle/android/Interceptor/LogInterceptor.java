package com.gradle.android.Interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.gradle.android.base.HttpClient;
import com.gradle.android.retrofit.OkhttpUtils;
import com.gradle.java.utils.DateFormatUtil;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LogInterceptor implements Interceptor {
	
	private HttpClient builder;

	@Override
	public Response intercept(Chain chain) throws IOException {
		  Request request = chain.request();
		  Map<String, Object> headers=new HashMap<>();
		  Map<String, Object> params=new HashMap<>();
		  
		  //添加统一请求头
//		  if(builder.getHeaders().size()!=0){
			  request=request.newBuilder().addHeader("version", "v1.0").build();
			  request=request.newBuilder().addHeader("http", "okhttp3.0").build();
//		  }
		  
		  //get请求    添加公共参数
		  if(request.method().equals("GET")){
			  HttpUrl httpUrl=request.url().newBuilder()
					  .addQueryParameter("client", "okhttp3.0 for")
					  .addQueryParameter("timestamp", DateFormatUtil.getDateTimeStr())
					  .build();
			  request=request.newBuilder().url(httpUrl).build();
		  }
		  
		  
		  Map<String,Object> postParm=new HashMap<>();
		  if(request.method().equals("POST")){
			  if (request.body() instanceof FormBody) {
		            FormBody.Builder bodyBuilder = new FormBody.Builder();
		            FormBody formBody = (FormBody) request.body();
		            //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
		            for (int i = 0; i < formBody.size(); i++) {
		            	postParm.put(formBody.encodedName(i), formBody.encodedValue(i));
		                bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
		            }

		            formBody = bodyBuilder
		                    .addEncoded("clienttype", "1")
		                    .addEncoded("imei", "imei")
		                    .addEncoded("version", "VersionName")
		                    .addEncoded("timestamp", String.valueOf(System.currentTimeMillis()))
		                    .build();

		            request = request.newBuilder().post(formBody).build();
		  }
		  }else if(request.body() instanceof RequestBody){
			
//			  RequestBody requestBody=request.body();
//			  requestBody.create(request.body().contentType(), )
		
		  }
		  //post请求   添加公共参数
		  
		  Response response = chain.proceed(request);
		  okhttp3.MediaType mediaType = response.body().contentType();
          String content = response.body().string();
          
         
    	  OkhttpUtils.println("------------------------------------------");
		  OkhttpUtils.println("请求头:"+JSON.toJSONString( response.request().headers().toMultimap()));
		  OkhttpUtils.println("url:"+JSON.toJSONString(response.request().url().toString()));
		  OkhttpUtils.println("参数:"+JSON.toJSONString(postParm));
//    		  OkhttpUtils.println("响应头:"+JSON.toJSONString( response.headers().toMultimap()));
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
