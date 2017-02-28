package com.gradle.api.uas;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author RaoMeng
 *
 */
public class PlatformApi {
	//18328587849  123456  //测试
	private static String url_login_test="http://113.105.74.135:8001/sso/login";
	private static String url_login_formal="https://account.ubtob.com/sso/login";
	private static String cookies;
	private static String username="18328587849";
	private static String password="123456";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
         if (cookies==null) {
        	 loginB2B(url_login_test, username, password);
		}else{
			
		}
	}
	
	/**
	 * 登录 B2BString user, String password
	 * @param url
	 * @param username
	 * @param password
	 */
	public static void loginB2B(String url,String username,String password) {
		RequestBody formBody = new FormBody.Builder()
//		.add("appId", "sso")
		.add("appId", "b2b")
		.add("username", username)
		.add("spaceId", "76035")
		.add("password", password)
		.build();
		Request request = new Request.Builder().url(url)
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(formBody)
				.build();
		OkhttpUtils.println(url);
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {
			

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				OkhttpUtils.println("size:"+JSON.toJSONString(response.headers().size()));
				OkhttpUtils.println("size 4:"+JSON.toJSONString(response.headers().name(4)));
				OkhttpUtils.println("size 5:"+JSON.toJSONString(response.headers().name(5)));
				//打印完整的cookie
				OkhttpUtils.println("all hearders:"+JSON.toJSONString(response.headers().toString()));
				//打印完整的json格式数据
				OkhttpUtils.println("all hearders:"+JSON.toJSONString(response.headers().toMultimap()));
				//打印多个key为 Set-Cookie的值
			    OkhttpUtils.println("set-cookie:"+JSON.toJSONString(response.headers("Set-Cookie")));
			    //打印多个key，多个key会自动放入数组里面
			    OkhttpUtils.println("set-cookie:"+JSON.toJSONString(response.header("Set-Cookie")));
			    OkhttpUtils.println("set-cookie 5:"+JSON.toJSONString(response.headers().value(5)));
			    OkhttpUtils.println("set-cookie 6:"+JSON.toJSONString(response.headers().value(6)));
			    OkhttpUtils.println(JSON.toJSONString(response));
				String json = OkhttpUtils.getResponseString(response);
				OkhttpUtils.println("b2b登录：" + json);
				
			   cookies=	response.headers().value(5)+";"+response.headers().value(6);
				checkLoginAtB2B(cookies);
				
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}
	
	
	public static void checkLoginAtB2B(String cookie){
		String url ="http://uas.ubtob.com/authentication";
		OkhttpUtils.println(cookie);
//		cookie="JSESSIONID=AAD026810600CE98B72C1B035133B7AD; Path=/; HttpOnly,uid=4sjrtiHZOu3sG71-9lOyB1jEPXOxBgJ_3jjuugBiDcZs9xlBhPHqdaypUNveDTUwUV8V2UZiz_XyiF54d5oA8etWywLg7AiREkmq93ydcqvCVc4-lBf0bGEQMIIvck2mx-4UFRkgZW0SinAqerzvnjG20OD4co1xYOH3GvPFYozb_8vt7AARwVeoDvRWutb8Fn-30hx4ABq-71kD7qCxpVZVFV08Z18APcJLGWy3djt4tegS8kFjXdwfjNHgjUBhlxlbbJz2561foZeffYi4K51ayiZVLZjfo4hPHlbNMrCP-InSzgVh_kEzq4PmSr_2QWXbwH_PzLiT_QUf1oKLjECvqXpW0bJquxlEWRLRAXpmmGpu96yGszEdJ2d-XtHeCjXHOgBSe_UWEVFpM9us57u5rBiOWGF_qKb2X2sV3ZuQWAu2mCRRp8jFRP0lYGwjywCg2Lk-CPjHxGsSyKorqpHk6JNRAOLp2DVEKgRFDxHP9VpKfDYlHf756eDHW0Jpiym962jTu3ARMNbUaUMrOkmoS9DhuvhxdbyuK7KecyEG2flEdL6cA75Seb6z_egZw26Df3cFTPbtKseXGk4X3XU3vEmAAwl9ABOuz0Iuq6vkS46gfg..;domain=.ubtob.com;path=/;HTTPOnly;";
//		OkhttpUtils.println(cookie);
		Request request = new Request.Builder().url(url)
				.addHeader("content-type", "text/html;charset:utf-8")
				.addHeader("Cookie",  cookie)
				.build();
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

}
