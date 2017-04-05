package com.gradle.android.base;

import java.util.Map;

/**
 * @author Arison
 * 使用构建者设计模式封装
 */
public class HttpApiModel {
	
	private String url;
	private Map<String,Object> params;//请求参数
	private Map<String,Object> headers;//
	
	private int method;//方法
	private int isSyn;//是否是同步
	private int cacheType;//缓存类型
	private int maxRetryCount;//最大重试次数
	private boolean isDebug;//是否开启打印日志
	

}
