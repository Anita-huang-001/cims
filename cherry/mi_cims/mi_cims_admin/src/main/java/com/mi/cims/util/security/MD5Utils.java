package com.mi.cims.util.security;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
	
	// md5加密
	public static String md5Encode(String input){
        return DigestUtils.md5Hex(input);
    }
}
