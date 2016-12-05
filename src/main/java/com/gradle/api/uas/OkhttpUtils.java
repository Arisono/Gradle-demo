package com.gradle.api.uas;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import com.gradle.java.utils.ExceptionUtils;

import okhttp3.OkHttpClient;
import okhttp3.Response;

public class OkhttpUtils {
	
	private static boolean debug = true;// 是否日志打印
	
	public static OkHttpClient client = new OkHttpClient.Builder()
	.connectTimeout(11, TimeUnit.SECONDS)
	.readTimeout(11, TimeUnit.SECONDS).build();
	
	
	/**
	 * 打印日志
	 * @param msg
	 */
	public static void println(String msg) {
		if (debug) {
			System.out.println(getLineInfo(false) + msg);
		}
	}

	/**
	 * 获取代码当前行数
	 * 
	 * @return
	 */
	public static String getLineInfo(boolean dispalyName) {
		StackTraceElement ste = new Throwable().getStackTrace()[2];
		if (dispalyName) {
			return ste.getFileName() + ":【第" + ste.getLineNumber() + "行】:";
		} else {
			return "【第" + ste.getLineNumber() + "行】:";
		}
	}

	/**
	 * 统一的网络失败回调方法
	 * 
	 * @param e
	 */
	public static void onFailurePrintln(IOException e) {
		println("onFailure:" + ExceptionUtils.printExceptionStack(e));
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
}
