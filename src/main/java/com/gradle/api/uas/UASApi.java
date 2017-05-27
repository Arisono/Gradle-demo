package com.gradle.api.uas;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gradle.android.retrofit.OkhttpUtils;
import com.gradle.java.encryption.MD5Utils;
import com.gradle.java.model.DownloadRepoMessageEvent;
import com.gradle.java.rxjava.RxBus;
import com.gradle.java.rxjava.RxjavaUtils;
import com.gradle.java.utils.DateFormatUtil;
import com.gradle.java.utils.HmacUtils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * 测试UU互联接口 平台接口
 * 
 * @author Arison RaoMeng
 */
@SuppressWarnings("unused")
public class UASApi {
   
	private static String sessionId;
	private static String emcode;
	private static String cookies;
	
	
	private static final String baseurl_normal = "http://218.18.115.198:8888/ERP/";
	private static final String master_normal = "USOFTSYS";
	
	private static final String baseurl_uas = "https://218.17.158.219:8443/uas/";
	private static final String master_uas = "UAS";
	//测试
	private static final String baseurl_test = "http://192.168.253.252:8080/ERP/";
	private static final String master_test = "UAS_TEST";
	
	private static final String phone_test="13266699268";
	private static final String password_test="111111";

	private static final String master = master_test;
	private static final String baseurl = baseurl_test;
	
	private static String phone=phone_test;
	private static String password=password_test;
	private static Map<String, Object> params=new HashMap<>();
			

	public static void main(String[] args) {
		initRxjavaCall();
//		api_task_while();
		loginERP(phone, password, master); // uas系统登录
	}


	private static void api_task_while() {
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				OkhttpUtils.println("定时任务开始----");
				//loginManage(phone, password);// 管理平台登录
				
			}
		}, 1000,1000);
	}


	/**
	 * 登录成功之后请求接口
	 */
	protected static void callbackResquest() {
		//api_updateWorkDate();// 更新班次接口
		// getNotApproved();//获取审批流接口
		 //getFormandGridDetail("FeePlease!CCSQ");// 配置表单 生成表单接口 Workovertime  FeePlease!CCSQ
		// getFormandGridDetail("FeePlease!CCSQ");
		// getCompoData();//下拉接口
		// getDBFindData();//单选多选接口
		// selectDetailData();// 配置表单 详细界面数据接口
		// saveFormData();//配置表单 保存数据接口
		// upateFormData();//配置表单 更新数据接口
		// loginIM(MD5Utils.encode("13510107574"),
		// MD5Utils.encode("111111"));//登录IM系统
		// addWorkReport();//添加日报
		// getStagePoints();
		// api_isTurnToCustomer();
		// getCompoData("VisitRecord", "vr_type");
		// getCompoData("VisitRecord", "vr_nichestep");
		// api_getWorkDate();//获取班次
		// loadUrlNoParams("https://www.baidu.com/");//测试url
		// login();//定时任务
		
		
		//startTaskCard("2017-05-22");//打卡签到
		
		//getListMenuData();//动态表单，父级菜单
		
		 //api_getCaller();
		
		//新版加班单调整之后的接口测试
		//api_saveWorkExtra();
//		api_workExtra_uncommit();
//		api_workExtra_update();
		api_workExtra_dete();
	}
	
	
	/**
	 * 加班单反提交
	 */
	public static void api_workExtra_uncommit(){
		    params.put("caller", "ExtraWork$");
		    params.put("id", "32202");
//			params.put("formStore", "{\"wod_startdate\":\"2017-05-28 00:00:00\",\"wod_enddate\":\"2017-05-28 05:00:00\",\"wo_worktask\":\"哈哈哈姐姐\"}");
			OkhttpUtils.sendPostHttp(baseurl+"/hr/attendance/resSubmitExtraWork.action", params, "JSESSIONID=" + sessionId, "加班单反提交");
	}
	
	/**
	 * 加班单更新  需要反提交
	 */
	public static void api_workExtra_update(){
		    params.put("caller", "ExtraWork$");
		    params.put("id", "32202");
			params.put("formStore", "{\"wod_id\":32202,\"wod_startdate\":\"2017-05-29 00:00:00\",\"wod_enddate\":\"2017-05-29 05:00:00\",\"wo_worktask\":\"哈哈哈姐姐\"}");
			OkhttpUtils.sendPostHttp(baseurl+"mobile/oa/ExtraWorkUpdateAndSubmit.action", params, "JSESSIONID=" + sessionId, "加班单更新");
	}
	
	/**
	 * 加班单删除   需要反提交
	 */
	public static void api_workExtra_dete(){
		     params.put("id", "32202");
		     params.put("caller", "ExtraWork$");
			//params.put("formStore", "{\"wod_startdate\":\"2017-05-27 00:00:00\",\"wod_enddate\":\"2017-05-27 05:00:00\",\"wo_worktask\":\"哈哈哈姐姐\"}");
			OkhttpUtils.sendPostHttp(baseurl+"hr/attendance/deleteExtraWork.action", params, "JSESSIONID=" + sessionId, "加班单保存");
	}
	
	/**
	 * 加班单保存
	 */
	public static void api_saveWorkExtra(){
        params.put("caller", "ExtraWork$");
		params.put("formStore", "{\"wod_startdate\":\"2017-05-27 00:00:00\",\"wod_enddate\":\"2017-05-27 05:00:00\",\"wo_worktask\":\"哈哈哈姐姐\"}");
		OkhttpUtils.sendPostHttp(baseurl+"mobile/oa/ExtraWorkSaveAndSubmit.action", params, "JSESSIONID=" + sessionId, "加班单保存");
	}
	
	
	private static void api_getCaller(){
		
		OkhttpUtils.sendGetHttp(baseurl+"mobile/oa/getoaconifg.action", null, 
				"JSESSIONID=" + sessionId, "获取caller");
	}
	
	private static void getListMenuData(){
		OkhttpUtils.sendGetHttp(baseurl+"mobile/oa/getmenuconfig.action", null, 
				"JSESSIONID=" + sessionId, "菜单");
	}

	/**
	 * 打卡签到
	 * @param date
	 */
	private static void startTaskCard(String date) {
		addMobileMac("addMobileMac");
	
		//注册rxbus事件
		RxjavaUtils.registerSubscription(RxBus.getInstance().toObservable()
				.filter(o -> o instanceof DownloadRepoMessageEvent)
				.map(o -> (DownloadRepoMessageEvent) o)
				.doOnNext(o -> RxjavaUtils.showMessage(o.getMessage()))
				.subscribe(new Subscriber<DownloadRepoMessageEvent>() {

					@Override
					public void onCompleted() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onError(Throwable e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onNext(DownloadRepoMessageEvent t) {
						OkhttpUtils.println(t.getMessage());
						selectCardLog(date,"selectCardLog");
						
					}
				}));
	}

	static String json;
	/**
	 * 登录管理平台
	 * 
	 * @desc: get请求
	 * @param phone
	 * @param password
	 */
	public static void loginManage(String phone, String password) {
		//192.168.253.192:8080/platform-manage
		//manage.ubtob.com
		//192.168.253.60:9090/platform-manage
		String url = "http://manage.ubtob.com/public/account?user=" + phone
				+ "&password=" + password;
		url = url + "&_timestamp=" + System.currentTimeMillis();
		url = url + "&_signature=" + HmacUtils.encode(url);
		//OkhttpUtils.println("管理平台登录url:" + url);
		Request request = new Request.Builder().url(url)
				.addHeader("content-type", "text/html;charset:utf-8").build();
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				String json = OkhttpUtils.getResponseString(response);
				//String account=JSON.parseArray(json).getJSONObject(0).getString("account");
				RxBus.getInstance().send("管理平台："+json);
				//OkhttpUtils.println("管理平台：" + json);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.println("管理平台：异常"+e.getMessage()+" 时间："+DateFormatUtil.getDateTimeStr());
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
				.add("password", password)
				.add("master", master).build();
		Request request = new Request.Builder().url(url)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		OkhttpUtils.println(url);
		OkhttpUtils.println("user:" + user + " password:" + password
				+ " master:" + master);
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
				OkhttpUtils.println("失败");;
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}

	// 登录 B2BString user, String password
	public static void loginB2B() {
		String url = "https://account.ubtob.com/sso/login";
		RequestBody formBody = new FormBody.Builder().add("appId", "sso")
				.add("username", "13266703535").add("spaceId", "81744")
				.add("password", "1").build();
		Request request = new Request.Builder().url(url)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();
		OkhttpUtils.println(url);
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				OkhttpUtils.println("size:"
						+ JSON.toJSONString(response.headers().size()));
				OkhttpUtils.println("size 4:"
						+ JSON.toJSONString(response.headers().name(4)));
				OkhttpUtils.println("size 5:"
						+ JSON.toJSONString(response.headers().name(5)));
				// 打印完整的cookie
				OkhttpUtils.println("all hearders:"
						+ JSON.toJSONString(response.headers().toString()));
				// 打印完整的json格式数据
				OkhttpUtils.println("all hearders:"
						+ JSON.toJSONString(response.headers().toMultimap()));
				// 打印多个key为 Set-Cookie的值
				OkhttpUtils.println("set-cookie:"
						+ JSON.toJSONString(response.headers("Set-Cookie")));
				// 打印多个key，多个key会自动放入数组里面
				OkhttpUtils.println("set-cookie:"
						+ JSON.toJSONString(response.header("Set-Cookie")));
				OkhttpUtils.println("set-cookie 5:"
						+ JSON.toJSONString(response.headers().value(5)));
				OkhttpUtils.println("set-cookie 6:"
						+ JSON.toJSONString(response.headers().value(6)));
				OkhttpUtils.println(JSON.toJSONString(response));
				String json = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println("b2b登录：" + json);

				cookies = response.headers().value(5) + ";"
						+ response.headers().value(6);
				checkLoginAtB2B(cookies);

			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}

	public static void checkLoginAtB2B(String cookie) {
		String url = "http://uas.ubtob.com/authentication";
		OkhttpUtils.println(cookie);
		// cookie="JSESSIONID=AAD026810600CE98B72C1B035133B7AD; Path=/; HttpOnly,uid=4sjrtiHZOu3sG71-9lOyB1jEPXOxBgJ_3jjuugBiDcZs9xlBhPHqdaypUNveDTUwUV8V2UZiz_XyiF54d5oA8etWywLg7AiREkmq93ydcqvCVc4-lBf0bGEQMIIvck2mx-4UFRkgZW0SinAqerzvnjG20OD4co1xYOH3GvPFYozb_8vt7AARwVeoDvRWutb8Fn-30hx4ABq-71kD7qCxpVZVFV08Z18APcJLGWy3djt4tegS8kFjXdwfjNHgjUBhlxlbbJz2561foZeffYi4K51ayiZVLZjfo4hPHlbNMrCP-InSzgVh_kEzq4PmSr_2QWXbwH_PzLiT_QUf1oKLjECvqXpW0bJquxlEWRLRAXpmmGpu96yGszEdJ2d-XtHeCjXHOgBSe_UWEVFpM9us57u5rBiOWGF_qKb2X2sV3ZuQWAu2mCRRp8jFRP0lYGwjywCg2Lk-CPjHxGsSyKorqpHk6JNRAOLp2DVEKgRFDxHP9VpKfDYlHf756eDHW0Jpiym962jTu3ARMNbUaUMrOkmoS9DhuvhxdbyuK7KecyEG2flEdL6cA75Seb6z_egZw26Df3cFTPbtKseXGk4X3XU3vEmAAwl9ABOuz0Iuq6vkS46gfg..;domain=.ubtob.com;path=/;HTTPOnly;";
		// OkhttpUtils.println(cookie);
		Request request = new Request.Builder().url(url)
				.addHeader("content-type", "text/html;charset:utf-8")
				.addHeader("Cookie", cookie).build();
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				String json = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println("B2B登录检查：：" + json);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}

	/**
	 * 更新班次接口
	 */
	public static void api_updateWorkDate() {
		String url = baseurl + "mobile/updateWorkDate.action";
		String formStore = "{\"wd_offdutyone\":\"12:00\",\"wd_ondutythree\":\"\",\"wd_defaultor\":\"\",\"wd_code\":\"2017030036\",\"wd_man\":\"A01,A135,A011,敖峰,BYQY,C,C0001,C000104,C000102,C000101,C00010306,C00010305,C00010304,C00010303,C00010302,C00010301,C00010204,C00010203,C00010202,C00010201,C00010105,C00010104,C00010103,C00010102,C00010101,C00010204010,C00010204009,C00010204008,C00010204007,C00010204006,C00010204005,C00010204004,C00010204003,C00010204002,C00010204001,C00010203007,C00010203006,C00010203005,C00010203004,C00010203003,C00010203002,C00010203001,C00010202008,C00010202007,C00010202006,C00010202005,C00010202004,C00010202003,C00010202002,C00010202001,C00010201008,C00010201007,C00010201006,C00010201005,C00010201004,C00010201003,C00010201002,C00010201001,曹秋莲,CCS,曾绘龙,曾起飞,测试,测试,测试,ceshi,测试,测试,测试,测试H2,测试新增,CH,CH02,CH01,陈爱平,陈爱平,程会,程会,陈虎,陈佳,陈静,陈劲松,陈金金,陈璐,陈萍,陈瑞鸿,陈姝君,陈姝君,陈晓东,陈小虎,陈小龙,陈小小虎,陈玄奘,陈真,陈正亮,陈正明,陈正明,陈宗华,醇亲王,CS033,CS030,CS029,CS024,CS023,CS022,CS021,CS020,CS019,CS016,CS015,CS014,CS013,CS010,CS009,CS008,CS007,CS006,CS005,cscscs,cscscs,CYYT,大安,大毛,邓国超,丁无,丁一,丁一,丁英琳,丁正元,董必伟,方龙海,付家华,龚鹏明,恭亲王,苟安,管理员,管理员,郭丽亚,国胜,何建清,黄俊,黄耀鹏,黄玉林,胡兴文,江勇,康燕波,酷酷,Leo,连冰花,廖华,廖益强,李聪元,李党武,李剑辉,李明亮,林子健,刘杰,刘俊娟,刘莉,刘萌冰,刘明辉,流陌陌,刘鹏,刘鹏,刘鹏1,刘玉栋,刘兆星,李洋洋,龙晓兰,lp,卢浩光,卢浩光,罗强,罗研飞,吕全明,马,马超,马丹,马昭,马昭,马昭,梅林聪,起飞,庆亲王,邱永红,饶猛,沈佳,SO,孙曲芳,孙土桂,谭岳鸿,谭焯怡,test,test001,天派测试,王二,王凯,王焜坤,WANGPANZ,王文曜,王一,韦学先,翁理科,翁理科,吴事原,吴事原,wusy,wwy,肖成龙,晓兰,小马,肖舒婷,熊晨阳,熊志新,许春山,徐健,徐诗,杨丹,叶芊,业务经理001,业务员001,叶鈺柳,应鹏,易紫燕,柚子,YS02,YS01,余佳,余佳1,臧亚诚,ZGB,ZGS1,ZGS1,张頔,张佩盛,张一二,张一一,张长,章政,张仲林,赵斌,赵文,钟军秀,中文明,钟燕玲,钟勇斌,周兵,周袁,邹美玲,1,6,5,333,555,1314,5555, 胡志强\",\"wd_degree\":2,\"wd_name\":\"很知足\",\"wd_offdutytwo\":\"18:00\",\"wd_emcode\":\"A01,A135,A011,AOF,BYQY,E3344,C0001,C000104,C000102,C000101,C00010306,C00010305,C00010304,C00010303,C00010302,C00010301,C00010204,C00010203,C00010202,C00010201,C00010105,C00010104,C00010103,C00010102,C00010101,C00010204010,C00010204009,C00010204008,C00010204007,C00010204006,C00010204005,C00010204004,C00010204003,C00010204002,C00010204001,C00010203007,C00010203006,C00010203005,C00010203004,C00010203003,C00010203002,C00010203001,C00010202008,C00010202007,C00010202006,C00010202005,C00010202004,C00010202003,C00010202002,C00010202001,C00010201008,C00010201007,C00010201006,C00010201005,C00010201004,C00010201003,C00010201002,C00010201001,U0770,CCS,U0737,U0764,test201703001,cscscs,CESHI1,CESHI,89859,888,123123,UTEST02,20170227,CH,CH02,CH01,U0758,CHENAP,U0202,CHENGH,A021,U0762,U0501,CHENJINSONG,CHENJJ,U0326,chenp,CRH,U0709,CHENSJ,CXD,CXH,A023,A0211,CHENXZ,CHENZ,CHENZL,CHENZM,CHENZHENGMING,CHENZH,A233,CS033,CS030,CS029,CS024,CS023,CS022,CS021,CS020,CS019,CS016,CS015,CS014,CS013,CS010,CS009,CS008,CS007,CS006,CS005,test13141,test0001,CYYT,1103,A1,DENGGC,test20161212,CS1001,CS0607,dingyl,U0412,U0751,U0747,A020,U0736,GONGQW,GOUA,admin,ADMIN,U0409,A033,U0732,U0768,A0232323,U0323,HUXW,U0726,U0739,A0011,CHENJS,LIANBH,U0503,LIAOYQ,licy,U0708,U0731,U0707,U0761,L00010102002,LIUJ,U0502,U0723,U0712,U0711,liupeng,U0755,LIUPENG1,LIUYD,U0717,LIYY,U0763,lp,luhg,U0730,LUOQ,LUOYF,LVQM,ma,mazz,MAD,mazhao,U0754,MAZ,meilc,SZ001,A356,QIUYH,AH0001,A001,CT6666,A019,U0314,A036,U0714,test20170301,test201731,CAOXH,2015121902,U0715,U0734,111111,WWY,WANGYI,WXX,WENGLK,U0703,WSY,W1314,WUSY,wwytest55,U0752,U1122,XIAOMA,xiaost,XIONGCY,U0741,U0103,U0705,XUSHI,U0721,U0713,TEST008,TEST001,U0733,YINGP,U0725,A0001,YS02,YS01,U1112,YUJ,U0745,ZGB,ZGS12,ZGS1,U0767,U0727,ZHANGYIER,ZHANGYIYI,ZHANGCHANG,U0728,ZHANGZL,ZHAOBIN,ZHAOWEN,U0722,AYA051,zhongyl,U0101,U0735,zhouy,ZML,CESHI2,6,5,wwytest3,01,CS0405,C00010204011,U0729\",\"wd_offdutythree\":\"\",\"wd_ondutyone\":\"08:00\",\"wd_day\":\"7\",\"wd_ondutytwo\":\"13:30\",\"wd_pcount\":257,\"wd_id\":6536,\"wd_earlytime\":2,\"wd_defaultorcode\":\"\"}";
		RequestBody formBody = new FormBody.Builder().add("caller", "WorkDate")
				.add("master", "UAS_TEST")
				// .add("emcode", emcode)
				.add("formStore", formStore).build();
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
				OkhttpUtils.println("更新班次接口：" + json);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});

	}

	/**
	 * 获取班次
	 */
	public static void api_getWorkDate() {
		String url = baseurl + "mobile/getWorkDate.action";
		RequestBody formBody = new FormBody.Builder().add("date", "20170214")
				.add("master", master).add("emcode", emcode).build();
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
				// loginManage("13530100540", "58278668");// 管理平台登录
				if (cookies == null) {
					loginB2B();
				} else {
					checkLoginAtB2B(cookies);
				}
			}
		};
		timer.schedule(task, 5, 1000);
	}
	
	
	public static void addMobileMac(String method){
		//master=USOFTSYS, emcode=U0316, sessionUser=U0316, sessionId=29DB60DE6E40D859B9169FE5013A8520, macAddress=3c:b6:b7:64:a7:ea
		String url = baseurl + "mobile/addMobileMac.action";
		RequestBody formBody = new FormBody.Builder()
				.add("master", master)
				.add("emcode",emcode)		
				.add("macAddress", "3c:b6:b7:64:a7:ea")
				.add("sessionId", sessionId)
				.build();

		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();

		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				OkhttpUtils.println(OkhttpUtils.getResponseString(response),OkhttpUtils.typeMiddle,method);
				getStringCode("getStringCode");
				return;
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}
	
	 static String code = "";
	
	public static void getStringCode(String method){
		
		//master=USOFTSYS, sessionUser=U0316, sessionId=29DB60DE6E40D859B9169FE5013A8520, caller=CardLog, type=2
		String url = baseurl + "common/getCodeString.action";
		RequestBody formBody = new FormBody.Builder()
				.add("master", master)
				.add("type","2")		
				.add("caller", "CardLog")
				.add("sessionId", sessionId)
				.build();

		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();

		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
			    String result=	OkhttpUtils.getResponseString(response);
			    OkhttpUtils.println(result,OkhttpUtils.typeMiddle,method);
			
				code=JSON.parseObject(result).getString("code");
				saveCardLog("12.12", code, "saveCardLog");
				
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	
	}
	
	
	public static void saveCardLog(String dis,String code,String method){
		//master=USOFTSYS, sessionUser=U0316, sessionId=29DB60DE6E40D859B9169FE5013A8520, caller=CardLog, type=2
		String formStore=""
				+ "{\"cl_emname\":\"刘杰\","
				+ "\"cl_distance\":"+dis+","
				+ "\"cl_time\":"+"2017-05-22 08:22:10"+","
				+ "\"cl_emcode\":\"U0316\","
				+ "\"cl_phone\":\"13266699268\","
				+ "\"cl_code\":\""+code+"\","
				+ "\"cl_location\":\"在英唐大厦附近\","
				+ "\"cl_address\":\"中国广东省深圳市南山区科技南五路5\"}";
		OkhttpUtils.println(formStore);
		String url = baseurl + "mobile/saveCardLog.action";
		RequestBody formBody = new FormBody.Builder()
				.add("master", master)	
				.add("caller", "CardLog")
				.add("formStore", formStore)
				.add("sessionId", sessionId)
				.build();

		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();

		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
			    String result=	OkhttpUtils.getResponseString(response);
			    OkhttpUtils.println(result,OkhttpUtils.typeMiddle,method);
			    RxBus.getInstance().send(new DownloadRepoMessageEvent("保存成功！"));
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
		
	}
	
	public static void selectCardLog(String date,String method){
		//{master=USOFTSYS, emcode=U0316, pageSize=100, sessionUser=U0316, 
		//condition=cl_emcode='U0316' and to_char(cl_time,'yyyy-MM-dd')='2017-03-03', 
		//sessionId=29DB60DE6E40D859B9169FE5013A8520, caller=CardLog, page=1, currentMaster=USOFTSYS}
		String url = baseurl + "/mobile/oa/workdata.action";
		RequestBody formBody = new FormBody.Builder()
				.add("currentMaster", master)
				.add("master", master)
				.add("emcode",emcode)	
				.add("condition", "cl_emcode='U0316' and to_char(cl_time,'yyyy-MM-dd')='"+date+"'")
				.add("caller", "CardLog")
				.add("page", "1")
				.add("sessionId", sessionId)
				.build();

		Request request = new Request.Builder().url(url)
				.header("cookie", "JSESSIONID=" + sessionId)
				.addHeader("sessionUser", emcode)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody).build();

		OkhttpUtils.client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
			    String result=	OkhttpUtils.getResponseString(response);
			    OkhttpUtils.println(result,OkhttpUtils.typeMiddle,method);
			
		
				
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
		
	}
	
	
	private static void initRxjavaCall() {
		RxBus.getInstance().toObservable().subscribe(new Subscriber<Object>() {

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
			OkhttpUtils.println(t.toString());
				
			}
		});
	}

}
