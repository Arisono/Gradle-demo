package com.gradle.java.thread;


/**
 * @author Arison
 * 模拟Android sqlite数据库
 */
public class SQLiteDatabase {

	private static SQLiteDatabase instance;
	
	private boolean isOpen=false;
	
	public static SQLiteDatabase getInstance(){
		 if(instance==null){
			 synchronized (SQLiteDatabase.class) {
				if (instance==null) {
					instance=new SQLiteDatabase();
				}
			}
		 }
		return instance;
	}
	
	public  void openDB(){
		this.isOpen=true;
	}
	
	public  void  closeDB(){
		this.isOpen=false;
	}
	
	public  boolean getStateDB(){
		return isOpen;
	}
}
