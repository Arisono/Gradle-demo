package com.gradle.java.singleton;

/**
 * Created by Arison on 2017/3/2.
 */
@SuppressWarnings("unused")
public class ApiPlatform extends ApiBase implements ApiModel{

    
    private static ApiPlatform instance;
   
    private final String mBaseUrl_test = "http://192.168.253.192:8088/platform-b2b/";
    private final String mBaseUrl_developer = "http://218.17.158.219:9090/platform-b2b/";
    private final String mBaseUrl = mBaseUrl_test;
    
    
   
    public ApiPlatform() {
		super();
		 //考勤单据列表
        setList_vacation(mBaseUrl+"mobile/vacation/getAllVacation");
        setList_workOvertime(mBaseUrl+"mobile/workOvertime/getWorkOvertime");
        setList_feePlease(mBaseUrl+"mobile/feePlease/getFeePlease");
        
        setSave_vacation(mBaseUrl+"");
        setSave_feePlease(mBaseUrl+"mobile/feePlease/saveFeePlease");
        setSave_workOvertime(mBaseUrl+"");
	}


	public static ApiPlatform getInstance(){
    	if (instance==null) {
			synchronized (ApiPlatform.class) {
				if (instance==null) {
					instance=new ApiPlatform();
				}
			}
		}
    	
    	return instance;
    }

}
