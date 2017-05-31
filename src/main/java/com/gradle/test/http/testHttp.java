package com.gradle.test.http;

import com.android.base.net.HttpClient;
import com.android.base.net.RetrofitImpl;
import com.android.retrofit.subscriber.NetResquestSubscriber;
import com.android.retrofit.subscriber.SubscriberOnNextListener;

/**
 * 测试网络请求
 * Created by Arison on 2017/5/31.
 */
public class testHttp {
    public static final String BASE_URL="http://192.168.253.200:8080/Chapter/";
    
    /**
      * @desc:
      * @author：Arison on 2017/5/31
      */
    public static void main(String arg[]){
        HttpClient httpClient=new HttpClient.Builder()
                .url(BASE_URL)
                .httpBase(RetrofitImpl.getInstance())
                .build();


        httpClient.Api().send(new HttpClient.Builder()
        .url("json")
        .add("12","12")
        //.header("12","12")
        .build(),new NetResquestSubscriber<>(new SubscriberOnNextListener<Object>() {
            @Override
            public void onNext(Object o) {

            }
        }));
    }
}
