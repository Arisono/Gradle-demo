package com.gradle.design.config;

/**
 * Created by Arison on 2017/3/2.
 */
public abstract class ApiBase {
	
	public  String mBaseUrl;
    //定义各种公共接口
    public String login;//登录
    public String getMasters;//获取账套
    
    
    public String getUsersInfo;//人员信息
    //审批流接口
    public String getAuditTodo;//待审批
    public String getAuditDone;//已审批
    
    //任务
    public String task_save;
    public String task_list;
    public String task_reply;
    public String task_change;
    
    
    //消息
    public String obtain_announce_url;//公告中心：通过企业uu获取所有公告
    public String news_center_url;//新闻中心
    public String notification_center_url;//通知中心
    public String user_info_query_url;//用户信息查询：（管理员/非管理员）
    
    //uas 考勤单据
    public String travel_request_url;//出差申请
    public String leave_application_url;//请假单
    public String work_overtime_url;//加班申请
    
    //  考勤单据列表接口
    public String list_vacation;//请假单列表
    public String list_workOvertime;//加班单列表
    public String list_feePlease;//出差单列表

    public String save_vacation;//请假单列表
    public String save_workOvertime;//加班单列表
    public String save_feePlease;//出差单列表
    
    //考勤班次
    public String punch_address_url;//打卡地址
    public String punch_schedule_url;//打卡班次
    public String punch_record_url;//员工打卡记录
    
   
    //工作日报
    public String daily_work_url;//工作日报
    public String work_schedule_url;//员工工作时间
    public String work_order_url;//工作日程
    
    
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getGetMasters() {
		return getMasters;
	}
	public void setGetMasters(String getMasters) {
		this.getMasters = getMasters;
	}
	public String getObtain_announce_url() {
		return obtain_announce_url;
	}
	public void setObtain_announce_url(String obtain_announce_url) {
		this.obtain_announce_url = obtain_announce_url;
	}
	public String getNews_center_url() {
		return news_center_url;
	}
	public void setNews_center_url(String news_center_url) {
		this.news_center_url = news_center_url;
	}
	public String getNotification_center_url() {
		return notification_center_url;
	}
	public void setNotification_center_url(String notification_center_url) {
		this.notification_center_url = notification_center_url;
	}
	public String getUser_info_query_url() {
		return user_info_query_url;
	}
	public void setUser_info_query_url(String user_info_query_url) {
		this.user_info_query_url = user_info_query_url;
	}
	public String getTravel_request_url() {
		return travel_request_url;
	}
	public void setTravel_request_url(String travel_request_url) {
		this.travel_request_url = travel_request_url;
	}
	public String getLeave_application_url() {
		return leave_application_url;
	}
	public void setLeave_application_url(String leave_application_url) {
		this.leave_application_url = leave_application_url;
	}
	public String getWork_overtime_url() {
		return work_overtime_url;
	}
	public void setWork_overtime_url(String work_overtime_url) {
		this.work_overtime_url = work_overtime_url;
	}
	public String getList_vacation() {
		return list_vacation;
	}
	public void setList_vacation(String list_vacation) {
		this.list_vacation = list_vacation;
	}
	public String getList_workOvertime() {
		return list_workOvertime;
	}
	public void setList_workOvertime(String list_workOvertime) {
		this.list_workOvertime = list_workOvertime;
	}
	public String getList_feePlease() {
		return list_feePlease;
	}
	public void setList_feePlease(String list_feePlease) {
		this.list_feePlease = list_feePlease;
	}
	public String getSave_vacation() {
		return save_vacation;
	}
	public void setSave_vacation(String save_vacation) {
		this.save_vacation = save_vacation;
	}
	public String getSave_workOvertime() {
		return save_workOvertime;
	}
	public void setSave_workOvertime(String save_workOvertime) {
		this.save_workOvertime = save_workOvertime;
	}
	public String getSave_feePlease() {
		return save_feePlease;
	}
	public void setSave_feePlease(String save_feePlease) {
		this.save_feePlease = save_feePlease;
	}
	public String getPunch_address_url() {
		return punch_address_url;
	}
	public void setPunch_address_url(String punch_address_url) {
		this.punch_address_url = punch_address_url;
	}
	public String getPunch_schedule_url() {
		return punch_schedule_url;
	}
	public void setPunch_schedule_url(String punch_schedule_url) {
		this.punch_schedule_url = punch_schedule_url;
	}
	public String getPunch_record_url() {
		return punch_record_url;
	}
	public void setPunch_record_url(String punch_record_url) {
		this.punch_record_url = punch_record_url;
	}
	public String getDaily_work_url() {
		return daily_work_url;
	}
	public void setDaily_work_url(String daily_work_url) {
		this.daily_work_url = daily_work_url;
	}
	public String getWork_schedule_url() {
		return work_schedule_url;
	}
	public void setWork_schedule_url(String work_schedule_url) {
		this.work_schedule_url = work_schedule_url;
	}
	public String getWork_order_url() {
		return work_order_url;
	}
	public void setWork_order_url(String work_order_url) {
		this.work_order_url = work_order_url;
	}
	public String getGetAuditTodo() {
		return getAuditTodo;
	}
	public void setGetAuditTodo(String getAuditTodo) {
		this.getAuditTodo = getAuditTodo;
	}
	public String getmBaseUrl() {
		return mBaseUrl;
	}
	public void setmBaseUrl(String mBaseUrl) {
		this.mBaseUrl = mBaseUrl;
	}
    
    
    
}
