package com.gradle.okhttp.main;

import java.io.IOException;




import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * @author Arison
 * @funtion：okhttp http请求示例程序
 * @dec： 1，请求头添加
 *       2，get,post等方式的请求
 *       3，带参数请求
 *       4，post+json提交数据
 *       
 */
public class OkhttpUtilsMain {


	public static void main(String[] args) {
		sendHeaders();
	}
	
	/**
	 * 发请求头以及请求参数
	 */
	public static void sendHeadersAndParams() {
		String china_str="";
		try {
			china_str = URLEncoder.encode("中文", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");
	    String postBody = "Hello World";
		OkHttpClient client=new OkHttpClient();
		Request request=new Request.Builder().url(
				"http://localhost:8080/spring-mvc-showcase/api/getHeaders")
				.header("cookie", "JSESSIONID=EB36DE5E50E342D86C55DAE0CDDD4F6D")
				.addHeader("content-type", "text/html;charset:utf-8")
				.addHeader("Home", "china")//自定义的header
				.addHeader("Home1",china_str)//自定义的header  传中�?
				.addHeader("user-agent", "android")
				.post(RequestBody.create(MEDIA_TYPE_TEXT, postBody))
				.build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				String json=response.body().string();
				System.out.println(json);
				String home1=JSON.parseObject(json).getJSONObject("headers").getString("home1");
				System.out.println(URLDecoder.decode(home1,"utf-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发请求头
	 */
	public static void sendHeaders() {
		String china_str="";
		try {
			china_str = URLEncoder.encode("中文", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		OkHttpClient client=new OkHttpClient();
		Request request=new Request.Builder().url(
				"http://localhost:8080/spring-mvc-showcase/api/getHeaders")
				.header("cookie", "JSESSIONID=EB36DE5E50E342D86C55DAE0CDDD4F6D")
				.addHeader("content-type", "text/html;charset:utf-8")
				.addHeader("Home", "china")//自定义的header
				.addHeader("Home1",china_str)//自定义的header  传中�?
				.addHeader("user-agent", "android")
				.build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				String json=response.body().string();
				System.out.println(json);
				String home1=JSON.parseObject(json).getJSONObject("headers").getString("home1");
				System.out.println(URLDecoder.decode(home1,"utf-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**@dec 基本测试
	 * @throws IOException
	 */
	public static void okBasicRequest() {
		OkHttpClient client = new OkHttpClient();

		    Request request = new Request.Builder()
		            .url("http://www.baidu.com")
		            .build();

		   try {
			Response response = client.newCall(request).execute();
			    if (!response.isSuccessful()) {
//		        throw new IOException("服务器端错误: " + response);
			    }
			    //输入响应
			    Headers responseHeaders = response.headers();
			    for (int i = 0; i < responseHeaders.size(); i++) {
			        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
			    }
			    //输出响应实体
			    //System.out.println(response.body().string());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
