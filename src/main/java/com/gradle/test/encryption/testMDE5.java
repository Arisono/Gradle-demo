package com.gradle.test.encryption;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.gradle.java.encryption.MD5Utils;

/**
 * @author Arison
 * 不可逆的        非对称加密
 * MD5算法加密
 * 非盐加密+盐加密
 */
public class testMDE5 {

	public static void main(String[] args) {
    	//非盐加密
    	System.out.println("非盐加密："+MD5Utils.encode("13266699268"));
    	System.out.println("非盐加密："+MD5Utils.encode("111111"));
    	//盐加密
		try {
			String pwd=MD5Utils.getEncryptedPwd("123456");
			System.out.println("盐加密   密文："+pwd);
			System.out.println("盐加密  是否验证通过："+MD5Utils.validPasswd("123456", pwd));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
