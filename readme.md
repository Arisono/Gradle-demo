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
- 【LeetCode源码及题解】在[com.gradle.java.leetcode](https://github.com/Arisono/Gradle-demo/tree/master/src/main/java/com/gradle/java/leetcode)包名下;
- 【设计模式源码与分析】在[com.gradle.design](https://github.com/Arisono/Gradle-demo/tree/master/src/main/java/com/gradle/design)包名下;
- 【Java基本知识】在[com.gradle.java](https://github.com/Arisono/Gradle-demo/tree/master/src/main/java/com/gradle/java)包名下;
- 【Rxjava+Retrofit+Okhttp组合使用】在[com.gradle.android.retrofit](https://github.com/Arisono/Gradle-demo/tree/master/src/main/java/com/gradle/android/retrofit)包名下;

  
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

2017-05-10

- okhttp 缓存策略简单处理。

2017-04-20

- 网络库完善：retrofit+okhttp+rxjava 框架进行基本的单个文件上传和下载。多个文件上传和下载。

2017-04-19

- builder设计模式的引入
- 网络库完善：初步利用简单工厂模式来隔离  网络请求具体实现 ,你可以选择retrofit,okhttp,httpClient,HttpUrlConnection,Volley等具体框架或者类库,变换具体类改动比较小。后期打算整改为工厂模式,完全符合开闭原则。
- 案例接口用的是本地服务器程序，有springboot搭建的web应用。[服务器源码](https://github.com/Arisono/Chapter)关于部署springboot 请自行百度。

2017-04-05

- 网络库完善： 封装Retrofit+Rxjava+Okhttp 网络请求
- 网络库完善：添加统一请求头，get，post添加公共参数，利用okhttp的拦截器机制
- 网络库完善：添加响应时间，超时时间，是否打印日志。
- 网络库完善：okhttp错误重试，rxjava错误重试机制，网络请求失败后再次尝试请求

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
