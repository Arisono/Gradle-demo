package com.gradle.test.http;

import com.gradle.android.base.HttpClient;
import com.gradle.android.base.HttpUtils;
import com.gradle.android.retrofit.OkhttpUtils;

public class testHttp {

	public static void main(String[] args) {
	  //初始化
	  //baseurl
	  //网络框架的选择
	  HttpClient httpClient=new HttpClient.Builder("")
			  .add("param1", "value1")
			  .add("param2", "value2")
			  .header("Cookie", "abdclejdldj82jk23jfjd")
			  .maxRetryCount(3)
			  .isDebug(true)
			  .timeout(8000)
			  .syn(false)
			  .build();
      OkhttpUtils.println(httpClient);
      OkhttpUtils.println(httpClient.getTimeout());
      OkhttpUtils.println(httpClient.getMaxRetryCount());
      
      httpClient=new HttpClient.Builder("")
			  .add("param1", "value1")
			  .add("param2", "value2")
			  .header("Cookie", "abdclejdldj82jk23jfjd")
			  .maxRetryCount(5)
			  .isDebug(true)
			  .timeout(9000)
			  .syn(false)
			  .build();
      
      OkhttpUtils.println(httpClient);
      OkhttpUtils.println(httpClient.getTimeout());
      OkhttpUtils.println(httpClient.getMaxRetryCount());
	}

}
