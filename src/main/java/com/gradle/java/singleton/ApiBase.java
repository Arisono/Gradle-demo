package com.gradle.java.singleton;

/**
 * Created by Arison on 2017/3/2.
 */
public abstract class ApiBase {
    //定义各种公共接口
    public String login;//登录
    public String getMasters;//获取账套


    public String getGetMasters() {
        return getMasters;
    }

    public void setGetMasters(String getMasters) {
        this.getMasters = getMasters;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
