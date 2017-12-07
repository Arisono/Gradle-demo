package com.gradle.test.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.android.retrofit.demo.OkhttpUtils;
import com.gradle.design.config.ApiBase;
import com.gradle.design.config.ApiConfig;
import com.gradle.design.config.ApiPlatform;
import com.gradle.design.config.ApiUtils;
import com.gradle.java.rxjava.RxBus;
import com.gradle.java.utils.StringUtils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import rx.Observer;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author RaoMeng,Arison
 * 平台接口测试
 */
/**
 * @author Arison
 *
 */
@SuppressWarnings("unused")
public class testPlatformApi {
	
	private final static String METHOD_GET="get";
	private final static String METHOD_POST="post";
	private static String url_login_test = "http://113.105.74.135:8001/sso/login";
	private static String url_login_formal = "https://uas.ubtob.com/sso/login";
	
	private static String url_login = url_login_test;
	private static String username = "15270003113";
	private static String password = "1";
	private static String cookies = "";
	private static String enuu = "";
	private static String emcode = "";
	private static Map<String, Object> params=new HashMap<>();
	/**
	 * 统一的接口地址封装
	 */
	private static ApiBase url=ApiConfig.getInstance(ApiUtils.getApiModel()).getmApiBase();

	/**
	 * main函数
	 * @param args
	 */
	public static void main(String[] args) {
	
		//测试环境  正式环境
//		url.setmBaseUrl(ApiPlatform.mBaseUrl_developer );
//		//管理平台登录---参数
		testUASApi.loginManage(username, password);// 管理平台登录
//		//执行登录
//	    taskRun();
//	    //执行异步任务
//	    observer();
	    //{"username":"18680669690","spaceId":"88745","password":"111111","appId":"b2b"}
	 //   loginB2B(url_login_formal, "18680669690", password);
	}
	
	/**
	 * 登录成功后的回调方法
	 * 支持get post接口调用
	 * 所有的接口测试写在这里！！！
	 */
	protected static void loginCall() {
		//考勤单据
		//api_attendance();
	    //日报列表
		//api_worklogs();
		//单据保存
		loginParamsCall();
		//审批流列表
		api_approval();
	    //任务接口测试
		//api_task();
	}


	private static void api_worklogs() {
		Map<String, Object> params;
		params=new HashMap<>();
		params.put("emcode", "1000009169");
		params.put("enuu", "10041166");
		params.put("pageNumber", "1");
		params.put("pageSize", "10");
		OkhttpUtils.sendHttp(url.daily_work_url, params,cookies , "日报列表", METHOD_GET);
	}


	private static void api_approval() {
		Map<String, Object> params;
		params=new HashMap<>();
		params.put("emuu", "1000002802");//1000009169
		params.put("enuu", "10030994");//10041166
		params.put("count", "10");
		params.put("page", "1");
		OkhttpUtils.sendHttp(url.getAuditTodo, params,cookies , "审批流列表-未审批", METHOD_GET);
		OkhttpUtils.sendHttp(url.getAuditDone, params,cookies , "审批流列表-已审批", METHOD_GET);
		
		//单据详情
		params=new HashMap<>();
		params.put("id", "21");
		params.put("whichpage", "3");
		OkhttpUtils.sendGetHttp(url.mBaseUrl+"mobile/detailCenter/getDetail", params, cookies, "审批流详情");;
	}


	/**考勤单据
	 * @return
	 */
	private static Map<String, Object> api_attendance() {
		Map<String, Object> params;
		params=new HashMap<>();
		params.put("emcode", "1000009169");
		params.put("enuu", "10041166");
		params.put("pageNumber", "1");
		params.put("pageSize", "10");
		//考勤单据
		OkhttpUtils.sendHttp(url.list_vacation, params, cookies ,"请假列表", METHOD_GET);
		OkhttpUtils.sendHttp(url.list_feePlease, params, cookies ,"出差列表", METHOD_GET);
		OkhttpUtils.sendHttp(url.list_workOvertime, params,cookies , "加班列表", METHOD_GET);
		return params;
	}


	private static void api_task() {
		String formStore= "{\n" +
                "\"detail\":\"测试\",\n" +
                "\"recordercode\":\"1000009169\",\n" +
                "\"uu\":\"10030994\",\n" +
                "\"taskname\":\"刘的请假单\",\n" +//任务名称
//                "\"recorder\":\"10041166\",\n" +
//                "\"doman\":\"余佳\",\n" +
                "\"domancode\":\"1000009169,1000009169,1000009169\",\n" +
                "\"startdate\":\"2017-03-20 09:12:00\",\n" +
                "\"enddate\":\"2017-03-20 12:12:00\"\n" +
                "}";
		String formStore1="{\n" +
	               "\"detail\":\"liujie 测试\",\n" +
	               "\"recordercode\":\"1000009169\",\n" +
	               "\"recorder\":\"10041166\",\n" +
	               "\"doman\":\"余佳,曹秋莲\",\n" +
	               "\"domancode\":\"U1112,U0770\",\n" +
	               "\"startdate\":\"2017-03-20 09:12:00\",\n" +
	               "\"enddate\":\"2017-03-20 12:12:00\"\n" +
	               "}\n";
		//保存
		Map<String, Object> params;
		params=new HashMap<>();
		params.put("formStore", formStore);
		OkhttpUtils.sendPostHttp(url.task_save, params, cookies, "任务保存：");
		//列表
		String statu="";
		params=new HashMap<>();
		params.put("emuu", "1000009169");
		params.put("enuu", "10030994");
		params.put("pageNumber", "1");
		params.put("pageSize", "200");
		params.put("status", statu);
		
		OkhttpUtils.sendGetHttp(url.task_list, params, cookies, "任务列表"+statu+":");
		
		//获取任务回复
		params=new HashMap<>();
		params.put("id", "81");
		OkhttpUtils.sendGetHttp(url.task_reply, params, cookies, "任务回复列表：");
		//任务状态变更
		formStore= "{\n" +
	               "\"id\":\"81\",\n" +//单据id
	               "\"status\":\"待确认\",\n" +//状态
	               "\"statuscode\":\"unconfirmed\"\n" +//状态码
	               "}";
		String gridStore="{\n" +
	              "\"taskid\":\"81\",\n" +//单据id
	              "\"replyman\":\"1000009169\",\n" +//个人uu 
	              "\"Replytime\":\"2017-03-22 09:00:00\",\n" +
	              "\"Replydetail\":\"dsajfdsaf\",\n" +//回复内容
	              "\"uu\":\"10030994\"\n" +//企业uu enUU
	              "}";
		
		params=new HashMap<>();
		params.put("formStore", formStore);
		params.put("gridStore", gridStore);
//		OkhttpUtils.println("formStore:"+formStore);
//		OkhttpUtils.println("gridStore:"+gridStore);
		OkhttpUtils.sendPostHttp(url.task_change, params, cookies, "任务变更状态：");
		//通讯录人员列表
		params=new HashMap<>();
		params.put("enuu", "10041166");
		OkhttpUtils.sendGetHttp(url.getUsersInfo, params, cookies, "人员列表：");
	}


    
	/**
	 * 表单数据录入专有测试方法
	 * 保存json数据  统一测试方法
	 */
	private static void loginParamsCall() {
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
		String gridStore= 
					"[{\n"+
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
//		OkhttpUtils.println(formStore);
//		OkhttpUtils.println(gridStore);
		OkhttpUtils.sendHttp(url.save_feePlease, params,cookies ,"出差单保存：", METHOD_POST);
		
		//请假单
		params=new HashMap<>();
		params.put("formStore", formStore);
		params.put("enuu", "10041166");
		formStore="{\"emcode\":\"1000009169\","
				+ "\"enuu\":\"10041166\","
				+ "\"va_alldays\":0.0,"
				+ "\"va_alltimes\":0.0,"
				+ "\"va_code\":null,"
				+ "\"va_date\":null,"
				+ "\"va_emcode\":null,"
				+ "\"va_emname\":null,"
				+ "\"va_endtime\":\"2017-03-19 15:46:00\","
				+ "\"va_holidaytype\":null,"
				+ "\"va_id\":0,"
				+ "\"va_mankind\":null,"
				+ "\"va_recordor\":null,"
				+ "\"va_remark\":\"句警察局\","
				+ "\"va_startime\":\"2017-03-17 15:46:00\","
				+ "\"va_status\":null,"
				+ "\"va_statuscode\":null,"
				+ "\"va_vacationtype\":\"病假\"}";
		OkhttpUtils.sendHttp(url.save_vacation, params,cookies ,"请假单保存：", METHOD_POST);
	}

	
	/**
	 * 登录 B2B
	 * {"username":"18680669690","spaceId":"88745","password":"111111","appId":"b2b"}
	 * @param url
	 * @param username
	 * @param password
	 */
	public static void loginB2B(String url, String username, String password) {
		RequestBody formBody = new FormBody.Builder()
				.add("appId", "b2b")
				.add("username", username)
			    .add("spaceId", "88745")
				//.add("spaceId", "76035")
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
				OkhttpUtils.println("cookies:" +cookies );
				loginCall();
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.println("登录b2b,超时响应"+e);
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
					loginB2B(url_login, username, password);
					}else{
						OkhttpUtils.println("會話保持："+cookies);
						loginCall();
					}
			}
		};
		timer.schedule(task, 5, 1000000);
	}
	
	
	private static void observer() {
		RxBus.getInstance().toObservable()
		   .subscribe(new Observer<Object>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub
				
			}
		     
			@Override
			public void onNext(Object t) {
				//网络接口返回结果---这里打印
				OkhttpUtils.println(t.toString());
			}
		});
	}
}