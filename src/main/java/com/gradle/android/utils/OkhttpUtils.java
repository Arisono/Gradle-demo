package com.gradle.android.utils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSON;
import com.gradle.java.rxjava.RxBus;
import com.gradle.java.utils.ExceptionUtils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.FormBody.Builder;

/**
 * @author Arison
 * okhttp工具类
 */
@SuppressWarnings("unused")
public class OkhttpUtils {
	
	private static boolean debug = true;// 是否日志打印
	
	public static OkHttpClient client = new OkHttpClient.Builder()
	.connectTimeout(10, TimeUnit.SECONDS)
	.readTimeout(10, TimeUnit.SECONDS)
	//信任所有证书
	.sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())
	.hostnameVerifier(new TrustAllHostnameVerifier())
	.build();
	
	
	/**
	 * 打印日志
	 * @param msg
	 */
	public static void println(String msg,int type) {
		if (debug) {
			System.out.println(getLineInfo(type) + msg);
		}
	}
	
	/**
	 * 打印日志
	 * @param msg
	 */
	public static void println(String msg) {
		if (debug) {
			System.out.println(getLineInfo(typeSimple) + msg);
		}
	}
	
	/**
	 * 打印日志
	 * @param msg
	 */
	public static void println(String msg,int type,String method) {
		if (debug) {
			System.out.println(getLineInfo(type,method) + msg);
		}
	}
	
	/**
	 * 打印日志
	 * @param msg
	 */
	public static void println(String msg,String method) {
		if (debug) {
			System.out.println(getLineInfo(typeSimple,method) + msg);
		}
	}

	public static final int typeAll=0;
	public static final int typeSimple=1;
	public static final int typeClass=2;
	public static final int typeMiddle=3;
	/**
	 * 获取代码当前行数
	 * 
	 * @return
	 */
	public static String getLineInfo(int dispalyName,String method) {
		Throwable root= new Throwable();
		StackTraceElement ste = root.getStackTrace()[2];
		switch (dispalyName) {
		case typeAll:
			return "【"+ste.getFileName()+"】" + ":【第" + ste.getLineNumber() + "行】:【"+method+"】";
		case typeSimple:
			return "【第" + ste.getLineNumber() + "行】:";
		case typeClass:
			return ste.getFileName() + ":【第" + ste.getLineNumber() + "行】:";
		case typeMiddle:
			return  "【第" + ste.getLineNumber() + "行】:"+ "【"+method+"】";
		default:
			break;
		}
		return null;
	}

	/**
	 * 获取代码当前行数
	 * 
	 * @return
	 */
	public static String getLineInfo(int dispalyName) {
		Throwable root= new Throwable();
		StackTraceElement ste = root.getStackTrace()[2];
		switch (dispalyName) {
		case typeAll:
			return "【"+ste.getFileName()+"】" + ":【第" + ste.getLineNumber() + "行】";
		case typeSimple:
			return "【第" + ste.getLineNumber() + "行】:";
		case typeClass:
			return ste.getFileName() + ":【第" + ste.getLineNumber() + "行】:";
		case typeMiddle:
			return  "【第" + ste.getLineNumber() + "行】:";
		default:
			break;
		}
		return null;
	}
	/**
	 * 统一的网络失败回调方法
	 * 
	 * @param e
	 */
	public static void onFailurePrintln(IOException e) {
		//println("onFailure:" + ExceptionUtils.printExceptionStack(e));
		if (e instanceof ConnectException) {
			println("服务器拒绝访问！");
		} else if (e instanceof SocketTimeoutException) {
			println("超时响应！");
		}
	}

	/**
	 * @param response
	 * @throws IOException
	 */
	public static String getResponseString(Response response)
			throws IOException {
		if (response.isSuccessful()) {
			String json = response.body().string();
			return json;
		} else {
			return "code:" + response.code() + "  message:"
					+ response.message();
		}
	}
	
	
	//http://blog.csdn.net/u013686019/article/details/52856389  信任所有证书
	private static class TrustAllCerts implements X509TrustManager {  
	    @Override  
	    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}  
	  
	    @Override  
	    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}  
	  
	    @Override  
	    public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}

		
	}  
	
	private static class TrustAllHostnameVerifier implements HostnameVerifier {  
	    @Override  
	    public boolean verify(String hostname, SSLSession session) {  
	        return true;  
	    }  
	}  
	
	private static SSLSocketFactory createSSLSocketFactory() {  
	    SSLSocketFactory ssfFactory = null;  
	  
	    try {  
	        SSLContext sc = SSLContext.getInstance("TLS");  
	        sc.init(null,  new TrustManager[] { new TrustAllCerts() }, new SecureRandom());  
	              
	        ssfFactory = sc.getSocketFactory();  
	    } catch (Exception e) {  
	    }  
	  
	    return ssfFactory;  
	}  

	
	
	/**
	 * Arison
	 * post请求回调
	 * @param url
	 * @param params
	 * @param testName
	 */
	public static void sendHttp(String url, Map<String, Object> params,String cookies,String tag,String method){
		if ("get".equals(method)) {
			sendGetHttp(url, params,cookies, tag);
		}
		if("post".equals(method)){
			sendPostHttp(url,params,cookies,tag);
		}
		
	}
	
	
	/** 
	 * post http
	 * @param url
	 * @param params
	 * @param tag
	 */
	public static void sendPostHttp(String url,Map<String,Object> params,String cookies,String tag){
		Builder paramBuilder = new FormBody.Builder();
		if (!params.isEmpty()) {
		Iterator<Map.Entry<String, Object>> entries=    params.entrySet().iterator();
		while (entries.hasNext()) {  
		    Map.Entry<String, Object> entry = entries.next();  
		    paramBuilder.add(String.valueOf(entry.getKey()),  String.valueOf(entry.getValue()));
		}  
		OkhttpUtils.println(tag+":"+url);
		RequestBody formBody=paramBuilder.build();
		Request request = new Request.Builder()
				.url(url)
				.addHeader("content-type", "text/html;charset:utf-8")
				.addHeader("Cookie", cookies)
				.post(formBody)
				.build();
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String requestJson = OkhttpUtils.getResponseString(response);
				//OkhttpUtils.println(tag + ":" + requestJson);
				RxBus.getInstance().send(tag + ":" +requestJson);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
		
		}

	}
	
	
	/** 
	 * get http 
	 * @param url
	 * @param tag
	 */
	public static void sendGetHttp(String url,Map<String,Object> params,String cookies,String tag){
		 StringBuilder buf = new StringBuilder(url);
		if (!params.isEmpty()) { 
			 
	            if (url.indexOf("?") == -1)
	                buf.append("?");
	            else if (!url.endsWith("&"))
	                buf.append("&");
			Iterator<Map.Entry<String, Object>> entries=    params.entrySet().iterator();
			while (entries.hasNext()) {  
			    Map.Entry<String, Object> entry = entries.next();  
			    buf.append(String.valueOf(entry.getKey()))
                .append("=")
                .append(String.valueOf(entry.getValue()))
                .append("&");
			}  
			  buf.deleteCharAt(buf.length() - 1);
		}
		
		Request request = new Request.Builder()
				.url(buf.toString())
				.addHeader("content-type", "text/html;charset:utf-8")
				.addHeader("Cookie",cookies)
				.build();
		OkhttpUtils.println(tag+":"+buf.toString());
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String requestJson = OkhttpUtils.getResponseString(response);
				//OkhttpUtils.println(tag + ":" + requestJson);
				RxBus.getInstance().send(tag + ":" +requestJson);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
		
	}
}
