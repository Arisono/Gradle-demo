package com.android.retrofit.Interceptor;

import java.io.IOException;
import java.net.ConnectException;

import com.android.retrofit.demo.OkhttpUtils;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**Okhttp重试拦截器
 * @author Arison
 *
 */
public class RetryIntercepter implements Interceptor {
	public int maxRetry;//最大重试次数
    private int retryNum = 0;//假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）
    public RetryIntercepter(int maxRetry) {
        this.maxRetry = maxRetry;
    }
	@SuppressWarnings("resource")
	@Override
	public Response intercept(Chain chain) throws IOException   {
		  Request request = chain.request();
	      Response  response = chain.proceed(request);
	       try {
	    	  // OkhttpUtils.println("重试拦截器："+response.isSuccessful());
	       while (!response.isSuccessful() && retryNum < maxRetry) {
	            retryNum++;
	            response = chain.proceed(request);
	       }
		 }catch (ConnectException e) {
				OkhttpUtils.println("服务器拒绝连接.....");
		 }catch (IOException e) {
			 OkhttpUtils.println("服务器拒绝连接.....");
		 }
	      return response;
	}

}
