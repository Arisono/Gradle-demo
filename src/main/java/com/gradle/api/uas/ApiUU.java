package com.gradle.api.uas;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gradle.java.utils.ExceptionUtils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/** 测试UU互联接口
 * @author Arison
 *
 */
/**
 * @author Arison
 *
 */
/**
 * @author Arison
 *
 */
public class ApiUU {

	private static String sessionId;
	private static String emcode;
	
	
	@SuppressWarnings("unused")
	private static final String baseurl_normal = "http://218.18.115.198:8888/ERP/";
	private static final String baseurl_origin = "http://218.17.158.219:8090/ERP/";
	@SuppressWarnings("unused")
	private static final String baseurl_test = "http://150.242.59.60:8099/ERP/";
	@SuppressWarnings("unused")
	private static final String master_normal="USOFTSYS";
	
	private static final String master_uas="UAS";
	
	private static final String master = master_uas;// UAS//USOFTSYS//DATACENTER
	private static final String baseurl = baseurl_origin;// 正式账套

	private static boolean debug=true;// 是否日志打印
	
	private static OkHttpClient client = new OkHttpClient.Builder()
	.connectTimeout(10, TimeUnit.SECONDS)
	.readTimeout(10, TimeUnit.SECONDS).build();

	public static void main(String[] args) {
//		loginManage("13266699268","111111");
		loginERP("13266699268", "111111", master); 
//		api_github_trends();
		
		loginIM();
	}

	

	/**
	 * 登录成功之后请求接口
	 */
	protected static void callbackResquest() {
	    //getNotApproved();//获取审批流接口
		getFormandGridDetail("Workovertime");// 配置表单   生成表单接口 Workovertime  Ask4Leave
		//getCompoData();//下拉接口
		//getDBFindData();//单选多选接口
		selectDetailData();// 配置表单 详细界面数据接口
		//saveFormData();//配置表单  保存数据接口
		//upateFormData();//配置表单  更新数据接口
	}

	
	
	
	public static void loadUrlNoParams(String url){
		Request request = new Request.Builder()
		        .url(url)
				.build();
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
					String json =getResponseString(response);
					println("load：" + json);
			}

			@Override
			public void onFailure(Call call, IOException e) {
			       onFailurePrintln(e);
			}
		});
	}
	
	
	// 登录模块
	// 登录管理平台
	public static void loginManage(String phone, String password) {
		String url ="http://manage.ubtob.com/public/account";
		RequestBody formBody = new FormBody.Builder()
				.add("user", phone)
				.add("password", password)
				.build();
		Request request = new Request.Builder()
		        .url(url)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
					String json =getResponseString(response);
					println("管理平台登录：" + json);
				
			}

			@Override
			public void onFailure(Call call, IOException e) {
			       onFailurePrintln(e);
			}
		});
	}

	// 登录IM
	public static void loginIM() {
		String url ="http://113.105.74.140:8092/user/login";
		RequestBody formBody = new FormBody.Builder()
				.add("latitude", "22.540691")
				.add("longitude", "113.95332")
				.add("model", "vivo X6SPlus D")
				.add("osVersion", "5.1.1")
				.add("password", "96e79218965eb72c92a549dd5a330112")
				.add("serial", "869897023236739")
				.add("telephone", "671ac63be0df529e97effa7dcd485310")
				.build();
		Request request = new Request.Builder()
		        .url(url)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
					String json =getResponseString(response);
					println("IM平台登录：" + json);
				
			}

			@Override
			public void onFailure(Call call, IOException e) {
			       onFailurePrintln(e);
			}
		});
	}

	// 登录ERP
	public static void loginERP(String user, String password, String master) {
		String url = baseurl + "/mobile/login.action";
		RequestBody formBody = new FormBody.Builder()
				.add("username", user)
				.add("password", password)
				.add("master", master).build();
		Request request = new Request.Builder()
		        .url(url)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
					String json =getResponseString(response);
					println("uas登录：" + json);
					sessionId = JSON.parseObject(json).getString("sessionId");// 拿到关键参数
					emcode = JSON.parseObject(json).getString("erpaccount");// 拿到关键参数
					callbackResquest();
			}

			@Override
			public void onFailure(Call call, IOException e) {
			       onFailurePrintln(e);
			}
		});
	}

	// 登录B2B
	public void loginB2B(String url, String user, String password) {

	}

	/**
	 * 接口 获取动态表单数据--请假单
	 * @throws UnsupportedEncodingException
	 *         Workovertime Ask4Leave
	 */
	public static void getFormandGridDetail(String caller) {
		if (caller == null) {
			caller = "Workovertime";
		}
		String url = baseurl
				+ "mobile/getformandgriddetail.action?caller=" + caller
				+ "&condition=1=1" + "&sessionId=" + sessionId+"&id=22239";
		println(url);
		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8").build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				println(getResponseString(response));
			}

			@Override
			public void onFailure(Call call, IOException e) {
				onFailurePrintln(e);
			}
		});

	}

	/**
	 * 接口 获取审批流程
	 */
	public static void getNotApproved() {
		// common/desktop/process/alreadyDo.action//已审批
		// common/desktop/process/toDo.action//未审批
		// common/desktop/process/alreadyLaunch.action//我发起的流程
		String url = baseurl
				+ "common/desktop/process/toDo.action?count=1000&page=1";
		// RequestBody formBody = new FormBody.Builder()
		// .add("count", "50")
		// .add("page", "1")
		// .add("start","0")
		// .add("limit", "25")
		// .build();
		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
					String json =getResponseString(response);
					println("获取未审批流程接口：" + json);
					sessionId = JSON.parseObject(json).getString("sessionId");// 拿到关键参数
					emcode = JSON.parseObject(json).getString("erpaccount");// 拿到关键参数
			}

			@Override
			public void onFailure(Call call, IOException e) {
			      onFailurePrintln(e);
			}
		});
	}

	/**
	 * 下拉接口
	 */
	public static void getCompoData() {
		String url = baseurl + "mobile/common/getCombo.action";
		RequestBody formBody = new FormBody.Builder().add("master", master)
				.add("field", "va_holidaytype").add("caller", "Ask4Leave")
				.add("sessionId", sessionId).build();
		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				getResponseString(response);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				onFailurePrintln(e);
			}
		});

	}

	/**
	 * @category 多选接口
	 */
	public static void getDBFindData() {
		String url = baseurl + "common/dbfind.action";
		RequestBody formBody = new FormBody.Builder().add("master", master)
				.add("which", "form").add("pageSize", "5")
				.add("condition", "1=1").add("field", "va_emname")
				.add("caller", "Ask4Leave").add("sessionId", sessionId)
				.add("page", "1").build();
		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
					String json =getResponseString(response);
					println(json);
					String dataStr = JSON.parseObject(json).getString("data");
					JSONArray datas = JSON.parseArray(dataStr);// values
					System.out.println(datas.getJSONObject(0).getString(
							"em_name"));
					JSONArray dbfinds = JSON.parseObject(json).getJSONArray(
							"dbfinds");
					String fieldkey = "";
					for (int i = 0; i < dbfinds.size(); i++) {
						JSONObject item = dbfinds.getJSONObject(i);
						String key = item.getString("field");
						if (key.equals("va_emname")) {
							System.out.println(item.getString("dbGridField"));
							fieldkey = item.getString("dbGridField");
						}
					}
					System.out.println("url:" + url);
					System.out.println("master:" + master);
					System.out.println("emcode:" + emcode);
					for (int i = 0; i < datas.size(); i++) {
						System.out.println("value" + i + ":"
								+ datas.getJSONObject(i).getString(fieldkey));
					}
			}

			@Override
			public void onFailure(Call call, IOException e) {
				onFailurePrintln(e);
			}
		});
	}


	/**
	 * 表单详细数据接口
	 * mobile/common/getformandgriddata.action?caller=Workovertime&id=12865
	 */
	public static void selectDetailData() {
		String url = baseurl + "mobile/common/getformandgriddata.action";
		RequestBody formBody = new FormBody.Builder()
				.add("master", master)
				.add("id", "12865")
				.add("caller", "Workovertime")
				.add("sessionId", sessionId).build();
		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
			    println( getResponseString(response));;
			}

			@Override
			public void onFailure(Call call, IOException e) {
				onFailurePrintln(e);
			}
		});

	}
	
	
	/**
	 * 动态表单保存 接口
	 */
	public static void saveFormData(){
		String url = baseurl + "/mobile/oa/commonSaveAndSubmit.action";
		RequestBody formBody = new FormBody.Builder()
				.add("master", master)
				.add("gridStore", "null")
				.add("formStore", "{\"va_holidaytype\":\"按小时\",\"va_vacationtype\":\"事假\",\"va_status\":\"在录入\",\"va_emcode\":\"sunquan\",\"va_emname\":\"龚鹏明\",\"va_department\":\"测试\",\"va_position\":\"测试\",\"va_mankind\":\"副总及以上\",\"va_alldays\":\"12\",\"va_alltimes\":\"25\",\"va_startime\":\"2016-11-28 13:39:00\",\"va_remark\":\"测试\",\"va_recordor\":\"刘佳\",\"va_date\":\"2016-11-28 13:39:00\",\"va_endtime\":\"2016-12-28 13:39:00\"}")
				.add("caller", "Ask4Leave")
				.add("sessionId", sessionId).build();

		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
			    println(getResponseString(response));
			}

			@Override
			public void onFailure(Call call, IOException e) {
				onFailurePrintln(e);
			}
		});
	
	}
	
	/**
	 * 动态表单  更新接口
	 */
	public static void upateFormData(){
		String url = baseurl + "mobile/commonUpdate.action";
		RequestBody formBody = new FormBody.Builder()
				.add("master", master)
				.add("gridStore", "null")
				.add("formStore", "{\"va_holidaytype\":\"按小时\",\"va_vacationtype\":\"事假\",\"va_status\":\"在录入\",\"va_emcode\":\"sunquan\",\"va_emname\":\"龚鹏明\",\"va_department\":\"测试111\",\"va_position\":\"测试\",\"va_mankind\":\"副总及以上\",\"va_alldays\":\"12\",\"va_alltimes\":\"25\",\"va_startime\":\"2016-11-28 13:39:00\",\"va_remark\":\"测试\",\"va_recordor\":\"刘佳\",\"va_date\":\"2016-11-28 13:39:00\",\"va_endtime\":\"2016-12-28 13:39:00\"}")
				.add("caller", "Ask4Leave")
				.add("sessionId", sessionId).build();

		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
			    println(getResponseString(response));
			}

			@Override
			public void onFailure(Call call, IOException e) {
				onFailurePrintln(e);
			}
		});
	
	}

	/**打印日志
	 * @param msg
	 */
	public static void println(String msg) {
		if (debug) {
			System.out.println(getLineInfo(false)+msg);
		}
	}

	/**获取代码当前行数
	 * @return
	 */
	public static String getLineInfo(boolean dispalyName) {
		StackTraceElement ste = new Throwable().getStackTrace()[2];
		if (dispalyName) {
			return ste.getFileName() + ":【第" + ste.getLineNumber()+"行】:";
		}else{
			return "【第" + ste.getLineNumber()+"行】:";
		}
	}

	/**统一的网络失败回调方法
	 * @param e
	 */
	public static void onFailurePrintln(IOException e) {
		println("onFailure:"
				+ ExceptionUtils.printExceptionStack(e));
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
	public static String getResponseString(Response response) throws IOException {
		if (response.isSuccessful()) {
			String json = response.body().string();
//		    println(json);
			return json;
		} else {
//			println(JSON.toJSONString("code:"
//					+ response.code()));
//			println(JSON.toJSONString("body:"
//					+ response.message()));
			return "code:"+response.code()+"  message:"+response.message();
		}
	}
	
	
	/**
	 * github trends
	 */
	public static void api_github_trends() {
		loadUrlNoParams("http://github.laowch.com/json/_daily");
		loadUrlNoParams("http://github.laowch.com/json/_weekly");
		loadUrlNoParams("http://github.laowch.com/json/_monthly");
		loadUrlNoParams("http://github.laowch.com/json/_yearly");//无效
	}
}
