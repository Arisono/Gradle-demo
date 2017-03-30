package com.gradle.android.retrofit;

import java.util.Map;

import com.gradle.java.model.ErrorInfo;


import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;


/**
 * @desc:主要用于HTTP参数测试
 * @name:ParamService
 * @method:Rxjava+Retrofit
 * @author Arison
 *
 */
public interface ParamService {
	/**
	 * post 普通参数请求
	 * @param url
	 * @param param
	 * @return
	 */
	@FormUrlEncoded
	@POST()
	Observable<Object> postParam(@Url String url, @FieldMap Map<String, Object> param);
	
	
	/**
	 * post 单个请求体 
	 * @param url
	 * @param param
	 * @return
	 */
	@POST()
	Observable<Object> postBodyByString(@Url String url, @Body String body,@QueryMap Map<String, Object> param);
	
	/**
	 * post 单个请求体 
	 * @param url
	 * @param param
	 * @return
	 */
	@POST()
	Observable<Object> postBodyByObject(@Url String url, @Body Object body,@QueryMap Map<String, Object> param);
	
	
	/**
	 * post 单个请求体 
	 * @param url
	 * @param param
	 * @return
	 */
	@POST()
	Observable<Object> postBodyByModel(@Url String url, @Body ErrorInfo<String> body,@QueryMap Map<String, Object> param);
	/**
	 * 不允许多个@Body注解
	 * post 单个请求体 
	 * @param url
	 * @param param
	 * @return
	 */
	@POST()
	Observable<Object> postBodyByMuli(@Url String url, @Body String body,@Body String body2,@QueryMap Map<String, Object> param);
	
	/**
	 * @param url
	 * @param body
	 * @param body2
	 * @param param
	 * @return
	 */
	@Multipart
	@POST()
	Observable<Object> postBodyByMulile(@Url String url, @Part MultipartBody.Part body,@Part MultipartBody.Part body2,@QueryMap Map<String, Object> param);
}
