package com.gradle.android.Interceptor;

import com.gradle.android.retrofit.OkhttpUtils;

import okhttp3.logging.HttpLoggingInterceptor.Logger;
@Deprecated
public class CustomLogger implements Logger {

	@Override
	public void log(String message) {
		OkhttpUtils.println(message);		
	}

}
