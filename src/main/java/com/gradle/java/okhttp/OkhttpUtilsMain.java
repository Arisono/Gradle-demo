package com.gradle.java.okhttp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;
import com.gradle.java.encryption.RSAUtils;
import com.gradle.java.utils.ExceptionUtils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Arison
 * @funtion：okhttp http请求示例程序 @dec： 1，请求头添加 2，get,post等方式的请求 3，带参数请求
 *                 4，post+json提交数据
 * 
 */
public class OkhttpUtilsMain {
	public static final MediaType JSONTYPE = MediaType.parse("application/json; charset=utf-8");

	public static void main(String[] args) {
		// sendFormParams();//RSA加密
		// sendSysResquest();//异步请求
		// sendHeaders();//请求头
		sendPostBody();
	}

	public static void sendPostBody() {
		OkHttpClient client =
				new OkHttpClient.Builder()
				.connectTimeout(5, TimeUnit.SECONDS)
				.readTimeout(5, TimeUnit.SECONDS)
				.build();

		Request request = new Request.Builder()
				.url("http://message.ubtob.com/sms/send")
				.addHeader("content-type", "application/json")
				.post(
						RequestBody.create(
								MediaType
										.parse("text/html,application/json,application/xml;q=0.9,image/webp,*/*;q=0.8"),
								"{\"receiver\":\"13266699268\",\"params\":[\"刘杰\"],\"templateId\":\"4b60e18b-de2e-410f-9de1-819265d9e636\"}"))
				.build();

		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (response.isSuccessful()) {
					String json = response.body().string();
					System.out.println(json);
				} else {
					System.out.println(JSON.toJSONString(response.code()));
				}
			}

			@Override
			public void onFailure(Call call, IOException e) {
				System.out.println(ExceptionUtils.printExceptionStack(e));
				if (e instanceof ConnectException) {
					System.out.println("服务器拒绝访问！");
				} else if (e instanceof SocketTimeoutException) {
					System.out.println("超时响应！");
				}
			}
		});
	}

	/**
	 * Okhttp 异步请求
	 */
	public static void sendSysResquest() {
		RequestBody formBody =
				new FormBody.Builder().add("username", "123").add("password", "df13edafsdddsads").build();

		OkHttpClient client =
				new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS).build();

		Request request = new Request.Builder()
				.url("http://localhost:8080/spring-mvc-showcase/client/info")
				.header("cookie", "JSESSIONID=EB36DE5E50E342D86C55DAE0CDDD4F6D")
				.addHeader("content-type", "text/html;charset:utf-8")

				.post(formBody)
				.build();

		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (response.isSuccessful()) {
					String json = response.body().string();
					System.out.println(json);
				} else {
					System.out.println(JSON.toJSONString(response.code()));
				}
			}

			@Override
			public void onFailure(Call call, IOException e) {
				System.out.println(ExceptionUtils.printExceptionStack(e));
				if (e instanceof ConnectException) {
					System.out.println("服务器拒绝访问！");
				} else if (e instanceof SocketTimeoutException) {
					System.out.println("超时响应！");
				}
			}
		});

	}

	/**
	 * 表单提交
	 */
	public static void sendFormParams() {
		Map<String, Object> publicMap = RSAEncodeToken();
		String miwen = (String) publicMap.get("miwen");
		String public_key = (String) publicMap.get("public_key");

		RequestBody formBody = new FormBody.Builder()
				.add("username", "123")
				.add("password", "df13edafsdddsads")
				.add("publicKey", public_key)
				.add("miwen", miwen)
				.build();// getParameterNames接收

		String json_1 = "{}";// postBody 接收

		@SuppressWarnings("unused")
		String bytes = "username=123&password=df13edafsdddsads";

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("http://localhost:8080/spring-mvc-showcase/http/getHeaders")
				.header("cookie", "JSESSIONID=EB36DE5E50E342D86C55DAE0CDDD4F6D")
				.addHeader("content-type", "text/html;charset:utf-8")
				.post(RequestBody.create(JSONTYPE, json_1))
				.post(formBody)
				// .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
				// bytes))
				.build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				String json = response.body().string();
				System.out.println(json);
			} else {
				System.out.println(JSON.toJSONString(response.code()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("******************************************************");
	}

	/**
	 * 客户端加密token -RSA算法
	 * 
	 * @return
	 */
	public static Map<String, Object> RSAEncodeToken() {
		Map<String, Object> result = new HashMap<String, Object>();
		// 客户端保存公钥用于加密数据，把私钥发向服务器，服务器拿到私钥解密
		// 服务器用私钥加密，返回给客户端，客户端用公钥解密
		Map<String, byte[]> keyMap = RSAUtils.generateKeyBytes();
		// 加密
		byte[] publicKey = keyMap.get(RSAUtils.PUBLIC_KEY);
		// 解密
		byte[] privateKey = keyMap.get(RSAUtils.PRIVATE_KEY);

		String public_key = Base64.encodeBase64String(publicKey);// 字符串
		String private_key = Base64.encodeBase64String(privateKey);// 字符串

		System.out.println("公钥：" + public_key);
		System.out.println("私钥：" + private_key);

		// 密文
		byte[] encodedText = RSAUtils.RSAEncode(Base64.decodeBase64(public_key), RSAUtils.PLAIN_TEXT.getBytes());
		String miwen = Base64.encodeBase64String(encodedText);
		System.out.println("RSA encoded: " + miwen);

		System.out.println(
				"RSA decoded: " + RSAUtils.RSADecode(Base64.decodeBase64(private_key), Base64.decodeBase64(miwen)));
		result.put("public_key", private_key);
		result.put("miwen", miwen);

		return result;
	}

	/**
	 * 发请求头以及请求参数
	 */
	public static void sendHeadersAndParams() {
		String china_str = "";
		try {
			china_str = URLEncoder.encode("中文", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");
		String postBody = "Hello World";
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("http://localhost:8080/spring-mvc-showcase/http/getHeaders")
				.header("cookie", "JSESSIONID=EB36DE5E50E342D86C55DAE0CDDD4F6D")
				.addHeader("content-type", "text/html;charset:utf-8")
				.addHeader("Home", "china")
				// 自定义的header
				.addHeader("Home1", china_str)
				// 自定义的header
				.addHeader("user-agent", "android")
				.put(RequestBody.create(MEDIA_TYPE_TEXT, postBody))

				// .post(RequestBody.create(MEDIA_TYPE_TEXT, postBody))
				.build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				String json = response.body().string();
				System.out.println(json);
				String home1 = JSON.parseObject(json).getJSONObject("headers").getString("home1");
				System.out.println(URLDecoder.decode(home1, "utf-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发请求头
	 */
	public static void sendHeaders() {
		String china_str = "";
		try {
			china_str = URLEncoder.encode("中文", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("http://192.168.253.132:9090/platform-b2c/?client=true")
				.header("cookie", "JSESSIONID=EB36DE5E50E342D86C55DAE0CDDD4F6D")
				.addHeader("content-type", "text/html;charset:utf-8")
				.addHeader("client-name", "uasClient")// 自定义的header
				.addHeader("Home1", china_str)// 自定义的header 传中�?
				.addHeader("user-agent", "android")
				.build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				String json = response.body().string();
				System.out.println(json);
				String home1 = JSON.parseObject(json).getJSONObject("headers").getString("home1");
				System.out.println(URLDecoder.decode(home1, "utf-8"));
			} else {
				System.out.println(JSON.toJSONString(response.code()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void okBasicRequest() {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("http://www.baidu.com").build();
		try {
			Response response = client.newCall(request).execute();
			if (!response.isSuccessful()) {
			}
			Headers responseHeaders = response.headers();
			for (int i = 0; i < responseHeaders.size(); i++) {
				System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
