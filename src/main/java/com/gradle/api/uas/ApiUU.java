package com.gradle.api.uas;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gradle.java.utils.HmacUtils;
import com.gradle.java.utils.MD5Utils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 测试UU互联接口
 * 
 * @author Arison
 */
@SuppressWarnings("unused")
public class ApiUU {

	private static final String baseurl_normal = "http://218.18.115.198:8888/ERP/";
	private static final String baseurl_uas = "http://218.17.158.219:8090/ERP/";
	private static final String baseurl_test = "http://218.18.115.198:8887/ERP/";
	private static final String master_normal = "USOFTSYS";
	private static final String master_uas = "UAS";
	private static final String master_test = "USOFT_MALL";

	private static final String master = master_uas;// UAS//USOFTSYS//DATACENTER
	private static final String baseurl = baseurl_uas;// 正式账套
	private static String sessionId;
	private static String emcode;

	public static void main(String[] args) {
//		loginManage("13266699268", "111111");//管理平台登录
//		loginERP("13266699268", "111111", master); // uas系统登录13691965521
		
//		loginManage("13691965521", "13691965521");
//		loginERP("13691965521", "13691965521", master);
	}

	/**
	 * 登录成功之后请求接口
	 */
	protected static void callbackResquest() {
		// getNotApproved();//获取审批流接口
		// getFormandGridDetail("Ask4Leave");// 配置表单 生成表单接口 Workovertime
		// Ask4Leave
		// getFormandGridDetail("FeePlease!CCSQ");
		// getCompoData();//下拉接口
		// getDBFindData();//单选多选接口
		// selectDetailData();// 配置表单 详细界面数据接口
		// saveFormData();//配置表单 保存数据接口
		// upateFormData();//配置表单 更新数据接口
		// "13510107574","111111"
		// loginIM(MD5Utils.encode("13510107574"),
		// MD5Utils.encode("111111"));//登录IM系统
		// addWorkReport();//添加日报

		// getStagePoints();
		// api_isTurnToCustomer();
		// vr_type，vr_nichestep
        //getCompoData("VisitRecord", "vr_type");
        //getCompoData("VisitRecord", "vr_nichestep");
		api_getWorkDate();//获取班次
	}

	
	/**
	 * 获取班次
	 */
	public static void api_getWorkDate(){
		String url = baseurl + "mobile/getWorkDate.action";
		RequestBody formBody = new FormBody.Builder()
				.add("date", "20170214")
				.add("master", master)
				.add("emcode", emcode)
				.build();
		OkhttpUtils.println(url);
		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				String json = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println("班次接口：" + json);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
		
	}
	
	public static void api_isTurnToCustomer() {
		String url = baseurl + "/mobile/crm/isTurnToCustomer.action";
		RequestBody formBody = new FormBody.Builder()
				.add("bccode", "2016120035").add("master", master).build();
		OkhttpUtils.println(url);
		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				String json = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println("isTurnToCustomer：" + json);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}

	public static void getStagePoints() {
		String url = baseurl + "mobile/crm/getStagePoints.action";
		RequestBody formBody = new FormBody.Builder()
				.add("bccode", "2016120035").add("currentStep", "testpoints")
				.add("master", master).build();
		OkhttpUtils.println(url);
		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				String json = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println("getStagePoints()：" + json);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}

	/**
	 * 测试接口参数
	 */
	public static void loadPostParams() {
		RequestBody formBody = new FormBody.Builder()
				.add("data",
						"[{\"bi_inqty\":\"0.0\",\"bi_prodid\":\"0\",\"bi_piid\":\"50707678\",\"bi_location\":\"\",\"bi_inoutno\":\"MG161200001\",\"bi_prodcode\":\"\",\"bi_whcode\":\"CK005\",\"bi_barcode\":\"7690867143120700426\"},{\"bi_inqty\":\"0.0\",\"bi_prodid\":\"0\",\"bi_piid\":\"50707678\",\"bi_location\":\"\",\"bi_inoutno\":\"MG161200001\",\"bi_prodcode\":\"\",\"bi_whcode\":\"CK005\",\"bi_barcode\":\"7690867143120700429\"},{\"bi_inqty\":\"0.0\",\"bi_prodid\":\"0\",\"bi_piid\":\"50707678\",\"bi_location\":\"\",\"bi_inoutno\":\"MG161200001\",\"bi_prodcode\":\"\",\"bi_whcode\":\"CK005\",\"bi_barcode\":\"7690867143120700430\"}]")
				// .add("type", "类型")
				.build();
		// String json_1 = "{'type':'dfdf','data','你好1'}";
		// String json_2 = "{'type':'dfdf','data','你好2'}";
		// String json_3 = "{'type':'dfdf','data','你好3'}";
		Request request = new Request.Builder()
				.addHeader("content-type", "application/json;charset:utf-8")
				.url("http://192.168.253.200:8080/spring-mvc-showcase/api/paramBody3")
				// .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
				// json_1))
				// .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
				// json_2))
				// .post(formBody)
				.put(formBody).build();
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				String json = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println("load：" + json);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}

	/**
	 * 加载一般url
	 * 
	 * @param url
	 */
	public static void loadUrlNoParams(String url) {
		Request request = new Request.Builder().url(url).build();
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				String json = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println("load：" + json);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}

	/**
	 * 登录管理平台
	 * 
	 * @desc: get请求
	 * @param phone
	 * @param password
	 */
	public static void loginManage(String phone, String password) {
		String url = "http://manage.ubtob.com/public/account?user=" + phone
				+ "&password=" + password;
		url = url + "&_timestamp=" + System.currentTimeMillis();
		url = url + "&_signature=" + HmacUtils.encode(url);
		OkhttpUtils.println("管理平台登录接口url:" + url);
		Request request = new Request.Builder().url(url)
				.addHeader("content-type", "text/html;charset:utf-8").build();
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				String json = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println("管理平台登录接口返回值：" + json);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}

	/**
	 * 登录IM系统
	 * 
	 * @param phone
	 * @param password
	 */
	public static void loginIM(String phone, String password) {
		String url = "http://113.105.74.140:8092/user/login";
		RequestBody formBody = new FormBody.Builder()
				.add("latitude", "22.540691").add("longitude", "113.95332")
				.add("model", "vivo X6SPlus D").add("osVersion", "5.1.1")
				.add("password", password)
				// 手机的IMEL
				.add("serial", "869897023236739").add("telephone", phone)
				.build();
		Request request = new Request.Builder().url(url)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				String json = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println("IM平台登录：" + json);

			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}

	// 登录ERP
	public static void loginERP(String user, String password, String master) {
		String url = baseurl + "mobile/login.action";
		RequestBody formBody = new FormBody.Builder().add("username", user)
				.add("password", password).add("master", master).build();
		Request request = new Request.Builder().url(url)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		OkhttpUtils.println(url);
		OkhttpUtils.println("user:"+user+" password:"+password+" master:"+master);
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				String json = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println("uas登录：" + json);
				sessionId = JSON.parseObject(json).getString("sessionId");// 拿到关键参数
				emcode = JSON.parseObject(json).getString("erpaccount");// 拿到关键参数
				callbackResquest();
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}

	// 登录B2B
	public void loginB2B(String url, String user, String password) {

	}

	/**
	 * 接口 获取动态表单数据--请假单
	 * 
	 * @throws UnsupportedEncodingException
	 *             Workovertime Ask4Leave
	 */
	public static void getFormandGridDetail(final String caller) {
		String url = baseurl + "mobile/getformandgriddetail.action?caller="
				+ caller + "&condition=1=1" + "&sessionId=" + sessionId
				+ "&id=-1";
		OkhttpUtils.println(url);
		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8").build();
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				OkhttpUtils.println(caller + ":"
						+ OkhttpUtils.getResponseString(response));
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
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
				+ "common/desktop/process/toDo.action?count=10&page=2&page=1";
		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8").build();
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				String json = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println("获取未审批流程接口：" + json);
				sessionId = JSON.parseObject(json).getString("sessionId");// 拿到关键参数
				emcode = JSON.parseObject(json).getString("erpaccount");// 拿到关键参数
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}

	/**
	 * 下拉接口
	 */
	public static void getCompoData(String caller, String field) {
		String url = baseurl + "mobile/common/getCombo.action";
		RequestBody formBody = new FormBody.Builder().add("master", master)
				.add("field", field).add("caller", caller)
				.add("sessionId", sessionId).build();
		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				OkhttpUtils.println(OkhttpUtils.getResponseString(response));;
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
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
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				String json = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println(json);
				String dataStr = JSON.parseObject(json).getString("data");
				JSONArray datas = JSON.parseArray(dataStr);// values
				System.out.println(datas.getJSONObject(0).getString("em_name"));
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
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}

	/**
	 * 表单详细数据接口
	 * mobile/common/getformandgriddata.action?caller=Workovertime&id=12865
	 */
	public static void selectDetailData() {
		String url = baseurl + "mobile/common/getformandgriddata.action";
		RequestBody formBody = new FormBody.Builder().add("master", master)
				.add("id", "12865").add("caller", "Workovertime")
				.add("sessionId", sessionId).build();
		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				OkhttpUtils.println(OkhttpUtils.getResponseString(response));
				;
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});

	}

	/**
	 * 动态表单保存 接口
	 */
	public static void saveFormData() {
		String url = baseurl + "/mobile/oa/commonSaveAndSubmit.action";
		OkhttpUtils.println(url);
		RequestBody formBody = new FormBody.Builder()
				.add("master", master)
				.add("gridStore", "null")
				.add("formStore",
						"{\"va_holidaytype\":\"按小时\",\"va_vacationtype\":\"事假\",\"va_status\":\"在录入\",\"va_emcode\":\"sunquan\",\"va_emname\":\"龚鹏明\",\"va_department\":\"测试\",\"va_position\":\"测试\",\"va_mankind\":\"副总及以上\",\"va_alldays\":\"12\",\"va_alltimes\":\"25\",\"va_startime\":\"2016-11-28 13:39:00\",\"va_remark\":\"测试\",\"va_recordor\":\"刘佳\",\"va_date\":\"2016-11-28 13:39:00\",\"va_endtime\":\"2016-12-28 13:39:00\"}")
				.add("caller", "Ask4Leave").add("sessionId", sessionId).build();

		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();

		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				OkhttpUtils.println(OkhttpUtils.getResponseString(response));
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});

	}

	/**
	 * 动态表单 更新接口
	 */
	public static void upateFormData() {
		String url = baseurl + "mobile/commonUpdate.action";
		RequestBody formBody = new FormBody.Builder()
				.add("master", master)
				.add("gridStore", "null")
				.add("formStore",
						"{\"va_holidaytype\":\"按小时\",\"va_vacationtype\":\"事假\",\"va_status\":\"在录入\",\"va_emcode\":\"sunquan\",\"va_emname\":\"龚鹏明\",\"va_department\":\"测试111\",\"va_position\":\"测试\",\"va_mankind\":\"副总及以上\",\"va_alldays\":\"12\",\"va_alltimes\":\"25\",\"va_startime\":\"2016-11-28 13:39:00\",\"va_remark\":\"测试\",\"va_recordor\":\"刘佳\",\"va_date\":\"2016-11-28 13:39:00\",\"va_endtime\":\"2016-12-28 13:39:00\"}")
				.add("caller", "Ask4Leave").add("sessionId", sessionId).build();

		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();

		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				OkhttpUtils.println(OkhttpUtils.getResponseString(response));
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});

	}

	/**
	 * 添加日报
	 */
	public static void addWorkReport() {
		String url = baseurl + "mobile/addWorkReport.action";
		RequestBody formBody = new FormBody.Builder()
				.add("master", master)
				.add("formStore",
						"{\"wd_experience\":\"\",\"wd_comment\":\"13.37一二三四五六七八九十一二三四五六七八九\\n一二三四五六七八九十一二三四五六七八九\\n一二三四五六七八九十一二三四五六七八九\\n一二三四五六七八九十一二三四五六七八九\\n一二三四五六七八九十一二三四五六七八\\n一二三四五六七八九十一二三四五六七八九\\n一二三四五六七八九十一二三四五六七八九\\n一二三四五六七八九十一二三四五六七八九\\n一二三四五六七八九十一二三四五六七八九\\n一二三四五六七八九十一二三四五六七八\\n一二三\",\"wd_empcode\":\"U0747\",\"wd_plan\":\"\"}")
				.add("caller", "WorkDaily").add("sessionId", sessionId).build();

		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();

		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				OkhttpUtils.println(OkhttpUtils.getResponseString(response));
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});

	}

	/**
	 * github trends
	 */
	public static void api_github_trends() {
		loadUrlNoParams("http://github.laowch.com/json/_daily");
		loadUrlNoParams("http://github.laowch.com/json/_weekly");
		loadUrlNoParams("http://github.laowch.com/json/_monthly");
		loadUrlNoParams("http://github.laowch.com/json/_yearly");// 无效
	}
	
	
	/**
	 * @定时任务
	 */
	public static void login() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				loginManage("13530100540", "58278668");// 管理平台登录
			}
		};
		timer.schedule(task, 5, 1000);
	}
}
