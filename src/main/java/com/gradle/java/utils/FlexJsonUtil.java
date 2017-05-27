package com.gradle.java.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;


public class FlexJsonUtil {

	public static <T> T fromJson(String json, Class<?> cls) {
		return new JSONDeserializer<T>().use(null, cls).deserialize(json);
	}

	public static <K, V> Map<K, V> fromJson(String json) {
		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		return new JSONDeserializer<Map<K, V>>().use(null, map.getClass())
				.deserialize(json);
	}

	public static <K, V> HashMap<K, V> fromHJson(String json) {
		HashMap<K, V> map = new HashMap<K, V>();
		return new JSONDeserializer<HashMap<K, V>>().use(null, map.getClass())
				.deserialize(json);
	}

	/** @注释：扩充Map */
	public static <K, V> LinkedHashMap<K, V> fromJsonLink(String json) {
		LinkedHashMap<K, V> map = new LinkedHashMap<K, V>();
		return new JSONDeserializer<LinkedHashMap<K, V>>().use(null,
				map.getClass()).deserialize(json);
	}

	/**@注释：扩充Map 不支持该类型 */
	public static <K, V> ConcurrentHashMap<K, V> fromJsonConcurrent(String json) {
		ConcurrentHashMap<K, V> map = new ConcurrentHashMap<K, V>();
		return new JSONDeserializer<ConcurrentHashMap<K, V>>().use(null, map.getClass()).deserialize(json);
	}

	
	public String toJson() {
		return new JSONSerializer().exclude("*.class").serialize(this);
	}

	public static String toJson(Object obj) {
		return new JSONSerializer().exclude("*.class").serialize(obj);
	}

	public static <T> String toJsonArray(Collection<?> collection) {
		return new JSONSerializer().exclude("*.class").serialize(collection);
	}

	public static <T> List<T> fromJsonArray(String json, Class<?> cls) {
		return new JSONDeserializer<List<T>>().use(null, ArrayList.class)
				.use("values", cls).deserialize(json);
	}
	
	public static void main(String[] args) {
	  String text="{\"keyField\":\"va_id\",\"pfField\":null,\"sessionId\":\"4C0DD969B6339EAC0E92D7238F6B21C4\",\"columns\":[{\"dataIndex\":\"va_status\",\"caption\":\"单据状态\",\"width\":80,\"type\":null,\"format\":null,\"render\":null},{\"dataIndex\":\"va_id\",\"caption\":\"id\",\"width\":0,\"type\":\"numberfield\",\"format\":\"0,000\",\"render\":null},{\"dataIndex\":\"va_vacationtype\",\"caption\":\"请假类型\",\"width\":80,\"type\":null,\"format\":null,\"render\":null},{\"dataIndex\":\"va_startime\",\"caption\":\"开始时间\",\"width\":200,\"type\":\"datetimefield\",\"format\":\"Y-m-d H:i:s\",\"render\":null},{\"dataIndex\":\"va_endtime\",\"caption\":\"结束时间\",\"width\":165,\"type\":\"datetimefield\",\"format\":\"Y-m-d H:i:s\",\"render\":null},{\"dataIndex\":\"va_remark\",\"caption\":\"请假原因\",\"width\":200,\"type\":null,\"format\":null,\"render\":null}],\"listdata\":[{\"va_startime\":\"2017-02-14 09:00:00\",\"va_id\":3600607,\"va_status\":\"已审核\",\"va_remark\":\"减肥计划发\",\"va_endtime\":\"2017-02-16 13:30:00\",\"va_vacationtype\":\"事假\"},{\"va_startime\":\"2016-11-28 13:39:00\",\"va_id\":3600573,\"va_status\":\"已提交\",\"va_remark\":\"测试\",\"va_endtime\":\"2016-12-28 13:39:00\",\"va_vacationtype\":\"事假\"},{\"va_startime\":\"2016-11-28 13:39:00\",\"va_id\":3600572,\"va_status\":\"已提交\",\"va_remark\":\"测试\",\"va_endtime\":\"2016-12-28 13:39:00\",\"va_vacationtype\":\"事假\"},{\"va_startime\":\"2016-11-28 13:39:00\",\"va_id\":3600571,\"va_status\":\"已提交\",\"va_remark\":\"测试\",\"va_endtime\":\"2016-12-28 13:39:00\",\"va_vacationtype\":\"事假\"},{\"va_startime\":\"2016-11-28 13:39:00\",\"va_id\":3600570,\"va_status\":\"已提交\",\"va_remark\":\"测试\",\"va_endtime\":\"2016-12-28 13:39:00\",\"va_vacationtype\":\"事假\"},{\"va_startime\":\"2016-11-28 13:39:00\",\"va_id\":3600569,\"va_status\":\"已提交\",\"va_remark\":\"测试\",\"va_endtime\":\"2016-12-28 13:39:00\",\"va_vacationtype\":\"事假\"},{\"va_startime\":\"2016-11-28 13:39:00\",\"va_id\":3600507,\"va_status\":\"已提交\",\"va_remark\":\"测试\",\"va_endtime\":\"2016-12-28 13:39:00\",\"va_vacationtype\":\"事假\"},{\"va_startime\":\"2017-01-22 08:30:00\",\"va_id\":3599928,\"va_status\":\"在录入\",\"va_remark\":\"好成绩发挥好\",\"va_endtime\":\"2017-01-24 18:00:00\",\"va_vacationtype\":\"病假\"},{\"va_startime\":\"2017-01-21 08:30:00\",\"va_id\":3599887,\"va_status\":\"已审核\",\"va_remark\":\"号放假符合\",\"va_endtime\":\"2017-01-21 18:00:00\",\"va_vacationtype\":\"病假\"},{\"va_startime\":\"2017-01-21 08:30:00\",\"va_id\":3599886,\"va_status\":\"在录入\",\"va_remark\":\"借此机会发\",\"va_endtime\":\"2017-01-21 18:00:00\",\"va_vacationtype\":\"婚假\"}]}";
//	  Map<String, Object> cMap=	FlexJsonUtil.fromJsonConcurrent(text);
//	  System.out.println(JSON.toJSONString(cMap));
	  @SuppressWarnings("unchecked")
	  Map<String, Object> cMap=JSON.parseObject(text, HashMap.class);
	  System.out.println(cMap.toString());
	}
}
