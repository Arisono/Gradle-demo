package com.gradle.java.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;


/**
 * @author Arison
 *
 */
public class SortUtils {
	static String json="{\"totalCount\":17,\"data\":[{\"JP_ID\":288134,\"JP_STATUS\":\"待审批\",\"JP_NODEID\":\"29330317\",\"JP_NAME\":\"拜访记录流程\",\"JP_NODENAME\":\"task 3\",\"JP_CODEVALUE\":\"2016110010\",\"TYPECODE\":\"process\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"CHENAP\",\"JP_LAUNCHTIME\":1478077501000,\"JP_PROCESSNOTE\":\"null\",\"JP_LAUNCHERNAME\":\"陈爱平\",\"RN\":7},{\"JP_ID\":281480,\"JP_STATUS\":\"待审批\",\"JP_NODEID\":\"24290319\",\"JP_NAME\":\"拜访记录流程\",\"JP_NODENAME\":\"task 3\",\"JP_CODEVALUE\":\"2016090321\",\"TYPECODE\":\"process\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"A033\",\"JP_LAUNCHTIME\":1473666795000,\"JP_PROCESSNOTE\":\"null\",\"JP_LAUNCHERNAME\":\"国胜\",\"RN\":2},{\"JP_ID\":281728,\"JP_STATUS\":\"待审批\",\"JP_NODEID\":\"24440337\",\"JP_NAME\":\"拜访记录流程\",\"JP_NODENAME\":\"task 3\",\"JP_CODEVALUE\":\"2016090365\",\"TYPECODE\":\"process\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"A033\",\"JP_LAUNCHTIME\":1474188362000,\"JP_PROCESSNOTE\":\"null\",\"JP_LAUNCHERNAME\":\"国胜\",\"RN\":3},{\"JP_ID\":283343,\"JP_STATUS\":\"待审批\",\"JP_NODEID\":\"24440162\",\"JP_NAME\":\"拜访记录流程\",\"JP_NODENAME\":\"task 3\",\"JP_CODEVALUE\":\"2016090290\",\"TYPECODE\":\"process\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"U0730\",\"JP_LAUNCHTIME\":1474179999000,\"JP_PROCESSNOTE\":\"null\",\"JP_LAUNCHERNAME\":\"卢浩光\",\"RN\":4},{\"JP_ID\":285401,\"JP_STATUS\":\"待审批\",\"JP_NODEID\":\"25640720\",\"JP_NAME\":\"拜访记录流程\",\"JP_NODENAME\":\"task 3\",\"JP_CODEVALUE\":\"2016090598\",\"TYPECODE\":\"process\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"U0764\",\"JP_LAUNCHTIME\":1475034230000,\"JP_PROCESSNOTE\":\"null\",\"JP_LAUNCHERNAME\":\"曾起飞\",\"RN\":5},{\"JP_ID\":286579,\"JP_STATUS\":\"待审批\",\"JP_NODEID\":\"26570507\",\"JP_NAME\":\"拜访记录流程\",\"JP_NODENAME\":\"task 3\",\"JP_CODEVALUE\":\"2016100088\",\"TYPECODE\":\"process\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"U0764\",\"JP_LAUNCHTIME\":1476156521000,\"JP_PROCESSNOTE\":\"null\",\"JP_LAUNCHERNAME\":\"曾起飞\",\"RN\":6},{\"JP_ID\":950703,\"JP_STATUS\":\"待审批\",\"JP_NODEID\":\"26240023\",\"JP_NAME\":\"请假申请流程\",\"JP_NODENAME\":\"task 2\",\"JP_CODEVALUE\":\"AL16100004\",\"TYPECODE\":\"procand\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"CHENAP\",\"JP_LAUNCHTIME\":1475971851000,\"JP_PROCESSNOTE\":\"请假原因: 事假\\n\",\"JP_LAUNCHERNAME\":\"陈爱平\",\"RN\":9},{\"JP_ID\":943739,\"JP_STATUS\":\"待审批\",\"JP_NODEID\":\"24440107\",\"JP_NAME\":\"拜访记录流程\",\"JP_NODENAME\":\"task 3\",\"JP_CODEVALUE\":\"2016090278\",\"TYPECODE\":\"procand\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"CHENAP\",\"JP_LAUNCHTIME\":1474179802000,\"JP_PROCESSNOTE\":null,\"JP_LAUNCHERNAME\":\"陈爱平\",\"RN\":8},{\"JP_ID\":955457,\"JP_STATUS\":\"待审批\",\"JP_NODEID\":\"28700394\",\"JP_NAME\":\"拜访记录流程\",\"JP_NODENAME\":\"task 3\",\"JP_CODEVALUE\":\"2016100279\",\"TYPECODE\":\"procand\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"DENGGC\",\"JP_LAUNCHTIME\":1477554245000,\"JP_PROCESSNOTE\":null,\"JP_LAUNCHERNAME\":\"邓国超\",\"RN\":17},{\"JP_ID\":951085,\"JP_STATUS\":\"待审批\",\"JP_NODEID\":\"26311001\",\"JP_NAME\":\"请假申请流程\",\"JP_NODENAME\":\"task 2\",\"JP_CODEVALUE\":\"AL16100006\",\"TYPECODE\":\"procand\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"DENGGC\",\"JP_LAUNCHTIME\":1476001081000,\"JP_PROCESSNOTE\":\"请假原因: 里咯空空空\\n\",\"JP_LAUNCHERNAME\":\"邓国超\",\"RN\":10},{\"JP_ID\":951534,\"JP_STATUS\":\"待审批\",\"JP_ NAME\":\"拜访记录流程\",\"JP_NODENAME\":\"task 4\",\"JP_CODEVALUE\":\"2016100087\",\"TYPECODE\":\"procand\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"U0764\",\"JP_LAUNCHTIME\":1476239523000,\"JP_PROCESSNOTE\":null,\"JP_LAUNCHERNAME\":\"曾起飞\",\"RN\":11},{\"JP_ID\":951696,\"JP_STATUS\":\"待审批\",\"JP_NODEID\":\"26860066\",\"JP_NAME\":\"请假申请流程\",\"JP_NODENAME\":\"task 2\",\"JP_CODEVALUE\":\"AL16100009\",\"TYPECODE\":\"procand\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"A021\",\"JP_LAUNCHTIME\":1476274616000,\"JP_PROCESSNOTE\":\"请假原因: 测试。不想上班啦\\n\",\"JP_LAUNCHERNAME\":\"陈虎\",\"RN\":12},{\"JP_ID\":952470,\"JP_STATUS\":\"待审批\",\"JP_NODEID\":\"27030701\",\"JP_NAME\":\"请假申请流程\",\"JP_NODENAME\":\"task 2\",\"JP_CODEVALUE\":\"AL16100011\",\"TYPECODE\":\"procand\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"A033\",\"JP_LAUNCHTIME\":1476431901000,\"JP_PROCESSNOTE\":\"请假原因: 12224\\n\",\"JP_LAUNCHERNAME\":\"国胜\",\"RN\":13},{\"JP_ID\":952972,\"JP_STATUS\":\"待审批\",\"JP_NODEID\":\"27340120\",\"JP_NAME\":\"请假申请流程\",\"JP_NODENAME\":\"task 2\",\"JP_CODEVALUE\":\"AL16100016\",\"TYPECODE\":\"procand\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"CHENAP\",\"JP_LAUNCHTIME\":1476771279000,\"JP_PROCESSNOTE\":\"请假原因: 测试\\n\",\"JP_LAUNCHERNAME\":\"陈爱平\",\"RN\":14},{\"JP_ID\":955299,\"JP_STATUS\":\"待审批\",\"JP_NODEID\":\"28640102\",\"JP_NAME\":\"拜访记录流程\",\"JP_NODENAME\":\"task 3\",\"JP_CODEVALUE\":\"2016100260\",\"TYPECODE\":\"procand\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"CHENAP\",\"JP_LAUNCHTIME\":1477530168000,\"JP_PROCESSNOTE\":null,\"JP_LAUNCHERNAME\":\"陈爱平\",\"RN\":15},{\"JP_ID\":955424,\"JP_STATUS\":\"待审批\",\"JP_NODEID\":\"28700181\",\"JP_NAME\":\"请假申请流程\",\"JP_NODENAME\":\"task 2\",\"JP_CODEVALUE\":\"AL16100030\",\"TYPECODE\":\"procand\",\"JP_NODEDEALMAN\":\"L00010102002\",\"JP_LAUNCHERID\":\"U0730\",\"JP_LAUNCHTIME\":1477551524000,\"JP_PROCESSNOTE\":\"请假原因: test\\n\",\"JP_LAUNCHERNAME\":\"卢浩光\",\"RN\":16},{\"JP_ID\":281475,\"JP_STATUS\":\"未通过\",\"JP_NODEID\":\"24200905\",\"JP_NAME\":\"商机动态流程\",\"JP_NODENAME\":\"task 2\",\"JP_CODEVALUE\":\"2016090034\",\"TYPECODE\":\"unprocess\",\"JP_NODEDEALMAN\":\"TEST002\",\"JP_LAUNCHERID\":\"L00010102002\",\"JP_LAUNCHTIME\":1473305813000,\"JP_PROCESSNOTE\":\"[周西]不同意 备注:\\uD83D\\uDE02\",\"JP_LAUNCHERNAME\":\"刘杰\",\"RN\":1}],\"success\":true}";
	public static void main(String[] args) {
		JSONArray jsonArray=JSON.parseObject(json).getJSONArray("data");
		System.out.println("排序前："+jsonArray.toJSONString());
		
		
		sortJsonArray(jsonArray);
		//sortJSONArray(jsonArray, "JP_LAUNCHTIME", "ASC");
		//sortList(jsonArray, "JP_LAUNCHTIME", "ASC");
	
	}
	
	
	
	/**
	 * 解析JSONArray中JSONObject具体字段
	 * @param jsonArr
	 * @param sortField
	 * @param sortMode
	 * @return
	 */
	public static JSONArray sortJSONArray(JSONArray jsonArr,final String sortField, final String sortMode){
		  List<JSONObject> jsonValues = new ArrayList<JSONObject>();
		    for (int i = 0; i < jsonArr.size(); i++) {
		        jsonValues.add(jsonArr.getJSONObject(i));
		    }
		    sortList(jsonValues,sortField,sortMode);		 
		    jsonArr.clear();
		    for (int i = 0; i < jsonValues.size(); i++) {
		        jsonArr.add(i,jsonValues.get(i));
		    }
		    System.out.println("排序后："+jsonArr.toJSONString());
		    return jsonArr;
	}
	
	
	/**
	 *  JSONArray排序
	 */
	public static JSONArray sortJsonArray(JSONArray jsonArr){
		   List<JSONObject> jsonValues = new ArrayList<JSONObject>();
		    for (int i = 0; i < jsonArr.size(); i++) {
		        jsonValues.add(jsonArr.getJSONObject(i));
		    }
		    Collections.sort( jsonValues, new Comparator<JSONObject>() {
		        //You can change "Name" with "ID" if you want to sort by ID
		        private static final String KEY_NAME = "JP_LAUNCHTIME";

		        @Override
		        public int compare(JSONObject a, JSONObject b) {
		        	Long valA = null;
		        	Long valB = null;
		            try {
		                valA = (Long) a.get(KEY_NAME);
		                valB = (Long) b.get(KEY_NAME);
		            } 
		            catch (JSONException e) {
		                //do something
		            }
                     int result= valA.compareTo(valB);
                     
		            return -result;
		        }
		    });

		    
		    jsonArr.clear();
		    for (int i = 0; i < jsonValues.size(); i++) {
		        jsonArr.add(i,jsonValues.get(i));
		        
		    }
		    System.out.println("排序后："+jsonArr.toJSONString());
		    return jsonArr;
	}

	
	/** 
	 * 对List对象按照某个成员变量进行排序 
	 * @param list       List对象 
	 * @param sortField  排序的属性名称 
	 * @param sortMode   排序方式：ASC，DESC 任选其一 
	 */  
	public static <T> void sortList(List<T> list, final String sortField, final String sortMode) {  
	    if(list == null || list.size() < 2) {  
	        return;  
	    }  
	    Collections.sort(list, new Comparator<T>() {  
	        @Override  
	        public int compare(T o1, T o2) {  
	            try {  
	                @SuppressWarnings("rawtypes")
					Class clazz = o1.getClass();  
	                Field field = clazz.getDeclaredField(sortField); //获取成员变量  
	                field.setAccessible(true); //设置成可访问状态  
	                String typeName = field.getType().getName().toLowerCase(); //转换成小写  
	  
	                Object v1 = field.get(o1); //获取field的值  
	                Object v2 = field.get(o2); //获取field的值  
	  
	                boolean ASC_order = (sortMode == null || "ASC".equalsIgnoreCase(sortMode));  
	  
	                //判断字段数据类型，并比较大小  
	                if(typeName.endsWith("string")) {  
	                    String value1 = v1.toString();  
	                    String value2 = v2.toString();  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("short")) {  
	                    Short value1 = Short.parseShort(v1.toString());  
	                    Short value2 = Short.parseShort(v2.toString());  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("byte")) {  
	                    Byte value1 = Byte.parseByte(v1.toString());  
	                    Byte value2 = Byte.parseByte(v2.toString());  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("char")) {  
	                    Integer value1 = (int)(v1.toString().charAt(0));  
	                    Integer value2 = (int)(v2.toString().charAt(0));  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("int") || typeName.endsWith("integer")) {  
	                    Integer value1 = Integer.parseInt(v1.toString());  
	                    Integer value2 = Integer.parseInt(v2.toString());  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("long")) {  
	                    Long value1 = Long.parseLong(v1.toString());  
	                    Long value2 = Long.parseLong(v2.toString());  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("float")) {  
	                    Float value1 = Float.parseFloat(v1.toString());  
	                    Float value2 = Float.parseFloat(v2.toString());  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("double")) {  
	                    Double value1 = Double.parseDouble(v1.toString());  
	                    Double value2 = Double.parseDouble(v2.toString());  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("boolean")) {  
	                    Boolean value1 = Boolean.parseBoolean(v1.toString());  
	                    Boolean value2 = Boolean.parseBoolean(v2.toString());  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("date")) {  
	                    Date value1 = (Date)(v1);  
	                    Date value2 = (Date)(v2);  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("timestamp")) {  
	                    Timestamp value1 = (Timestamp)(v1);  
	                    Timestamp value2 = (Timestamp)(v2);  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else {  
	                    //调用对象的compareTo()方法比较大小  
	                    Method method = field.getType().getDeclaredMethod("compareTo", new Class[]{field.getType()});  
	                    method.setAccessible(true); //设置可访问权限  
	                    int result  = (Integer)method.invoke(v1, new Object[]{v2});  
	                    return ASC_order ? result : result*(-1);  
	                }  
	            }  
	            catch (Exception e) {  
	                String err = e.getLocalizedMessage();  
	                System.out.println(err);  
	                e.printStackTrace();  
	            }  
	  
	            return 0; //未知类型，无法比较大小  
	        }  
	    });  
	}  
}
