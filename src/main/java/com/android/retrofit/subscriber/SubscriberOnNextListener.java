package com.android.retrofit.subscriber;

/**
 * @author Arison
 * 回调接口
 * @param <T>
 */
public interface SubscriberOnNextListener<T> {
	 void onNext(T t);
}
