package com.gradle.java.thread;

import java.util.concurrent.atomic.AtomicInteger;

import com.gradle.android.utils.OkhttpUtils;

/**
 * @author Arison
 * 管理sqllite数据库
 */
public class DataBaseManager {
	
	private static DataBaseManager instance;
	
    /**
     * 保证多线程下原子操作
     */
    private  AtomicInteger i=new AtomicInteger();
    
    public static DataBaseManager getInstance(){
    	if(instance==null){
    		synchronized (DataBaseManager.class) {
				if (instance==null) {
					OkhttpUtils.println("数据库管理类DataBaseManager--->单例初始化！");
					instance=new DataBaseManager();
				}
			}
    	}
		return instance;
    }
	
	/**
	 * 模拟Android 数据库在多线程下的并发问题
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=1;i<=2000;i++){
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					//切记,打开,关闭数据库的操作不能直接SQLiteDatabase.getInstance().openDB();SQLiteDatabase.getInstance().closeDB();
					//必须调用管理者单例类DataBaseManager 来调用打开和关闭操作,从而解决多线程下访问sqlite数据库的问题
					DataBaseManager.getInstance().openDataBase();
					DataBaseManager.getInstance().closeDataBase();
				}
			},""+i).start();
		}

	}
	
	public synchronized AtomicInteger openDataBase(){
		OkhttpUtils.println("+++++++++++++++++++++++++++++++++++++++++++");
		OkhttpUtils.println("线程"+Thread.currentThread().getName()+"--->open开始---->当前数据库连接数："+i);
		if (i.incrementAndGet()==1) {
			OkhttpUtils.println("线程"+Thread.currentThread().getName()+"打开数据库之前！数据库状态："
					+SQLiteDatabase.getInstance().getStateDB());
			SQLiteDatabase.getInstance().openDB();//单例类模拟数据库打开操作
			OkhttpUtils.println("线程"+Thread.currentThread().getName()+"执行数据库打开操作！");
			OkhttpUtils.println("线程"+Thread.currentThread().getName()+"打开数据库之后！数据库状态："
					+SQLiteDatabase.getInstance().getStateDB());
		}else{
			OkhttpUtils.println("线程"+Thread.currentThread().getName()+"--->open()操作无效！新增一条数据库连接！---> 数据库状态："
					+SQLiteDatabase.getInstance().getStateDB());
		}
		OkhttpUtils.println("线程"+Thread.currentThread().getName()+"--->open完毕---->当前数据库连接数："+i);
		OkhttpUtils.println("+++++++++++++++++++++++++++++++++++++++++++");
		return i;
	}
	
	public synchronized AtomicInteger closeDataBase(){
		OkhttpUtils.println("--------------------------------------------");
		OkhttpUtils.println("线程"+Thread.currentThread().getName()+"--->close开始---->当前数据库连接数："+i);
		if (i.decrementAndGet()==0) {
			
			OkhttpUtils.println("线程"+Thread.currentThread().getName()+"关闭数据库之前！数据库状态："
					+SQLiteDatabase.getInstance().getStateDB());
			SQLiteDatabase.getInstance().closeDB();//单例类模拟数据库关闭操作
			OkhttpUtils.println("线程"+Thread.currentThread().getName()+"执行数据库关闭操作！");
			OkhttpUtils.println("线程"+Thread.currentThread().getName()+"关闭数据库之后！数据库状态："
					+SQLiteDatabase.getInstance().getStateDB());
			
		}else{
			OkhttpUtils.println("线程"+Thread.currentThread().getName()+"--->close()操作无效!关闭一条数据库连接！--->数据库状态："
					+SQLiteDatabase.getInstance().getStateDB());
		}
		OkhttpUtils.println("线程"+Thread.currentThread().getName()+"--->close完毕---->当前数据库连接数："+i);
		OkhttpUtils.println("--------------------------------------------");
		return i;
	}

}
