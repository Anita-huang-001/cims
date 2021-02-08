package com.mi.cims.util.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * ClassName: AesUtils
 * Function: AES加解密工具类
 *
 * @author Magic Image-刘伟
 * @date 2017年10月10日 下午3:55:34
 * @version V1.0.0
 */
public class AESUtils {

    /** 
     * 算法/模式/补码方式
     */
    private static final String ALGORITHMSTR = "AES/CBC/PKCS5Padding";

    /** 
     * encrypt:AES加密字符串
     * 
     * @author 刘伟 
     * @date 2017年10月11日 下午5:19:54
     * @param sSrc 待加密的字符串
     * @param sKey 密钥
     * @return 加密后的字符串
     * @throws Exception 
     */
    public static String encrypt(String sSrc, String sKey) throws Exception {
        byte[] raw = pruneSecretKey(sKey);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        IvParameterSpec iv = new IvParameterSpec(raw);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());
        // 使用base64转码
        return Base64.encodeBase64String(encrypted);
    }

    /** 
     * decrypt:AES解密字符串
     * 
     * @author 刘伟 
     * @date 2017年10月11日 下午5:27:09
     * @param sSrc 待解密的字符串
     * @param sKey 密钥
     * @return 解密后的字符串
     * @throws Exception 
     */
    public static String decrypt(String sSrc, String sKey) throws Exception {
        byte[] raw = pruneSecretKey(sKey);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        IvParameterSpec iv = new IvParameterSpec(raw);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] encrypted1 = Base64.decodeBase64(sSrc);// 先用bAES64解密
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original);
        return originalString;
    }

    /** 
     * pruneSecretKey:修整密钥key
     * 
     * @author 刘伟 
     * @date 2017年10月11日 下午5:30:00
     * @param sKey 密钥
     * @return 修整后的密钥
     * @throws Exception 
     */
    private static byte[] pruneSecretKey(String sKey) throws Exception {
        if (sKey == null || sKey.length() != 16) {
            throw new Exception("AES密钥必须是16位半角字母、数字、符号");
        }
        for (int i = 0; i < sKey.length(); i++) {
            if (sKey.charAt(i) > 126) {
                throw new Exception("AES密钥必须是16位半角字母、数字、符号");
            }
        }
        return sKey.getBytes("UTF-8");
    }

    public static void main(String[] args) throws Exception {
        String text = "12345678";
        String key = "0123456789abcdef";
        // 加密
        String encrypt = encrypt(text, key);
        System.out.println(encrypt);
        // 解密
        System.out.println(decrypt(encrypt, key));
    }
}