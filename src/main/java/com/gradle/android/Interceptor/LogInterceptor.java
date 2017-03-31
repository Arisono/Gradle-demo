package com.gradle.android.Interceptor;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.gradle.android.utils.OkhttpUtils;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LogInterceptor implements Interceptor {

	@Override
	public Response intercept(Chain chain) throws IOException {
		  Request request = chain.request();
		  Response response = chain.proceed(chain.request());
		  okhttp3.MediaType mediaType = response.body().contentType();
          String content = response.body().string();
          OkhttpUtils.println("------------------------------------------");
		  OkhttpUtils.println("请求头:"+JSON.toJSONString( response.request().headers().toMultimap()));
		  OkhttpUtils.println("url:"+JSON.toJSONString(response.request().url().toString()));
		  OkhttpUtils.println("参数:"+JSON.toJSONString(response.request().url().queryParameterNames().toArray()));
		  
		  OkhttpUtils.println("响应头:"+JSON.toJSONString( response.headers().toMultimap()));
		  OkhttpUtils.println("结果:"+content);
		  OkhttpUtils.println("------------------------------------------");
		return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
	}

}
