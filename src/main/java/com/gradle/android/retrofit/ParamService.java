package com.gradle.android.retrofit;

import java.util.Map;

import com.gradle.java.model.ErrorInfo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
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
 */
public interface ParamService {
   
	@GET()
	Observable<Object> getParam(@Url String url);
	
	@GET()
	Observable<Object> getParam(@Url String url, 
			@QueryMap Map<String, Object> param);
	
	@GET()
	Observable<Object> getParam(@Url String url,
			@QueryMap Map<String, Object> param,
			@HeaderMap Map<String, Object> header);

	@FormUrlEncoded
	@POST()
	Observable<Object> postParam(@Url String url);
	
	@FormUrlEncoded
	@POST()
	Observable<Object> postParam(@Url String url,
			@FieldMap Map<String, Object> param);
	
	@FormUrlEncoded
	@POST()
	Observable<Object> postParam(@Url String url, 
			@FieldMap Map<String, Object> param,
			@HeaderMap Map<String, Object> header);




	@POST()
	Observable<Object> uploads(
			@Url String url,
			  @Body RequestBody body);
	
	
	
	@POST()
	Observable<Object> postBodyByString(@Url String url, @Body String body, @QueryMap Map<String, Object> param);

	@POST()
	Observable<Object> postBodyByObject(@Url String url, @Body Object body, @QueryMap Map<String, Object> param);

	@POST()
	Observable<Object> postBodyByModel(@Url String url, @Body ErrorInfo<String> body,
			@QueryMap Map<String, Object> param);

	@POST()
	Observable<Object> postBodyByMuli(@Url String url, @Body String body, @Body String body2,
			@QueryMap Map<String, Object> param);

	@Deprecated
	@Multipart
	@POST()
	Observable<Object> postBodyByMulile(@Url String url, @Part MultipartBody.Part body, @Part MultipartBody.Part body2,
			@QueryMap Map<String, Object> param);
	
	
	@Deprecated
	@GET()
	Call<Object> getExceptionCall(@Url String url);

	@Deprecated
	@GET()
	Observable<Object> getException(@Url String url);
}
