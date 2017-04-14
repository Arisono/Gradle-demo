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

	private long retryTimeout = 5;
	private long connectTimeout;
	private long readTimeout;
	private long writeTimeout;

	private int method;// 方法
	private boolean isSyn;// 是否是同步
	private int cacheType;// 缓存类型
	private int maxRetryCount;// 最大重试次数
	private boolean isDebug;// 是否开启打印日志
	private Builder mBuilder;
	
	private HttpBase httpBase;
	
	
	public HttpClient() {
		super();
	}

	public HttpClient(Builder builder) {
		super();
		setBuilder(builder);
	}

	private void setBuilder(Builder builder) {
		this.baseUrl = builder.baseUrl;
		this.params = builder.params;
		this.headers = builder.headers;
		this.retryTimeout = builder.retryTimeout;
		this.connectTimeout=builder.connectTimeout;
		this.readTimeout=builder.readTimeout;
		this.writeTimeout=builder.writeTimeout;
		this.method = builder.method;
		this.isSyn = builder.isSyn;
		this.cacheType = builder.cacheType;
		this.maxRetryCount = builder.maxRetryCount;
		this.isDebug = builder.isDebug;
		this.httpBase=builder.httpBase;
	}


	private static HttpClient instance;

	public static HttpClient getInstance(Builder builder) {
		if (instance == null) {
			synchronized (HttpClient.class) {
				if (instance == null) {
					instance =newInstance(builder);
				}
			}
		}
		return instance;
	}
	
	public static HttpClient newInstance(Builder builder){
		instance = new HttpClient(builder);
		return instance;
	}
	
	

	public Builder newBuilder(String url) {
		return new Builder(url);
	}

	
	public static class Builder {
		
		private String baseUrl;
		private Map<String, Object> params = new HashMap<>();// 请求参数
		private Map<String, Object> headers = new HashMap<>();//

		
		private long connectTimeout;
		private long readTimeout;
		private long writeTimeout;
		
		private int method;// 方法
		private boolean isSyn;// 是否是同步
		private int cacheType;// 缓存类型
		private int maxRetryCount;// 最大重试次数
		private long retryTimeout = 5;//重试间隔时间
		private boolean isDebug;// 是否开启打印日志
		
		private HttpBase httpBase;//具体的网络请求类

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

		public Builder retryTimeout(long time) {
			this.retryTimeout = time;
			return this;
		}
		
		public Builder connectTimeout(long time) {
			this.connectTimeout = time;
			return this;
		}
		
		public Builder readTimeout(long time) {
			this.readTimeout = time;
			return this;
		}
		
		public Builder writeTimeout(long time) {
			this.writeTimeout = time;
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
		
		public Builder httpBase(HttpBase hb){
			this.httpBase=hb;
			return this;
		}

		public HttpClient build() {
			HttpClient client=newInstance(this);
			return client;
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

	public HttpBase Api() {
		httpBase.setBuilder(this);
		return httpBase;
	}

	public Builder getmBuilder() {
		return mBuilder;
	}

	public void setmBuilder(Builder mBuilder) {
		setBuilder(mBuilder);
		this.mBuilder = mBuilder;
	}
    
	
	
	public long getRetryTimeout() {
		return retryTimeout;
	}

	public long getConnectTimeout() {
		return connectTimeout;
	}

	public long getReadTimeout() {
		return readTimeout;
	}

	public long getWriteTimeout() {
		return writeTimeout;
	}


	public static class Method{
		public static int GET=0;
		public static int POST=1;
	}
	
}
