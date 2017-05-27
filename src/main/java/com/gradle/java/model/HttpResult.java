package com.gradle.java.model;

public class HttpResult<T> {
	
	private int status;//状态码
    private String message;//消息
    private String path;//路径
    private long timestamp;//时间
    //用来模仿Data
    private T subjects;//实体数据
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public T getSubjects() {
		return subjects;
	}
	public void setSubjects(T subjects) {
		this.subjects = subjects;
	}
    
    

}
