package com.gradle.design.config;

/**
 * Created by Arison on 2017/3/2.
 */
public class ApiPlatform extends ApiBase implements ApiModel{

    
    private static ApiPlatform instance;
   
    public static String mBaseUrl_test = "http://192.168.253.192:8088/platform-b2b/";
    public static String mBaseUrl_developer = "http://uas.ubtob.com/";
    public static String mBaseUrl_devtest= "http://218.17.158.219:9090/platform-b2b/"; 
    private  String mBaseUrl = mBaseUrl_developer;
    
    
   
    public ApiPlatform() {
		super();
		initApi(mBaseUrl);
	}


	private void initApi(String mBaseUrl) {
		super.mBaseUrl=mBaseUrl;
		 //考勤单据列表
        setList_vacation(mBaseUrl+"mobile/vacation/getAllVacation");
        setList_workOvertime(mBaseUrl+"mobile/workOvertime/getWorkOvertime");
        setList_feePlease(mBaseUrl+"mobile/feePlease/getFeePlease");
        //考勤单据保存
        setSave_vacation(mBaseUrl+"mobile/vacation/saveVacation");
        setSave_feePlease(mBaseUrl+"mobile/feePlease/saveFeePlease");
        setSave_workOvertime(mBaseUrl+"");
        //日报
        setDaily_work_url(mBaseUrl+"mobile/workDaily/getWorkDaily");
        //审批流
        setGetAuditTodo(mBaseUrl+"mobile/approvalflow/getAuditTodo");
        super.getAuditDone=mBaseUrl+"mobile/approvalflow/getAuditDone";
        //通讯录人员列表
        super.getUsersInfo=mBaseUrl+"mobile/approvalflow/getUsersInfo";
        
        //任务
        super.task_save=mBaseUrl+"mobile/mobiletask/saveTask";
        super.task_list=mBaseUrl+"mobile/mobiletask/getAllTasks";
        super.task_reply=mBaseUrl+"mobile/mobiletask/getTaskReply";
        super.task_change=mBaseUrl+"mobile/mobiletask/changeTaskStaus";
        
        
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


     public void setmBaseUrl(String mBaseUrl) {
		this.mBaseUrl = mBaseUrl;
		initApi(mBaseUrl);
	 }

   
	

	
	
	

}
