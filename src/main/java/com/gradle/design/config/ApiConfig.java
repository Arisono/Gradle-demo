package com.gradle.design.config;

/**
 * Created by Arison on 2017/3/2.
 */
public class ApiConfig {
    
    private static ApiConfig mInstance;
    
    private ApiModel mApiConfig;

    public ApiConfig(ApiModel api) {
        this.mApiConfig = api;
        
    }
    
    public static ApiConfig getInstance(ApiModel api){
        if (mInstance==null){
         synchronized (ApiConfig.class){
             if (mInstance==null){
                 mInstance=new ApiConfig(api);
             }
         }
        }
        return mInstance;
    }

    public ApiModel getmApiConfig() {
        return mApiConfig;
    }
    
    public ApiBase getmApiBase() {
        return (ApiBase) mApiConfig;
    }

    public void setmApiConfig(ApiModel mApiConfig) {
        this.mApiConfig = mApiConfig;
    }
    
    public ApiPlatform getmApiPlatform(){
        return  (ApiPlatform)mApiConfig;
    }
    
    public ApiUAS getmApiUAS(){
        return (ApiUAS)mApiConfig;
    }
}
