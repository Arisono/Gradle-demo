package com.gradle.android.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Arison 使用构建者设计模式封装
 */
public class HttpClient {

	private String baseUrl;;
	private Map<String, Object> params = new HashMap<>();// 请求参数
	private Map<String, Object> headers = new HashMap<>();//

	private long timeout = 5;

	private int method;// 方法
	private boolean isSyn;// 是否是同步
	private int cacheType;// 缓存类型
	private int maxRetryCount;// 最大重试次数
	private boolean isDebug;// 是否开启打印日志
	
	
	
	public HttpClient() {
		super();
	}

	public HttpClient(String baseUrl, Map<String, Object> params, Map<String, Object> headers, long timeout, int method,
			boolean isSyn, int cacheType, int maxRetryCount, boolean isDebug) {
		super();
		this.baseUrl = baseUrl;
		this.params = params;
		this.headers = headers;
		this.timeout = timeout;
		this.method = method;
		this.isSyn = isSyn;
		this.cacheType = cacheType;
		this.maxRetryCount = maxRetryCount;
		this.isDebug = isDebug;
	}


	private static HttpClient instance;

	public static HttpClient getInstance(Builder builder) {
		if (instance == null) {
			synchronized (HttpClient.class) {
				if (instance == null) {
					instance = new HttpClient(builder.baseUrl,builder.params
							,builder.headers,builder.timeout,builder.method,
							builder.isSyn,builder.cacheType,builder.maxRetryCount
							,builder.isDebug);
				}
			}
		}
		return instance;
	}
	
	public static HttpClient newInstance(Builder builder){
		instance = new HttpClient(builder.baseUrl,builder.params
				,builder.headers,builder.timeout,builder.method,
				builder.isSyn,builder.cacheType,builder.maxRetryCount
				,builder.isDebug);
		return instance;
	}
	
	

	public Builder newBuilder(String url) {
		return new Builder(url);
	}

	
	public static class Builder {
		private String baseUrl;;
		private Map<String, Object> params = new HashMap<>();// 请求参数
		private Map<String, Object> headers = new HashMap<>();//

		private long timeout = 5;

		private int method;// 方法
		private boolean isSyn;// 是否是同步
		private int cacheType;// 缓存类型
		private int maxRetryCount;// 最大重试次数
		private boolean isDebug;// 是否开启打印日志

		public Builder(String url) {
			this.baseUrl = url;
		}

		public Builder add(String key, Object value) {
			this.params.put(key, value);
			return this;
		}

		public Builder header(String key, Object value) {
			this.headers.put(key, value);
			return this;
		}

		public Builder timeout(long time) {
			this.timeout = time;
			return this;
		}

		public Builder cacheType(int cacheType) {
			this.cacheType = cacheType;
			return this;
		}

		public Builder maxRetryCount(int maxRetryCount) {
			this.maxRetryCount = maxRetryCount;
			return this;
		}

		public Builder isDebug(boolean isDebug) {
			this.isDebug = isDebug;
			return this;
		}

		public Builder method(int method) {
			this.method = method;
			return this;
		}

		public Builder syn(boolean isSyn) {
			this.isSyn = isSyn;
			return this;
		}

		public HttpClient build() {
			return newInstance(this);
		}
	}


	public String getBaseUrl() {
		return baseUrl;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public Map<String, Object> getHeaders() {
		return headers;
	}

	public long getTimeout() {
		return timeout;
	}

	public int getMethod() {
		return method;
	}

	public boolean isSyn() {
		return isSyn;
	}

	public int getCacheType() {
		return cacheType;
	}

	public int getMaxRetryCount() {
		return maxRetryCount;
	}

	public boolean isDebug() {
		return isDebug;
	}

	
}
