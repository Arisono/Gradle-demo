package com.gradle.android.Interceptor;

import java.io.IOException;

import com.gradle.android.base.HttpClient.CacheType;
import com.gradle.android.retrofit.OkhttpUtils;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 缓存拦截器
 * @author Arison
 * 
 */
public class CacheInterceptor implements Interceptor {
	
	private String cacheTime;//缓存时间
	private int cacheType;//缓存时间
	
	public CacheInterceptor(String cacheTime,int cacheType) {
		super();
		this.cacheTime = cacheTime;
		this.cacheType=cacheType;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		  Request request = chain.request();
		  
		  switch(cacheType) {
		  	case CacheType.ONLY_NETWORK:
		  		OkhttpUtils.println("强制使用网络");
		  		 request = request.newBuilder()
               .cacheControl(CacheControl.FORCE_NETWORK)//只访问網絡
               .build();
			break;
			case CacheType.ONLY_CACHED:
				OkhttpUtils.println("强制使用缓存");
				 request = request.newBuilder()
               .cacheControl(CacheControl.FORCE_CACHE)//只访问缓存
               .build();
			break;
			default:
			break;
		}
	
		  
	      Response response = chain.proceed(request);
	      Response response1 = response.newBuilder()
	                .removeHeader("Pragma")
	                .removeHeader("Cache-Control")
	                .header("Cache-Control", "max-age=" + cacheTime)
	                .build();
	        return response1;
	}

}
