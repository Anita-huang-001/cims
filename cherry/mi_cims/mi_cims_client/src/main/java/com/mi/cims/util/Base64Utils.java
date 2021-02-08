package com.mi.cims.util;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * Base64Utils: Base64加密解密工具.
 *
 * @author Magic Image-张万创
 * @date Oct 25, 2017 6:39:24 PM
 * @version V1.0.0
 */
public class Base64Utils {

    /**
     * 
     * encode:加密.
     * 
     * @author 张万创
     * @date Oct 25, 2017 6:39:50 PM
     * @param plainText
     * @return
     * @throws Exception
     */
    public static String encode(String plainText) throws Exception {
        if (plainText == null) {
            return null;
        }
        byte[] cipherText = Base64.encodeBase64(plainText.getBytes("utf-8"));
        return new String(cipherText);
    }

    /**
     * 
     * decode:解密.
     * 
     * @author 张万创
     * @date Oct 25, 2017 6:46:44 PM
     * @param cipherText
     * @return
     * @throws Exception
     */
    public static String decode(String cipherText) throws Exception {
        if (cipherText == null) {
            return null;
        }
        byte[] plainText = Base64.decodeBase64(cipherText.getBytes("utf-8"));
        return new String(plainText);
    }

    public static void main(String args[]) {
        String input = "33333333";
        String encodeStr = "";
        try {
            encodeStr = Base64Utils.encode(input);
            System.out.println(encodeStr);
            System.out.println(Base64Utils.decode(encodeStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
