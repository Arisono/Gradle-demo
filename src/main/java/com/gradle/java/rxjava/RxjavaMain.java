package com.gradle.java.rxjava;

import java.util.concurrent.Executors;

import com.gradle.java.model.DownloadRepoMessageEvent;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * @author Arison
 * 注册事件-接收消息
 * 博文资料参考： http://www.jianshu.com/p/59c3d6bb6a6b
 */
public class RxjavaMain {
	
	 private final static CompositeSubscription mAllSubscription = new CompositeSubscription();
	 
	 
	 
	
	 public static void main(String[] args) {
		 
		 rxBus();//rxbus测试
		 
		 observeOn();//观察事件
		
		 subscribeOn();//订阅事件
	 }

	/**
	 * subscribeOn() 订阅事件
	 */
	public static void subscribeOn() {
		Observable
	        .create(new Observable.OnSubscribe<String>() {
				@Override
				public void call(Subscriber<? super String> subscriber) {
					   threadInfo("耗时任务2： OnSubscribe.call()");
		                subscriber.onNext("耗时任务2-RxJava");
				}
	        })
	        .subscribeOn(getNamedScheduler("线程2"))//订阅在线程2
	        .doOnSubscribe(() -> threadInfo("耗时任务1"))
	        .subscribeOn(getNamedScheduler("线程1"))//订阅在线程1
	        .doOnSubscribe(() -> threadInfo("耗时任务0"))
	        .subscribeOn(getNamedScheduler("线程0："))
	        .doOnSubscribe(() -> threadInfo("耗时任务") )
	        .subscribe(s -> {                 //订阅在当前线程
	            threadInfo(".onNext()");
	            System.out.println(s + "-onNext");
	        });
	}

	/**
	 * observeOn方法   观察事件
	 */
	public static void observeOn() {
		Observable.just("RxJava")
	        .observeOn(getNamedScheduler("map之前的observeOn"))
	        .map(s -> {
	            threadInfo(".map()-1");
	            return s + "-map1";
	        })
	        .map( s -> {
	            threadInfo(".map()-2");
	            return s + "-map2";
	        })
	        .observeOn(getNamedScheduler("自定义的线程任务"))
	        .map(s ->{
	        	  threadInfo(".map()-3");
	        	  return s+"-自定义线程任务";	
	        		})
	        .observeOn(getNamedScheduler("subscribe之前的observeOn"))
	        .subscribe(s -> {
	            threadInfo(".onNext()");
	            System.out.println("观察事件链式调用："+s + "-onNext");
	            System.out.println("----------------------------");
	        });
	}

	/**
	 * 测试rxbus
	 */
	public static void rxBus() {
		// 注册 ---被观察者
		   registerSubscription(
	                RxBus.getInstance()
	                        .toObservable()
	                        .filter(o -> o instanceof DownloadRepoMessageEvent)
	                        .map(o -> (DownloadRepoMessageEvent) o)
//	                        .observeOn(AndroidSchedulers.mainThread())
	                        .doOnNext(o -> showMessage(o.getMessage()))
	                        .subscribe());
			//发送消息  ---观察者
			RxBus.getInstance().send(new DownloadRepoMessageEvent("我这是利用rxjava技术来发送消息！"));
	}
	 
	 protected static void showMessage(String message) {
	     System.out.println(message);
	  }
	 

	 protected static void registerSubscription(Subscription subscription) {
	        mAllSubscription.add(subscription);
	  }

	 protected void unregisterSubscription(Subscription subscription) {
	        mAllSubscription.remove(subscription);
	 }
	 
	 public static Scheduler getNamedScheduler(String name) {
		    return Schedulers.from(Executors.newCachedThreadPool(r -> new Thread(r, name)));
		}

		public static void threadInfo(String caller) {
		    System.out.println(caller + " => " + Thread.currentThread().getName());
		}

}
