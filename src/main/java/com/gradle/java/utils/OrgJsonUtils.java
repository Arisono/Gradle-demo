package com.gradle.java.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.gradle.model.Users;

/**
 * @author Arison
 * 使用org json 来解析数据
 */
public class OrgJsonUtils {

	public static void main(String[] args) {
		jsonObjectTest();
		jsonArrayTest();
		jsonObjectAndArrayTest();
		jsonObjectTestUtils();
	}
	
	public static void jsonObjectTestUtils(){
		String array="[{\"bi_inqty\":\"0.0\",\"bi_prodid\":\"0\",\"bi_piid\":\"50707678\",\"bi_location\":\"\",\"bi_inoutno\":\"MG161200001\",\"bi_prodcode\":\"\",\"bi_whcode\":\"CK005\",\"bi_barcode\":\"7690867143120700426\"},{\"bi_inqty\":\"0.0\",\"bi_prodid\":\"0\",\"bi_piid\":\"50707678\",\"bi_location\":\"\",\"bi_inoutno\":\"MG161200001\",\"bi_prodcode\":\"\",\"bi_whcode\":\"CK005\",\"bi_barcode\":\"7690867143120700429\"},{\"bi_inqty\":\"0.0\",\"bi_prodid\":\"0\",\"bi_piid\":\"50707678\",\"bi_location\":\"\",\"bi_inoutno\":\"MG161200001\",\"bi_prodcode\":\"\",\"bi_whcode\":\"CK005\",\"bi_barcode\":\"7690867143120700430\"}]";
		JSONArray jarray=new JSONArray(array);
		JSONObject object=jarray.getJSONObject(0);
		System.out.println(object.getString("bi_prodid"));
		
		JSONObject me=new JSONObject();
		
		me.put("array", jarray);
		me.put("info", "程序错误");
		
		
		 ArrayList<Users> users=new ArrayList<Users>();
		 for (int i = 0; i < 10; i++) {
			Users model=new Users();
			model.setAge(1);
			model.setName("名字"+i);
			users.add(model);
		}
		 
		String jsonStr= JSON.toJSONString(users);
		me.put("array1", new JSONArray(jsonStr));
		System.out.println(me.toString());
		
	}
	
	public static void jsonObjectTest() { 
		JSONObject jsonobj = new JSONObject("{\"name\":\"xiazdong\",\"age\":20}"); 
		    String name = jsonobj.getString("name"); 
		int age = jsonobj.getInt("age"); 
		    System.out.println("name = " + name + ",age = " + age); 
		} 
	
	
	public static void jsonArrayTest() { 
		JSONArray jsonarray = new JSONArray("[{'name':'xiazdong','age':20},{'name':'xzdong','age':15}]"); 
		for (int i = 0; i < jsonarray.length(); i++) { 
		JSONObject jsonobj = jsonarray.getJSONObject(i); 
		        String name = jsonobj.getString("name"); 
		        int age = jsonobj.getInt("age"); 
		        System.out.println("name = " + name + ",age = " + age); 
		    } 
		} 
	
	
	public static void jsonObjectAndArrayTest() { 
		String jsonstring = "{'name':'xiazdong','age':20,'book':['book1','book2']}"; 
		JSONObject jsonobj = new JSONObject(jsonstring); 
		 
		    String name = jsonobj.getString("name"); 
		System.out.println("name" + ":" + name); 
		 
		    int age = jsonobj.getInt("age"); 
		    System.out.println("age" + ":" + age); 
		 
		JSONArray jsonarray = jsonobj.getJSONArray("book"); 
		for (int i = 0; i < jsonarray.length(); i++) { 
		        String book = jsonarray.getString(i); 
		System.out.println("book" + i + ":" + book); 
		    }  
		}  

}
