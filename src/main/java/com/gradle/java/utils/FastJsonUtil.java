package com.gradle.java.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * @author Arison
 * 怎样解析JSON数据？
 */
public class FastJsonUtil {

	public static void main(String[] args) {
		parseObject();
	}
	
	
	/**
	 * @desc:fastjson解析json数据
	 */
	public static void parseObject(){
		String json="{\"number\":12.12,\"string\":\"这是对象里面的字符串\",\"desc\":\"这是根对象属性描述\",\"empty\":null,\"object\":{\"object_desc\":\"这是嵌套对象里面的描述属性\",\"object_number\":1},\"array\":[999,\"这是嵌套在对象里面的数组里面的字符串\",{\"array_object_num\":1,\"array_object_str\":\"这是字符串1\",\"array_object_desc\":\"在数组里面的对象,该对象没有key\"},[1,\"这是嵌套数组里面的字符串\"],null]}";
	    //解析对象
		System.out.println(JSON.parseObject(json));//可以解析内部元素
		System.out.println(JSON.parse(json));//不能解析内部元素(以下不考虑这种情况)
		
		JSONObject rootObject=JSON.parseObject(json);//解析对象---JSONObject
		
		System.out.println(rootObject.getString("string"));//获取对象-字符串   
		System.out.println(rootObject.getIntValue("number"));//获取对象-int(损精度)
		System.out.println(rootObject.getFloatValue("number"));//获取对象-float(不损精度)
		
		System.out.println(rootObject.getString("empty"));//取空值的情况，存在的key-为空
		System.out.println(rootObject.getString("empty1"));//取空值的情况,不存在的key-为空
		
		//解析对象-数组  object-array
		System.out.println(rootObject.getJSONArray("array").getIntValue(0));
		System.out.println(rootObject.getJSONArray("array").get(0));//第一个value 
		System.out.println(rootObject.getJSONArray("array").get(1));
		System.out.println(rootObject.getJSONArray("array").get(2));//嵌套对象
		System.out.println(rootObject.getJSONArray("array").get(3));//嵌套数组
		System.out.println(rootObject.getJSONArray("array").get(4));//数组里面的空值
//		System.out.println(rootObject.getJSONArray("array").get(5));//数组越界  报错
//		System.out.println(rootObject.getJSONArray("array").get(6));
		
		System.out.println(rootObject.getJSONArray("array").get(3) instanceof Array);//false
		System.out.println(rootObject.getJSONArray("array").get(3) instanceof List);//true
		System.out.println(rootObject.getJSONArray("array").get(3) instanceof ArrayList);//false
		System.out.println(rootObject.getJSONArray("array").get(3) instanceof JSONArray);//true
		
		System.out.println(rootObject.getJSONArray("array").get(2) instanceof Object);//true
		System.out.println(rootObject.getJSONArray("array").getJSONObject(2) instanceof Object);//true
		System.out.println(rootObject.getJSONArray("array").getJSONObject(2) instanceof JSONObject);//true
		System.out.println(rootObject.getJSONArray("array").get(2) instanceof JSONObject);//true
		
		
	    //解析对象  object-array-array-object
		System.out.println(rootObject.getJSONArray("array").getJSONArray(3).getIntValue(0));
		System.out.println(rootObject.getJSONArray("array").getJSONArray(3).getString(0));
		
		//解析对象-对象   object-object {"object_desc":"这是嵌套对象里面的描述属性","object_number":1}
		System.out.println(rootObject.getJSONObject("object"));
		System.out.println(rootObject.getJSONObject("object").get("object_desc"));
		System.out.println(rootObject.getJSONObject("object").getString("object_desc"));
		System.out.println(rootObject.getJSONObject("object").get("object_number"));
		System.out.println(rootObject.getJSONObject("object").getString("object_number"));
	}
	
	
	
	/** 
	 * @author Arison
	 *  JSON 解析类型问题
	 */
	public static void testIntValue(){
		String json="{\"sex\":1,\"sex1\":\"1\",\"sexs\":[\"1\",1,\"liujie\",{\"ages\":\"123\",\"age\":124}],\"key\":null}";
		String json2="{\"datas\":{\"sales\":[[1.79,\"姬冰雁\",\"A1232\",1]],\"visits\":[],\"receivables\":[[3.92,\"陈虎\",\"A021\",1]],\"profits\":[[1.73,\"姬冰雁\",\"JIBY\",1]]},\"success\":true}";
		String json3="[1,12,\"sex\",{\"1\":1,\"str\":\"123\"}]";
		
		System.out.println("解析数据1:"+json);
		System.out.println(JSON.parseObject(json).getString("sex"));
		System.out.println(JSON.parseObject(json).getIntValue("sex1"));
		System.out.println(JSON.parseObject(json).getJSONArray("sexs").getIntValue(0));
		System.out.println(JSON.parseObject(json).getJSONArray("sexs").getString(0));
		System.out.println(JSON.parseObject(json).getJSONArray("sexs").getString(0));
		
		System.out.println(JSON.parseObject(json2).getJSONObject("datas")
				.getJSONArray("sales").getJSONArray(0).getIntValue(2));//报错
		System.out.println(JSON.parseObject(json2).getJSONObject("datas")
				.getJSONArray("sales").getJSONArray(0).get(0).toString());
		
		System.out.println(JSON.parseArray(json3).getIntValue(0));
		System.out.println(JSON.parseArray(json3).getJSONObject(3).getIntValue("str"));
		
	}
}
