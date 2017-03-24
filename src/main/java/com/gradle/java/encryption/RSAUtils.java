package com.gradle.java.encryption;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;


public class RSAUtils {
	
	public static final String KEY_ALGORITHM = "RSA";
	/** 貌似默认是RSA/NONE/PKCS1Padding，未验证 */
	public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
	public static final String PUBLIC_KEY = "publicKey";
	public static final String PRIVATE_KEY = "privateKey";

	/** RSA密钥长度必须是64的倍数，在512~65536之间。默认是1024 */
	public static final int KEY_SIZE = 2048;

	public static final String PLAIN_TEXT = "MANUTD is the greatest club in the world";

	public static void main(String[] args) {
		Map<String, byte[]> keyMap = generateKeyBytes();
		// 加密
		byte[] publicKey = keyMap.get(PUBLIC_KEY);
		// 解密
		byte[] privateKey = keyMap.get(PRIVATE_KEY);

		String public_key = Base64.encodeBase64String(publicKey);// 字符串
		String private_key = Base64.encodeBase64String(privateKey);// 字符串

		System.out.println("公钥：" + public_key);
		System.out.println("私钥：" + private_key);

		// 密文
		byte[] encodedText = RSAEncode(Base64.decodeBase64(public_key),
				PLAIN_TEXT.getBytes());
		String miwen=Base64.encodeBase64String(encodedText);
		System.out.println("RSA encoded: " +miwen);
		
		System.out.println("RSA decoded: "
				+ RSADecode(Base64.decodeBase64(private_key), Base64.decodeBase64(miwen)));
	}

	/**
	 * 生成密钥对。注意这里是生成密钥对KeyPair，再由密钥对获取公私钥
	 * 生成RSA的公钥和私钥
	 * @return
	 */
	public static Map<String, byte[]> generateKeyBytes() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator
					.getInstance(KEY_ALGORITHM);
			keyPairGenerator.initialize(KEY_SIZE);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			Map<String, byte[]> keyMap = new HashMap<String, byte[]>();
			keyMap.put(PUBLIC_KEY, publicKey.getEncoded());
			keyMap.put(PRIVATE_KEY, privateKey.getEncoded());
			return keyMap;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 生成RSA的公钥和私钥
	 */
	public static Map<String, Object> generateKey() throws Exception{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGenerator.initialize(KEY_SIZE);  //512-65536 & 64的倍数
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/**
	 * 还原公钥，X509EncodedKeySpec 用于构建公钥的规范
	 * 
	 * @param keyBytes
	 * @return
	 */
	public static PublicKey restorePublicKey(byte[] keyBytes) {
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);

		try {
			KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
			PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 还原私钥，PKCS8EncodedKeySpec 用于构建私钥的规范
	 * 
	 * @param keyBytes
	 * @return
	 */
	public static PrivateKey restorePrivateKey(byte[] keyBytes) {
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
				keyBytes);
		try {
			KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
			PrivateKey privateKey = factory
					.generatePrivate(pkcs8EncodedKeySpec);
			return privateKey;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加密，三步走。
	 * 
	 * @param key
	 * @param plainText
	 * @return
	 */
	public static byte[] RSAEncode(byte[] key, byte[] plainText) {
		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, restorePublicKey(key));
			return cipher.doFinal(plainText);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 公钥加密
	 */
	public static byte[] encrypt(byte[] data, RSAPublicKey publicKey) throws Exception{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] cipherBytes = cipher.doFinal(data);
		return cipherBytes;
	}
	
	/**
	 * 解密，三步走。
	 * 
	 * @param key
	 * @param encodedText
	 * @return
	 */
	public static String RSADecode(byte[] key, byte[] encodedText) {
		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, restorePrivateKey(key));
			return new String(cipher.doFinal(encodedText));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 私钥解密
	 */
	public static byte[] decrypt(byte[] data, RSAPrivateKey privateKey) throws Exception{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] plainBytes = cipher.doFinal(data);
		return plainBytes;
	}
}