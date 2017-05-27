package com.gradle.android.subscriber;

/**
 * @author Arison
 * 回调接口
 * @param <T>
 */
public interface SubscriberOnNextListener<T> {
	 void onNext(T t);
}
