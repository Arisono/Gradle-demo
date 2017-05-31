package com.android.retrofit.demo;

import java.io.File;
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
import javax.xml.ws.http.HTTPException;

import com.alibaba.fastjson.JSON;
import com.android.base.net.HttpClient;
import com.android.retrofit.Interceptor.CustomLogger;
import com.android.retrofit.Interceptor.LogInterceptor;
import com.android.retrofit.Interceptor.RetryIntercepter;
import com.android.retrofit.demo.OkhttpUtils.TrustAllCerts;
import com.android.retrofit.demo.OkhttpUtils.TrustAllHostnameVerifier;
import com.gradle.java.rxjava.RxBus;
import com.gradle.java.utils.ExceptionUtils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.FormBody.Builder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;

/**
 * @author Arison
 * okhttp工具类
 */
@SuppressWarnings("unused")
public class OkhttpUtils {
	
	public static int maxLoadTimes=5;
	private static int serverLoadTimes=0;//
	private static boolean debug = true;// 是否日志打印
	
	public static OkHttpClient client = new OkHttpClient.Builder()
	.connectTimeout(10, TimeUnit.SECONDS)
	.readTimeout(10, TimeUnit.SECONDS)
	.sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())//信任所有证书
	.hostnameVerifier(new TrustAllHostnameVerifier())
	//.addInterceptor(new LogInterceptor())
//	.addInterceptor(new RetryIntercepter(3))
	.build();
	
	public static void initClient(HttpClient mbuilder){
		//本类保证初始化一次,减少系统开销
		 okhttp3.OkHttpClient.Builder okBuilder = client.newBuilder()
		.connectTimeout(mbuilder.getConnectTimeout(), TimeUnit.SECONDS)
		.readTimeout(mbuilder.getReadTimeout(), TimeUnit.SECONDS)
		.writeTimeout(mbuilder.getWriteTimeout(),TimeUnit.SECONDS)
		.sslSocketFactory(OkhttpUtils.createSSLSocketFactory(), new TrustAllCerts())//信任所有证书
		.hostnameVerifier(new TrustAllHostnameVerifier());
		 
		LogInterceptor logInterceptor= new LogInterceptor();
		logInterceptor.setBuilder(mbuilder);
		okBuilder.addInterceptor(logInterceptor);
		
		client=okBuilder.build();
	}
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
	public static void println(Object msg) {
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
			return "code:" + response.code() + "\n  message:"
					+ response.message()+"\n errorBody:"+response.body().string()
					+"\n 异常栈："+response;
		}
	}
	
	
	//http://blog.csdn.net/u013686019/article/details/52856389  信任所有证书
	public static class TrustAllCerts implements X509TrustManager {  
	    @Override  
	    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}  
	  
	    @Override  
	    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}  
	  
	    @Override  
	    public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}

		
	}  
	
	public static class TrustAllHostnameVerifier implements HostnameVerifier {  
	    @Override  
	    public boolean verify(String hostname, SSLSession session) {  
	        return true;  
	    }  
	}  
	
	public static SSLSocketFactory createSSLSocketFactory() {  
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
		serverLoadTimes=0;
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
				RxBus.getInstance().send(tag + ":" +requestJson);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(call,e,this);
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
		 if(params!=null){
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
		 }
		Request request = new Request.Builder()
				.url(buf.toString())
				.addHeader("content-type", "text/html;charset:utf-8")
				.addHeader("Cookie","12")
				.build();
		OkhttpUtils.println(tag+":"+buf.toString());
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String requestJson;
				requestJson = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println(requestJson);
				RxBus.getInstance().send(tag + ":" +requestJson);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(call,e,this);
			}
			
		});
		
	}
	
	
	/**
	 * 上传文件
	 * @param url
	 * @param params
	 * @param file
	 */
	public static void uploadFile(String url,Map<String,Object> params,String filePath){
		//创建File
        File file = new File(filePath);
        OkhttpUtils.println("上传文件名："+file.getName());
        RequestBody fileBody=RequestBody.create(
        		MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBody.Builder()
        		.setType(MultipartBody.FORM)
        		.addFormDataPart("file", file.getName(), fileBody)
        		.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String requestJson = OkhttpUtils.getResponseString(response);
				RxBus.getInstance().send("uploadFile"+ ":" +requestJson);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(call,e,this);
			}
		});
	}
	
	
	/**
	 * 多文件+参数 上传功能
	 * @param url
	 * @param params
	 * @param filePaths
	 */
	public static void uploadFile(String url,Map<String,Object> params){
		 MultipartBody.Builder builder = new MultipartBody.Builder();
         builder.setType(MultipartBody.FORM);
         //追加参数
         for (String key : params.keySet()) {
             Object object = params.get(key);
             if (!(object instanceof File)) {
                 builder.addFormDataPart(key, object.toString());
             } else {
                 File file = (File) object;
                 //其中参数“file”和服务器接收的参数 一一对应,保证多文件上传唯一key不变
                 builder.addFormDataPart("file", file.getName(), RequestBody.create(null, file));
             }
         }
       //创建RequestBody
         RequestBody body = builder.build();
         Request request = new Request.Builder()
                 .url(url)
                 .post(body)
                 .build();
         OkhttpUtils.client.newCall(request).enqueue(new Callback() {

 			@Override
 			public void onResponse(Call call, Response response) throws IOException {
 				String requestJson = OkhttpUtils.getResponseString(response);
 				RxBus.getInstance().send("uploadFile"+ ":" +requestJson);
 			}

 			@Override
 			public void onFailure(Call call, IOException e) {
 				OkhttpUtils.onFailurePrintln(call,e,this);
 			}
 		});
	}
	
	

	protected static void onFailurePrintln(Call call, IOException e,Callback callback) {
		if (e instanceof ConnectException) {
			println("服务器拒绝访问！");
		} else if (e instanceof SocketTimeoutException) {
			println("超时响应！");
		}else{
		
			OkhttpUtils.println(ExceptionUtils.printExceptionStack(e));
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if (serverLoadTimes<maxLoadTimes) {
			 serverLoadTimes++;
			 println("重新连接服务器"+serverLoadTimes);
		     OkhttpUtils.client.newCall(call.request()).enqueue(callback);;
		}		
	}
}
