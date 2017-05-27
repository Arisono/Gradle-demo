package com.gradle.java.rxjava;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;


/**
 * @author Arison
 * PublishSubject 与普通的Subject不同，在订阅时并不立即触发订阅事件，而是允许我们在任意时刻手动调用onNext(),onError(),onCompleted来触发事件。
 */
public class RxBus {
	
	 private static volatile RxBus mDefaultInstance;

	    private RxBus() {
	    }

	    public static RxBus getInstance() {
	        if (mDefaultInstance == null) {
	            synchronized (RxBus.class) {
	                if (mDefaultInstance == null) {
	                    mDefaultInstance = new RxBus();
	                }
	            }
	        }
	        return mDefaultInstance;
	    }

	    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

	    public void send(Object o) {
	        _bus.onNext(o);
	    }

	    public Observable<Object> toObservable() {
	        return _bus;
	    }
}
