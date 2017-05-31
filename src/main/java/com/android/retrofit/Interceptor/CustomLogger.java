package com.android.retrofit.Interceptor;

import com.android.retrofit.demo.OkhttpUtils;

import okhttp3.logging.HttpLoggingInterceptor.Logger;
@Deprecated
public class CustomLogger implements Logger {

	@Override
	public void log(String message) {
		OkhttpUtils.println(message);		
	}

}
