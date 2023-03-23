package com.jjq.util;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/3/22
 * @desc 加解密安全相关的工具类
 */
public class SecurityUtils {


    /**
     * 生成密钥对
     *
     * @param algorithm : 算法
     * @throws Exception
     */
    public static void generateKeyPair(String algorithm) throws Exception {
        // 获取密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 获取密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 获取公钥
        PublicKey publicKey = keyPair.getPublic();
        // 获取私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 获取byte数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 进行Base64编码
        String publicKeyString = base64Encode(publicKeyEncoded);
        System.out.println("publicKey:" + publicKeyString);
        String privateKeyString = base64Encode(privateKeyEncoded);
        System.out.println("privateKey:" + privateKeyString);
    }


    /**
     * 从字符串中加载公钥
     *
     * @param algorithm : 算法
     * @param keyString : 公钥字符串
     * @return : 公钥
     * @throws Exception
     */
    public static PublicKey loadPublicKeyFromString(String algorithm, String keyString) throws Exception {
        // 进行Base64解码
        byte[] decode = base64Decode(keyString);
        // 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 构建密钥规范
        X509EncodedKeySpec keyspec = new X509EncodedKeySpec(decode);
        // 获取公钥
        return keyFactory.generatePublic(keyspec);

    }

    private static byte[] base64Decode(String keyStr) {
        return java.util.Base64.getDecoder().decode(keyStr.getBytes());
    }
    private static String base64Encode(byte[] bytes) {
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 从字符串中加载私钥
     *
     * @param algorithm : 算法
     * @param keyString : 私钥字符串
     * @return : 私钥
     * @throws Exception
     */
    public static PrivateKey loadPrivateKeyFromString(String algorithm, String keyString) throws Exception {
        // 进行Base64解码
        byte[] decode = base64Decode(keyString);
        // 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 构建密钥规范
        PKCS8EncodedKeySpec keyspec = new PKCS8EncodedKeySpec(decode);
        // 生成私钥
        return keyFactory.generatePrivate(keyspec);
    }


    /**
     *
     * @param content : 原文
     * @param publicKeyStr : 公钥字符串
     * @return
     * @throws Exception
     */
    public static String encryptInRSA(String content, String publicKeyStr) {
        try {
            return encrypt("RSA", content, loadPublicKeyFromString("RSA", publicKeyStr), 512);
        } catch (Exception e) {
            throw new RuntimeException("rsa加密失败，content:" + content);
        }
    }

    /**
     * 解密
     * @param content : 原文
     * @param privateKeyStr : 公钥字符串
     * @return
     * @throws Exception
     */
    public static String decryptInRSA(String content, String privateKeyStr) {
        try {
            return decrypt("RSA", content, loadPrivateKeyFromString("RSA", privateKeyStr), 512);
        } catch (Exception e) {
            throw new RuntimeException("rsa解密失败，content:" + content);
        }
    }

    /**
     * 使用密钥加密数据
     *
     * @param algorithm      : 算法
     * @param input          : 原文
     * @param key            : 密钥
     * @param maxEncryptSize : 最大加密长度(需要根据实际情况进行调整)
     * @return : 密文
     * @throws Exception
     */
    public static String encrypt(String algorithm, String input, Key key, int maxEncryptSize) throws Exception {
        // 获取Cipher对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 初始化模式(加密)和密钥
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // 将原文转为byte数组
        byte[] data = input.getBytes();
        // 总数据长度
        int total = data.length;
        // 输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        decodeByte(maxEncryptSize, cipher, data, total, baos);
        // 对密文进行Base64编码
        return base64Encode(baos.toByteArray());

    }

    /**
     * 解密数据
     *
     * @param algorithm      : 算法
     * @param encrypted      : 密文
     * @param key            : 密钥
     * @param maxDecryptSize : 最大解密长度(需要根据实际情况进行调整)
     * @return : 原文
     * @throws Exception
     */
    public static String decrypt(String algorithm, String encrypted, Key key, int maxDecryptSize) throws Exception {
        // 获取Cipher对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 初始化模式(解密)和密钥
        cipher.init(Cipher.DECRYPT_MODE, key);
        // 由于密文进行了Base64编码, 在这里需要进行解码
        byte[] data = base64Decode(encrypted);
        // 总数据长度
        int total = data.length;
        // 输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        decodeByte(maxDecryptSize, cipher, data, total, baos);
        // 输出原文
        return baos.toString();

    }

    /**
     * 分段处理数据
     *
     * @param maxSize : 最大处理能力
     * @param cipher  : Cipher对象
     * @param data    : 要处理的byte数组
     * @param total   : 总数据长度
     * @param baos    : 输出流
     * @throws Exception
     */
    public static void decodeByte(int maxSize, Cipher cipher, byte[] data, int total, ByteArrayOutputStream baos) throws Exception {
        // 偏移量
        int offset = 0;
        // 缓冲区
        byte[] buffer;
        // 如果数据没有处理完, 就一直继续
        while (total - offset > 0) {
            // 如果剩余的数据 >= 最大处理能力, 就按照最大处理能力来加密数据
            if (total - offset >= maxSize) {
                // 加密数据
                buffer = cipher.doFinal(data, offset, maxSize);
                // 偏移量向右侧偏移最大数据能力个
                offset += maxSize;
            } else {
                // 如果剩余的数据 < 最大处理能力, 就按照剩余的个数来加密数据
                buffer = cipher.doFinal(data, offset, total - offset);
                // 偏移量设置为总数据长度, 这样可以跳出循环
                offset = total;
            }
            // 向输出流写入数据
            baos.write(buffer);
        }
    }
}
