# 学习版本构建工具Gradle以及Java知识

## 项目宗旨
      
- **Java基础**
        面向对象基本语法,抽象类,接口,内部类等;常用类api使用;常用算法,常用加密算法;Rxjava的使用;Http客户端接口测试,Okhttp的使用,HttpClient的使用,HttpUrlConnection的使用;集合的常见用法;
线程的基本知识;反射的基本知识;注解的基本知识;异常用法;io的基本用法;常用的json解析框架的使用;
- **Java进阶**
      设计模式;多线程,虚拟机,性能优化技巧,反射,复杂算法(红黑树,贪心,动态规划等),网络通信;
 
   
## 功能模块

- 【Okhttp接口测试】在[com.gradle.api.uas](https://github.com/Arisono/Gradle-demo/tree/master/src/main/java/com/gradle/api/uas)包名下;
- 【Java常用几种加密算法】在[com.gradle.java.encryption](https://github.com/Arisono/Gradle-demo/tree/master/src/main/java/com/gradle/java/encryption)包名下 ;[加密算法测试](https://github.com/Arisono/Gradle-demo/tree/master/src/main/java/com/gradle/java/test)
- 【Rxjava的学习笔记】在[com.gradle.java.rxjava](https://github.com/Arisono/Gradle-demo/tree/master/src/main/java/com/gradle/java/rxjava)包名下;
- 【LeetCode源码及题解】
- 【设计模式源码与分析】
- 【Java基本知识】

  
## 文档参考

 - [gradle-user-guide](https://dongchuan.gitbooks.io/gradle-user-guide-/content/overview/features.html)

## 学习目标

 - Gradle搭建一般Java项目
 - Gradle搭建Java Web项目
 - Gradle搭建Java Web多项目引用,多项目的Java构建

## 环境搭建
 
 - eclipse 集成Gradle插件BulidShip
 - eclipse 导入gradle项目，需要选择import gradle 类型的方式;否则eclipse编译会报gradle本地路径找不到的错误

## 更新记录


2017-04-05

- 封装Retrofit+Rxjava+Okhttp 网络请求
- 添加统一请求头，get，post添加公共参数，利用okhttp的烂机器机制
- okhttp错误重试，rxjava错误重试机制，网络请求失败后再次尝试请求

2017-03-13

- 归类java常用的加密算法以及补充测试程序

2017-03-10

- 解析fastjson 序列化反序列化排序问题;toString和parseObject
- api 再次封装和优化okhttp的get,post方法

2017-02-17
  
- 建立rxjava包目录； 测试rxjava1 rxjava2的基本使用
- 了解rxjava的两个方法observeOn与subscribeOn
- 了解rxjava 的rxbus基本封装方式，主要了解PublishSubject的使用

2017-02-15
- 集成okhttp
- 测试接口
