package com.gradle.java.rxjava;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.net.SocketTimeoutException;
import java.rmi.ConnectException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.gradle.android.retrofit.OkhttpUtils;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Rxjava1的基本使用
 * 
 * @author Arison
 *
 */
@SuppressWarnings("unused")
public class Rxjava1 {

	public static void main(String[] args) {
		// method1();//subscribe() 有订阅回调
		// method2(); //subscribe() 没有订阅回调
		// doOnNext();
		// ----操作符----
		// filter();
		// takeFirst();
		// map();
		// retryWhen();//retrywhen

	}

	private static void retryWhen() {
		Observable.create(new Observable.OnSubscribe<Integer>() {

			@Override
			public void call(Subscriber<? super Integer> t) {
				OkhttpUtils.println("执行任务");
				t.onNext(12);
				OkhttpUtils.println("遇到错误....");
//				t.onError(new RuntimeException("系统错误"));
//			    t.onError(new ConnectException("服务器拒绝连接"));
			    t.onError(new SocketTimeoutException("服务器连接超时"));
			}
		})
		.retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {

			@Override
			public Observable<?> call(Observable<? extends Throwable> t) {
				return t.flatMap(new Func1<Throwable, Observable<?>>() {
                    private int count=0;
					@Override
					public Observable<?> call(Throwable t) {
						if(t instanceof SocketTimeoutException){
							OkhttpUtils.println("错误类型：服务器请求超时！！！");
						}
						if(t instanceof ConnectException){
							OkhttpUtils.println("错误类型：服务器拒绝连接！！！");
						}
						if(t instanceof RuntimeException){
							OkhttpUtils.println("错误类型：错误类型：运行时发生异常！！！");
						}
						if(++count<=5){
							OkhttpUtils.println("网络请求重新连接"+count);
							return Observable.timer(3000, TimeUnit.MILLISECONDS);
						}
						return Observable.error(t);
					}
				});
			}

			
		})
		.observeOn(RxjavaUtils.getNamedScheduler("线程1"))
		.subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				OkhttpUtils.println("onCompleted()");
				
			}

			@Override
			public void onError(Throwable e) {
				OkhttpUtils.println(e.getMessage());
				
			}

			@Override
			public void onNext(Integer t) {
				OkhttpUtils.println(""+t);
				
			}
		});
	}

	private static void doOnNext() {
		Observable.create(new Observable.OnSubscribe<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				RxjavaUtils.threadInfo("发送事件---> call():");
				subscriber.onNext(1);// doOnNext函数监听
				//subscriber.onNext(2);
				// subscriber.onNext(3);
				// subscriber.onNext(4);
				// subscriber.onCompleted();
			}
		}).subscribeOn(RxjavaUtils.getNamedScheduler("运行在线程1上..."))
		       
				.doOnNext(new Action1<Integer>() {
					@Override
					public void call(Integer item) {
						RxjavaUtils.threadInfo("doOnNext1:" + item);
						item++;
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				})
				
				.doOnSubscribe(new Action0() {

					@Override
					public void call() {
						RxjavaUtils.threadInfo("线程任务2");

					}
				})
				.doOnSubscribe(new Action0() {

					@Override
					public void call() {
						RxjavaUtils.threadInfo("线程任务3");

					}
				})
				.doOnSubscribe(new Action0() {

					@Override
					public void call() {
						RxjavaUtils.threadInfo("线程任务4");

					}
				})
				.subscribeOn(RxjavaUtils.getNamedScheduler("运行在线程2上..."))
				.doOnSubscribe(new Action0() {

					@Override
					public void call() {
						RxjavaUtils.threadInfo("线程任务5");

					}
				})
				.doOnNext(new Action1<Integer>() {

					@Override
					public void call(Integer t) {

						RxjavaUtils.threadInfo("doOnNext2:" + t);
					}

				})
				 .filter(new Func1<Integer, Boolean>() {

					@Override
					public Boolean call(Integer t) {
					     RxjavaUtils.threadInfo("过滤器执行：");
						return true;
					}
				})
				.subscribe(new Subscriber<Integer>() {
					@Override
					public void onNext(Integer item) {
						RxjavaUtils.threadInfo("onNext:" + item);
					}

					@Override
					public void onError(Throwable error) {
						System.err.println("Error: " + error.getMessage());
					}

					@Override
					public void onCompleted() {
						System.out.println("Sequence complete.");
					}
				});
	}

	/**
	 * map() 把原数据通过函数加工生成我们期望的数据
	 */
	public static void map() {
		Observable.from(new String[] { "This", "is", "RxJava" })
				.map(new Func1<String, String>() {
					@Override
					public String call(String s) {
						return s.toUpperCase();
					}
				}).toList()// 转成List
				.map(new Func1<List<String>, List<String>>() {
					@Override
					public List<String> call(List<String> strings) {
						Collections.reverse(strings);
						return strings;
					}
				})
				// .observeOn(AndroidSchedulers.mainThread())
				// .subscribeOn(Schedulers.io())
				.subscribe(new Action1<List<String>>() {
					@Override
					public void call(List<String> s) {
						OkhttpUtils.println(JSON.toJSONString(s));
					}
				});

		Observable
				.just("test")
				.map(new Func1<String, String>() {
					@Override
					public String call(String s) {
						return "http://www.baidu.com/" + s;
					}
				})
				.map(o -> "百度接口：" + o)
				.toList()
				.map(new Func1<List<String>, List<String>>() {
					public java.util.List<String> call(java.util.List<String> t) {
						t.add("騰訊接口：http://www.tencent.com");
						return t;
					};
				}).map(o -> JSON.toJSONString(o))
				.map(new Func1<String, List<String>>() {
					@SuppressWarnings("unchecked")
					public List<String> call(String t) {
						return (List<String>) JSON.parse(t);
					};
				}).subscribe(new Action1<List<String>>() {
					@Override
					public void call(List<String> s) {
						OkhttpUtils.println(JSON.toJSONString(s));
					}

				});

	}

	/**
	 * takeFirst()
	 */
	public static void takeFirst() {
		Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
				.takeFirst(new Func1<Integer, Boolean>() {
					@Override
					public Boolean call(Integer student) {
						return true;
					}
				}).subscribe(new Observer<Integer>() {
					@Override
					public void onCompleted() {
						OkhttpUtils.println("onCompleted()");
					}

					@Override
					public void onError(Throwable e) {
						OkhttpUtils.println("onError()");
					}

					@Override
					public void onNext(Integer student) {
						OkhttpUtils.println("onNext():" + student);
					}
				});
	}

	/**
	 * filter() 操作符
	 */
	public static void filter() {
		Observable.just(1, 2, 3, 4, 5).filter(new Func1<Integer, Boolean>() {
			@Override
			public Boolean call(Integer item) {
				return (item < 4);
			}
		}).subscribe(new Subscriber<Integer>() {
			@Override
			public void onNext(Integer item) {
				System.out.println("Next: " + item);
			}

			@Override
			public void onError(Throwable error) {
				System.err.println("Error: " + error.getMessage());
			}

			@Override
			public void onCompleted() {
				System.out.println("Sequence complete.");
			}
		});
	}

	private static void method2() {
		Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> t) {
				System.out.println("--------------------------------");
				t.onNext("这是什么东西");
				t.onCompleted();

			}
		}).subscribeOn(Schedulers.io()).subscribe();// 订阅方法
	}

	/**
	 * 
	 */
	public static void method1() {
		// Subscription mSubscription=
		Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> t) {
				System.out.println("--------------------------------");
				t.onNext("这是什么东西");
				t.onCompleted();

			}
		}).subscribeOn(Schedulers.io()).subscribe(new Observer<String>() {

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
