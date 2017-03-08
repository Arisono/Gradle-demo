package com.gradle.api.uas;

import java.io.IOException;
import java.nio.Buffer;
import java.security.KeyStore.Entry;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.alibaba.fastjson.JSON;
import com.gradle.android.utils.OkhttpUtils;

import com.gradle.java.singleton.ApiBase;

import com.gradle.java.singleton.ApiConfig;
import com.gradle.java.singleton.ApiUtils;
import com.gradle.java.utils.DateFormatUtil;
import com.gradle.java.utils.StringUtils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author RaoMeng,Arison
 * 平台接口测试
 */
@SuppressWarnings("unused")
public class PlatformApi {
	
	private final static String METHOD_GET="get";
	private final static String METHOD_POST="post";
	private static String url_login_test = "http://113.105.74.135:8001/sso/login";
	private static String url_login_formal = "https://account.ubtob.com/sso/login";
	private static String cookies = "";
	private static String username = "15012345678";
	private static String password = "111111";
	private static ApiBase url=ApiConfig.getInstance(ApiUtils.getApiModel()).getmApiBase();

	public static void main(String[] args) {
	   taskRun();
	}


	protected static void loginCall() {

		Map<String, Object> params=new HashMap<>();
		params.put("emcode", "1000009169");
		params.put("enuu", "10041166");
		params.put("pageNumber", "1");
		params.put("pageSize", "10");
		sendHttp(url.list_vacation, params, "请假列表", METHOD_GET);
//		sendHttp(url.list_feePlease, params, "出差列表", METHOD_GET);
//		sendHttp(url.list_workOvertime, params, "加班列表", METHOD_GET);
		saveData();
	}


    
	private static void saveData() {
		String formStore=
			    "{\n"+
			            "        \"FP_N6\": null,\n"+
			            "        \"FP_PEOPLE2\": null,\n"+
			            "        \"emcode\": \"1000009169\",\n"+
			            "        \"enuu\": \"10030994\",\n"+
			            "        \"fp_code\": null,\n"+
			            "        \"fp_department\": null,\n"+
			            "        \"fp_id\": 0,\n"+
			            "        \"fp_kind\": null,\n"+
			            "        \"fp_preenddate\": \"2017-03-08 23:05:00\",\n"+
			            "        \"fp_prestartdate\": \"2017-03-08 16:04:00\",\n"+
			            "        \"fp_recorddate\": null,\n"+
			            "        \"fp_recordman\": null,\n"+
			            "        \"fp_status\": null,\n"+
			            "        \"fp_statuscode\": null,\n"+
			            "        \"fp_type\": null,\n"+
			            "        \"fp_v3\": \"测试\",\n"+
			            "        \"fp_v6\": null\n"+
			            "    }\n";
		String gridStore= "[{\n"+
	            "        \"FPD_D4\": null,\n"+
	            "        \"FPD_D6\": null,\n"+
	            "        \"Fpd_location\": \"测试\",\n"+
	            "        \"fpd_d2\": null,\n"+
	            "        \"fpd_date1\": null,\n"+
	            "        \"fpd_date2\": null,\n"+
	            "        \"fpd_detno\": 0,\n"+
	            "        \"fpd_fpid\": 0,\n"+
	            "        \"fpd_id\": 0,\n"+
	            "        \"fpd_location\": \"测试\"\n"+
	            "    }]";
		
		Map<String, Object> params=new HashMap<>();
		params.put("formStore", formStore);
		params.put("gridStore", gridStore);
		
		sendHttp(url.save_feePlease, params, "出差单保存：", METHOD_POST);
	}

	
	/**
	 * 登录 B2B
	 * 
	 * @param url
	 * @param username
	 * @param password
	 */
	public static void loginB2B(String url, String username, String password) {
		OkhttpUtils.println(username);
		OkhttpUtils.println(password);
		RequestBody formBody = new FormBody.Builder()
				.add("appId", "b2b")
				.add("username", username)
				.add("spaceId", "76035")
				.add("password", password)
				.build();
		Request request = new Request.Builder()
				.url(url)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		OkhttpUtils.println(url);
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				// OkhttpUtils.println("size:"+JSON.toJSONString(response.headers().size()));
				// OkhttpUtils.println("size
				// 4:"+JSON.toJSONString(response.headers().name(4)));
				// OkhttpUtils.println("size
				// 5:"+JSON.toJSONString(response.headers().name(5)));
				// //打印完整的cookie
				// OkhttpUtils.println("all
				// hearders:"+JSON.toJSONString(response.headers().toString()));
				// //打印完整的json格式数据
				// OkhttpUtils.println("all
				// hearders:"+JSON.toJSONString(response.headers().toMultimap()));
				// //打印多个key为 Set-Cookie的值
				// OkhttpUtils.println("set-cookie:"+JSON.toJSONString(response.headers("Set-Cookie")));
				// //打印多个key，多个key会自动放入数组里面
				// OkhttpUtils.println("set-cookie:"+JSON.toJSONString(response.header("Set-Cookie")));
				// OkhttpUtils.println("set-cookie
				// 5:"+JSON.toJSONString(response.headers().value(5)));
				// OkhttpUtils.println("set-cookie
				// 6:"+JSON.toJSONString(response.headers().value(6)));
				// OkhttpUtils.println(JSON.toJSONString(response));
				String json = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println("b2b登录：" + json);
				for (String iterable_element : response.headers("Set-Cookie")) {
					cookies = cookies + iterable_element + ";";
				}
				cookies = cookies.substring(0, cookies.length() - 1);
				loginCall();
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}

	
	/**
	 * Arison
	 * post请求回调
	 * @param url
	 * @param params
	 * @param testName
	 */
	public static void sendHttp(String url, Map<String, Object> params,String tag,String method){
		if ("get".equals(method)) {
			sendGetHttp(url, params, tag);
		}
		if("post".equals(method)){
			sendPostHttp(url,params,tag);
		}
		
	}
	
	
	/** 
	 * post http
	 * @param url
	 * @param params
	 * @param tag
	 */
	public static void sendPostHttp(String url,Map<String,Object> params,String tag){
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
				OkhttpUtils.println(tag + ":" + requestJson);
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
	public static void sendGetHttp(String url,Map<String,Object> params,String tag){
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
				OkhttpUtils.println(tag + ":" + requestJson);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
		
	}
	
	/**
	 * 循环任务
	 */
	public static void taskRun() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if(StringUtils.isEmpty(cookies)){
					loginB2B(url_login_test, username, password);
					}else{
						OkhttpUtils.println("會話保持："+cookies);
						loginCall();
					}
			}
		};
		timer.schedule(task, 5, 1000000);
	}
}
