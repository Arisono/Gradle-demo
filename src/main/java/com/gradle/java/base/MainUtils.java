package com.gradle.java.base;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gradle.android.utils.RegexUtil;
import com.gradle.java.utils.DateFormatUtil;
import com.gradle.java.utils.StringUtils;
import com.gradle.model.Users;

/**
 * @author Arison Java 语法测试类
 */
@SuppressWarnings("unused")
public class MainUtils {
	/** custom maximum value this axis represents */
	protected static float mCustomAxisMax = Float.NaN;
	private static int counter = 0;

	public static void main(String[] args) {
	    test07();
	}

	/**
	 * 
	 */
	public static void test_timeAdd() {
		long date=new Date().getTime();
		System.out.println(date);
		System.out.println(DateFormatUtil.getFormatDate(date));
		//天 *时 *分 * 秒
		//1 * 1*30 * 60 * 1000 
		System.out.println(DateFormatUtil.add(DateFormatUtil.getDate4StrDate("09:12", "HH:mm"),
					"HH:mm",1*1*30*60 * 1000));
	}

	/**
	 * 解析加班班次数据
	 */
	public static void test07() {
		String result = "{\"ifDefaultClass\":false,\"wd_degree\":2,\"wd_earlytime\":null,\"comAddressdata\":[{\"CS_WORKADDR\":\"深圳市南山区\",\"CS_VALIDRANGE\":\"300\",\"CS_ID\":10141,\"CS_LATITUDE\":\"22.540676518856678\",\"CS_LONGITUDE\":\"113.9528745854545\",\"CS_SHORTNAME\":\"宇声数码技术公司\",\"CS_CODE\":\"2017020234\",\"CS_INNERDISTANCE\":500,\"success\":true},{\"CS_WORKADDR\":\"广东省深圳市南山区高新区科技南六路29号万德莱大厦南座6楼\",\"CS_VALIDRANGE\":\"300\",\"CS_ID\":10143,\"CS_LATITUDE\":\"22.5416028163184\",\"CS_LONGITUDE\":\"113.95309916183191\",\"CS_SHORTNAME\":\"深圳市中兴供应链有限公司\",\"CS_CODE\":\"2017020236\",\"CS_INNERDISTANCE\":500,\"success\":true}],\"count\":null,\"wd_code\":\"TEST1\",\"Class3\":{\"wd_offend\":null,\"wd_onduty\":null,\"wd_offduty\":null,\"wd_onbeg\":null},\"Class2\":{\"wd_offend\":\"20:00\",\"wd_onduty\":\"13:30\",\"wd_offduty\":\"18:00\",\"wd_onbeg\":\"13:00\"},\"wd_id\":111281,\"Class1\":{\"wd_offend\":\"12:30\",\"wd_onduty\":\"08:30\",\"wd_offduty\":\"12:00\",\"wd_onbeg\":\"07:00\"},\"wd_pcount\":null,\"wd_name\":\"测试1\",\"ifNeedSignCard\":true,\"innerdistance\":null,\"distance\":null,\"sessionId\":\"729F70FB568EF25CC7F1CEE14A0900EE\",\"comaddressset\":false,\"longitude\":null,\"latitude\":null,\"success\":true,\"wd_day\":null}";
		JSONObject root = JSON.parseObject(result);
		JSONObject Class1 = root.getJSONObject("Class1");
		JSONObject Class2 = root.getJSONObject("Class2");
		JSONObject Class3 = root.getJSONObject("Class3");
		List<String> mTimeData=new ArrayList<String>();
		int type = 0;// 默认没有班次
		String wd_onduty = Class1.getString("wd_onduty");// 上班时间
		if (wd_onduty != null){
			String wd_offduty=Class1.getString("wd_offduty");//结束时间
			type = 1;
			//获取时间累加，加半小时
			mTimeData.addAll(timeAddMuilt(wd_onduty,wd_offduty));
		}
		wd_onduty = Class2.getString("wd_onduty");// 上班时间
		if (wd_onduty != null){
			String wd_offduty=Class2.getString("wd_offduty");//结束时间
			type = 2;
			mTimeData.addAll(timeAddMuilt(wd_onduty,wd_offduty));
		}
		wd_onduty = Class3.getString("wd_onduty");// 上班时间
		if (wd_onduty != null){
			String wd_offduty=Class3.getString("wd_offduty");//结束时间
			type = 3;
			mTimeData.addAll(timeAddMuilt(wd_onduty,wd_offduty));
		}
		System.out.println(JSON.toJSON(mTimeData));
	}

	
	/**
	 * 返回指定时间段内  循环累加半小时
	 * @param start
	 * @param end
	 * 08:30-12:30  [首 +中间值累加半小时 + 尾]
	 * @return
	 */
	public static List<String> timeAddMuilt(String start,String end){
		List<String> result=new ArrayList<String>();
		result.add(start);
		int i=0;
		while(true){
			start=DateFormatUtil.add(DateFormatUtil.getDate4StrDate(start, "HH:mm"),
					"HH:mm",1*1*30*60 * 1000);
			if(start.compareTo(end)>0){
			    result.add(end);
				break;
			}else{
				result.add(start);
			}
			i++;
		}
		
		return result;
	}
	
	
	/**
	 * java运算精度问题
	 */
	public static void test06() {
		double i = 10.0;
		double j = 9.9;
		// System.out.println(i-j);

		// float i=10.0f;
		// float j=9.9f;
		System.out.println(sub(i, j));
	}

	/**
	 * double 相减
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double sub(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.subtract(bd2).doubleValue();
	}

	/**
	 * string 转 date
	 */
	public static void test05() {
		String date_string = "2016-09-12 0:0";
		// 利用java中的SimpleDateFormat类，指定日期格式，注意yyyy,MM大小写
		// 这里的日期格式要求javaAPI中有详细的描述，不清楚的话可以下载相关API查看
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		// SimpleDateFormat format=new SimpleDateFormat("yyyyMM");
		// 设置日期转化成功标识
		boolean dateflag = true;
		// 这里要捕获一下异常信息
		try {
			Date date = format.parse(date_string);
		} catch (ParseException e) {
			dateflag = false;
		} finally {
			// 成功：true ;失败:false
			System.out.println("日期是否满足要求" + dateflag);
		}
	}

	/**
	 * HashSet测试
	 */
	public static void test04() {
		HashSet<Integer> va = new HashSet<Integer>();
		Random rand = new Random(47);
		final List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			int j = rand.nextInt(30);
			System.out.println("j=" + j);
			// System.out.println("i="+i*2);
			if (!va.contains(j)) {
				va.add(j);
				list.add(j);
			}
		}
		Iterator<Integer> iter_detail = list.iterator();
		while (iter_detail.hasNext()) {
			System.out.println("r=" + iter_detail.next());
		}
		for (Integer integer : list) {
			System.out.println("for=" + integer);
		}
	}

	/**
	 * 理解数组的基本知识
	 */
	public static void test03() {
		ArrayList<String> data = new ArrayList<String>();
		data.add("1");
		data.add("2");
		System.out.println(data.size());
		System.out.println(data.get(data.size() - 1));
	}

	/**
	 * 生成65537方法数
	 */
	public static void test02() {
		try {
			FileWriter fw = new FileWriter("demo.txt");
			for (int i = 1; i <= 65537; i++) {
				fw.write("public void me" + i + "(){ }\r\n");
				fw.flush();
			}
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试正则表达式
	 */
	public static void test01() {
		String s = "1.";
		System.out.println(s.substring(s.length() - 1, s.length()).equals("."));
		System.out.println(countStr(s, "."));
		System.out.println(s.toString().length() == 2
				&& countStr(s.toString(), ".") == 1);
		System.out.println(!RegexUtil.checkRegex(s.toString(),
				"^[0-9]+(.[0-9]{1})?$") && false);
	}

	/**
	 * 判断str1中包含str2的个数
	 * 
	 * @param str1
	 * @param str2
	 * @return counter
	 */
	public static int countStr(String str1, String str2) {

		return countStrs(str1, str2);
	}

	/**
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static int countStrs(String str1, String str2) {
		if (str1.indexOf(str2) == -1) {
			return 0;
		} else if (str1.indexOf(str2) != -1) {
			counter++;
			countStr(str1.substring(str1.indexOf(str2) + str2.length()), str2);
			return counter;
		}
		return 0;
	}

	/**
	 * 替换字符串
	 */
	public static void str_replace() {
		String str = "申请流";
		System.out.println(str.replace("流程", ""));
	}

	/**
	 * 时间加法
	 */
	public static void timeAdd() {
		String millise = "604800000";// 7天 毫秒
		System.out.println(DateFormatUtil.add(new Date(),
				"yyyy-MM-dd HH:mm:ss", Long.valueOf(millise)));
	}

	/**
	 * 取代所有空格
	 */
	public static void str_replaceAll() {
		String str = "  你  好   你好  你好    ";
		System.out.println(str.replaceAll(" ", ""));
	}

	/**
	 * 数字格式化
	 */
	public static void test_DecimalFormat() {
		System.out.println(new DecimalFormat("###,###,##0").format(12.0));
	}

	/**
	 * 测试for循环
	 */
	public static void test_forCase() {
		ArrayList<Integer> mMoneys = new ArrayList<Integer>();
		mMoneys.add(1000);
		mMoneys.add(2000);
		mMoneys.add(3000);
		mMoneys.add(4000);
		mMoneys.add(67000);
		mMoneys.add(6000);
		mMoneys.add(34000);
		for (Integer i : mMoneys) {
			System.out.println(i);
		}
	}

}
