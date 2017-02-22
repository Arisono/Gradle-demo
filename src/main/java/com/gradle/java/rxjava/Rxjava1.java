package com.gradle.java.rxjava;


import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Rxjava1的基本使用
 * @author Arison
 *
 */
public class Rxjava1 { 

	public static void main(String[] args) {
	    method1();//subscribe()  有订阅回调
        method2(); //subscribe() 没有订阅回调
	}

	private static void method2() {
		  Observable.create(new Observable.OnSubscribe<String>() {
			 @Override
			public void call(Subscriber<? super String> t) {
				    System.out.println("--------------------------------");
			        t.onNext("这是什么东西");
			        t.onCompleted();
				
			}
		   })
		  .subscribeOn(Schedulers.io())
		  .subscribe();//订阅方法
	}

	/**
	 * 
	 */
	public static void method1() {
		//	Subscription mSubscription=
		  Observable.create(new Observable.OnSubscribe<String>() {
			 @Override
			public void call(Subscriber<? super String> t) {
				    System.out.println("--------------------------------");
			        t.onNext("这是什么东西");
			        t.onCompleted();
				
			}
		   })
		  .subscribeOn(Schedulers.io())
		  .subscribe(new Observer<String>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted()");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("onError()");
			}

			@Override
			public void onNext(String t) {
				System.out.println(t);
			}
		});
	}

}
