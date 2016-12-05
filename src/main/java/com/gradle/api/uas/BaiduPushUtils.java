package com.gradle.api.uas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSONObject;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushBatchUniMsgRequest;
import com.baidu.yun.push.model.PushBatchUniMsgResponse;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;

/**
 * @author Arison 百度推送工具类
 * 百度推送内部的api指定了json-lib-2.4-jdk15来解析数据，批量推送设备的时候，fastjson不能取代
 * json-lib-2.4-jdk15，否则会报找不到类的错误
 * 
 */
public class BaiduPushUtils {
  //http://fir.im/usoftchina
	private static BaiduPushClient pushClient;
	private static String title = "UU互联内部测试版下载公告提醒！";
	private static String desc = "移动部Android组提醒您！";
//	private static String content = "     郑重提醒您，今日测试版本发布，欢迎下载！！ \n\n "
//			+ "                    移动部门温馨提示     #http://fir.im/usoftchina";
	
	private static String content = "UU下载链接地址，请点击#http://fir.im/usoftchina";
	private static String apiKey = "EmEVqG9NiKchcSbkoGkiyG2F2rp8YNmf";
	private static String secretKey = "vys9xmWtx2Oerv83usNtU64OEWOpz0Gq";

	private static String channelId = "3661901606382112872";// 单设备
//3662584296662527934
	private static String[] channelIds = { 
////	"3860966617109273119",//米三
        "3662584296662527934",//测试邓
		"3537149100550334139",//测试 陈
		"3683060365130437158",//测试 陈
//		 "3955136970545093253", // 小米四 测试机
		"3821126972469299968",//小明哥
//		 "3661901606382112872" // me
//		,"4214122687662249823"//vivo 3,
//		,"4593168493483957705"//产品
	};
	

	public static void main(String[] args) throws PushClientException,
			PushServerException {
		initBaiduPushClient();
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
		for (int i = 0; i < 1000; i++) {
			fixedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					
					try {
						Thread.sleep(1000);
						//pushStart(title,desc);//单设备
						pushBatchDevice(title, desc);// 多设备
					} catch (PushClientException e) {
						e.printStackTrace();
					} catch (PushServerException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});

		}

	}

	/**
	 * 单设备推送
	 * 
	 * @param title
	 * @param desc
	 * @throws PushClientException
	 * @throws PushServerException
	 */
	@SuppressWarnings("unused")
	private static void pushStart(String title, String desc)
			throws PushClientException, PushServerException {
		try {
			JSONObject notification = new JSONObject();
			notification.put("title", title);
			notification.put("description", desc);
			notification.put("notification_builder_id", 0);
			notification.put("notification_basic_style", 0x02 + 0x01 + 0x04);// 声音，震动，铃声全开
			notification.put("open_type", 2);
			// android客户端指定界面地址
			notification
					.put("pkg_content",
							"#Intent;component=com.xzjmyk.pm.activity/.ui.message.uas.B2bMsgActivity;end");
			// notification.put("url", "http://push.baidu.com");
			JSONObject jsonCustormCont = new JSONObject();
			jsonCustormCont.put("title", "待处理流程001"); // 鑷畾涔夊唴瀹癸紝key-value
			jsonCustormCont
					.put("url",
							"jsps/mobile/task.jsp?caller=ResourceAssignment!Bill%26id=11472"); // 鑷畾涔夊唴瀹癸紝key-value
			jsonCustormCont.put("master", "USOFTSYS");
			jsonCustormCont.put("uu", "10041166");
			jsonCustormCont.put("pageTitle", "商务消息");
			jsonCustormCont.put("masterId", "2929");
			jsonCustormCont
					.put("content",
							"UU互联测试版本下载"
									+ "这条内容是测试推送内容，请勿下载！后期就用这种方式提醒下载测试版本，请知须！下载地址是：#http://fir.im/usoftchina");
			// jsonCustormCont.put("platform", "ERP");
			// jsonCustormCont.put("title", "待处理流程001");
			// jsonCustormCont.put("url",
			// "jsps/mobile/process.jsp?nodeId=15340079");
			// jsonCustormCont.put("master", "UAS");
			// jsonCustormCont.put("uu", "10041166");
			// jsonCustormCont.put("masterId", "2929");
			notification.put("custom_content", jsonCustormCont);

			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
					.addChannelId(channelId).addMsgExpires(new Integer(1))
					.addMessageType(1).addMessage(notification.toString())
					.addDeviceType(3);// deviceType => 3:android, 4:ios
			// 5. http request
			PushMsgToSingleDeviceResponse response = pushClient
					.pushMsgToSingleDevice(request);

			System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
					+ response.getSendTime());
		} catch (PushClientException e) {

			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
	}

	/**
	 * 多设备推送
	 * 
	 * @param title
	 * @param desc
	 * @throws PushClientException
	 * @throws PushServerException
	 */
	public static void pushBatchDevice(String title, String desc)
			throws PushClientException, PushServerException {
		try {
			JSONObject notification = new JSONObject();
			notification.put("title", title);
			notification.put("description", "移动部Android组提醒您！请点击此通知下载测试版本！");
			notification.put("notification_builder_id", 0);
			notification.put("notification_basic_style", 0x02 + 0x01 + 0x04);
			notification.put("open_type", 2);
			notification
					.put("pkg_content",
							"#Intent;component=com.xzjmyk.pm.activity/.ui.message.uas.B2bMsgActivity;end");
            //自定义的json格式
			JSONObject jsonCustormCont = new JSONObject();
			jsonCustormCont.put("title", "待处理流程001"); 
			jsonCustormCont
					.put("url",
							"jsps/mobile/task.jsp?caller=ResourceAssignment!Bill%26id=11472"); // 鑷畾涔夊唴瀹癸紝key-value
			jsonCustormCont.put("master", "USOFTSYS");
			jsonCustormCont.put("uu", "10041166");
			jsonCustormCont.put("pageTitle", "商务消息");
			jsonCustormCont.put("masterId", "2929");
			jsonCustormCont.put("content", content);
			notification.put("custom_content", jsonCustormCont);

			PushBatchUniMsgRequest request = new PushBatchUniMsgRequest()
					.addChannelIds(channelIds).addMsgExpires(new Integer(3600))
					.addMessageType(1).addMessage(notification.toString())
					.addDeviceType(3).addTopicId("BaiduPush");// 设置类别主题
			// 5. http request
			PushBatchUniMsgResponse response = pushClient
					.pushBatchUniMsg(request);
			// Http请求结果解析打印
			System.out.println(String.format("msgId: %s, sendTime: %d",
					response.getMsgId(), response.getSendTime()));
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
	}

	/**
	 * init buduPushClient
	 */
	public static void initBaiduPushClient() {
		// 1. get apiKey and secretKey from developer console

		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		// 2. build a BaidupushClient object to access released interfaces
		pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});
	}
}
