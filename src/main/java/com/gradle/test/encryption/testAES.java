package com.gradle.test.encryption;

import com.gradle.java.encryption.AESUtil;
import com.gradle.java.encryption.BytesToHex;

/**
 * @author Arison
 *  对称加密
 */
public class testAES {

	//待加密的原文
	public static final String DATA = "android开发工程师";
	
	public static void main(String[] args) throws Exception {
		//获得密钥
		byte[] aesKey = AESUtil.initKey();
		System.out.println("AES 密钥 : " + BytesToHex.fromBytesToHex(aesKey));
		//加密
		byte[] encrypt = AESUtil.encryptAES(DATA.getBytes(), aesKey);
		System.out.println(DATA + " AES 加密 : " + BytesToHex.fromBytesToHex(encrypt));
		
		//解密
		byte[] plain = AESUtil.decryptAES(encrypt, aesKey);
		System.out.println(DATA + " AES 解密 : " + new String(plain));
	}
}
