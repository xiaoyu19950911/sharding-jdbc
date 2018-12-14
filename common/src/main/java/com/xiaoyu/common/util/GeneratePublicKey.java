package com.xiaoyu.common.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class GeneratePublicKey {
    public static void main(String[] args) {
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // 得到公钥字符串
        String publicKeyString = Base64Util.encode(publicKey.getEncoded());
        // 得到私钥字符串
        String privateKeyString = Base64Util.encode(privateKey.getEncoded());
        System.out.println(publicKeyString);
        System.out.println(privateKeyString);
    }
}
