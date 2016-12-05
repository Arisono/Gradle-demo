package com.gradle.java.utils;

/**
 * Hmac加密工具
 * 单项加密，不可逆  单向加密的用途主要是为了校验数据在传输过程中是否被修改。
 * @author Ariso
 *
 */
public class HmacUtils {

	private static HmacEncoder hmacEncoder;

	// 默认约定密钥
	private final static byte[] key = { 104, 116, 116, 112, 58, 47, 47, 119,
			119, 119, 46, 117, 98, 116, 111, 98, 46, 99, 111, 109, 47, 101,
			114, 112, 47, 115, 97, 108, 101, 47, 111, 114, 100, 101, 114, 115,
			63, 115, 111, 109, 101, 116, 104, 105, 110, 103 };

	static {
		// default algorithm: HmacSHA256
		hmacEncoder = new HmacSHA256Encoder();
	}

	/**
	 * 
	 * @param message
	 * @return 16进制密文
	 */
	public static String encode(Object message) {
		//System.out.println("mi:"+new String(key));
		byte[] encodeData = hmacEncoder.encode(String.valueOf(message)
				.getBytes(), key);
		return new String(Hex.encode(encodeData));
	}
	
	public static void main(String[] args) {
		
		System.out.println(new String(key));
		System.out.println(encode("13266699268"));
	}

}
