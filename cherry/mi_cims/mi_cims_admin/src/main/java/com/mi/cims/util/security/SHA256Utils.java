package com.mi.cims.util.security;

import org.apache.commons.codec.digest.DigestUtils;

public class SHA256Utils {

	// sha256加密
	public static String sha256Encode(String input){
		return DigestUtils.sha256Hex(input);
	}
}
