package com.gradle.test.encryption;

import com.android.retrofit.demo.OkhttpUtils;
import com.gradle.java.encryption.BytesToHex;
import com.gradle.java.encryption.DESUtil;

/**
 * @author Arison
 * 对称加密
 */
public class testDES {

	//待加密原文
	public static final String DATA = "hi, welcome to my git area!";
	
	public static void main(String[] args) throws Exception {
		byte[] desKey = DESUtil.initKey();
		OkhttpUtils.println(new String(desKey, "utf-8"));
		System.out.println("DES Key : " + BytesToHex.fromBytesToHex(desKey));
		byte[] desReult = DESUtil.encryptDES(DATA.getBytes(), desKey);
		byte[] desReult1 = DESUtil.encryptDES(DATA.getBytes(), desKey);
		System.out.println(DATA + "DES 加密 =====>>>>>>> " + BytesToHex.fromBytesToHex(desReult));
		System.out.println(DATA + "DES 加密1 =====>>>>>>> " + BytesToHex.fromBytesToHex(desReult1));
		byte[] plain = DESUtil.decryptDES(desReult, desKey);
		System.out.println(DATA + "DES 解密 =====>>>>>>> " + new String(plain));
	}
}
