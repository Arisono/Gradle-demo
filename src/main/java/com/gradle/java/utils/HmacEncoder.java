package com.gradle.java.utils;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Hash-based message authentication code，利用哈希算法，以一个密钥和一个消息为输入，生成一个消息摘要作为输出
 * 单项不可逆算法加密
 * @author Arison
 */
public class HmacEncoder {

    private final String algorithm;

    public HmacEncoder(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * 转换密钥
     *
     * @param key       二进制密钥
     * @param algorithm 密钥算法
     * @return 密钥
     */
    private static Key toKey(byte[] key, String algorithm) {
        // 生成密钥
        return new SecretKeySpec(key, algorithm);
    }

    /**
     * 根据给定密钥生成算法创建密钥
     *
     * @param algorithm 密钥算法
     * @return 密钥
     * @throws RuntimeException 当 {@link NoSuchAlgorithmException} 发生时
     */
    public byte[] getKey() {
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获得密钥
        return secretKey.getEncoded();
    }

    /**
     * 使用指定消息摘要算法计算消息摘要
     *
     * @param data 做消息摘要的数据
     * @param key  密钥
     * @return 消息摘要（长度为16的字节数组）
     */
    public byte[] encode(byte[] data, Key key) {
        Mac mac = null;
        try {
            mac = Mac.getInstance(algorithm);
            mac.init(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return new byte[0];
        }
        return mac.doFinal(data);
    }

    /**
     * 使用指定消息摘要算法计算消息摘要
     *
     * @param data 做消息摘要的数据
     * @param key  密钥
     * @return 消息摘要（长度为16的字节数组）
     */
    public byte[] encode(byte[] data, byte[] key) {
        return encode(data, toKey(key, algorithm));
    }

}
