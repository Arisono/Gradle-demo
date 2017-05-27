package com.gradle.java.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Rxjava2的使用
 * @author Arison
 *
 */
public class Rxjava2 {

	public static void main(String[] args) {
		runRxJava2();
	}
	/**
	 * 
	 */
	public static void runRxJava2() {
		// Flowable.just("Hello world").subscribe(System.out::println);
		 Observable<String> myObservable = Observable.create(  
			  new ObservableOnSubscribe<String>() {
				  
				 public void subscribe(io.reactivex.ObservableEmitter<String> e) throws Exception {
					 e.onNext("我是中国人");
					 e.onNext("我是美国人");
					 e.onNext("我是日国人");
					  e.onError(new Throwable("程序发生异常问题"));
					 e.onComplete();
				 }
			  }
			
			);  

		Observer<String> mObserver=new Observer<String>() {
			@Override
			public void onNext(String value) {
				System.out.println(value);
			}

			@Override
			public void onSubscribe(Disposable d) {
	            if(d.isDisposed())d.dispose();
				System.out.println("onSubscribe()");
			}

			@Override
			public void onError(Throwable e) {
		
				System.out.println("onError()"+e.getMessage());
			}

			@Override
			public void onComplete() {
		     
				System.out.println("onComplete()");
				
			}
		};
		
		myObservable.subscribe(mObserver);
	}
}
