package com.gradle.android.retrofit;

import java.util.Map;

import com.gradle.java.model.ErrorInfo;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

@SuppressWarnings("unused")
public interface GitHubService {

	@GET("hello")
	Call<Object> listRepos();

	@GET("getMap")
	Call<Object> listMap();

	@GET("param/{userId}")
	Call<Object> listMap(@Path("userId") String id);

	@GET
	Call<Object> listMap(@Url String url, @Query("userId") String id, @Query("body") String body);

	@GET("param")
	Call<Object> paramGet(@Query("id") String id);

	@GET("param")
	Call<Object> paramGet(@QueryMap Map<String, Object> param, @Query("userId") long id);

	@POST("param")
	Call<Object> paramPost(@QueryMap Map<String, Object> param, @Query("userId") long id);

	@FormUrlEncoded
	@POST("param")
	Call<Object> paramPostField(@FieldMap Map<String, Object> param, @Query("userId") long id);

	@FormUrlEncoded
	@POST("param")
	Call<Object> paramPostBodyField(@Field("body") String title, @QueryMap Map<String, Object> param,
			@Query("userId") long id);

	// 多个请求体body,未验证成功
	// @Multipart
	// @Headers("Content-type:application/json;charset=UTF-8")
	// @POST("paramModel")
	// Call<Object> paramModel(@Part() MultipartBody.Part model,@Part()
	// MultipartBody.Part body,@Query("name") String name);

	@Headers("Content-type:application/json;charset=UTF-8")
	@POST("paramModel")
	Call<Object> paramModel(@Body ErrorInfo<String> model, @Query("name") String name);

	@Headers("Content-type:application/json;charset=UTF-8")
	@POST("paramBody")
	Call<Object> paramBody(@Body ErrorInfo<String> model, @Query("name") String name);

	/**
	 * post 普通参数请求
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	@FormUrlEncoded
	@POST()
	Call<Object> postParam(@Url String url, @FieldMap Map<String, Object> param);

	/**
	 * post 单个请求体
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	@POST()
	Call<Object> postBodyByString(@Url String url, @Body String body, @QueryMap Map<String, Object> param);

	/**
	 * post 单个请求体
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	@POST()
	Call<Object> postBodyByObject(@Url String url, @Body Object body, @QueryMap Map<String, Object> param);

	/**
	 * post 单个请求体
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	@POST()
	Call<Object> postBodyByModel(@Url String url, @Body ErrorInfo<String> body, @QueryMap Map<String, Object> param);

	/**
	 * 不允许多个@Body注解 post 单个请求体
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	@POST()
	Call<Object> postBodyByMuli(@Url String url, @Body String body, @Body String body2,
			@QueryMap Map<String, Object> param);

	/**
	 * @param url
	 * @param body
	 * @param body2
	 * @param param
	 * @return
	 */
	@Multipart
	@POST()
	Call<Object> postBodyByMulile(@Url String url, @Part MultipartBody.Part body, @Part MultipartBody.Part body2,
			@QueryMap Map<String, Object> param);
}
