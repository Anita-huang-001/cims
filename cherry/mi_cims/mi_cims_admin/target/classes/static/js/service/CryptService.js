"use strict"

/**
 * 加解密服务
 */
app.service("CryptService", function() {
	
	/**
	 * MD5算法
	 * */
	this.MD5 = function(str) {
		return CryptoJS.MD5(str).toString();
	};
	
	/**
	 * SHA-256算法
	 * */
	this.SHA256 = function(str) {
		return CryptoJS.SHA256(str).toString();
	};
	
	/**
	 * AES算法
	 * */
	this.AES = {
		/**
		 * 加密
		 * @param str 要加密的字符串
		 * @param skey 加密密钥
		 * @return 加密后的字符串
		 * */
		encrypt : function(str, skey) {
			var key = CryptoJS.enc.Utf8.parse(skey);
			var enKey = [];
			
			var iv = key;
			var srcs = CryptoJS.enc.Utf8.parse(str);
			var encrypted = CryptoJS.AES.encrypt(srcs, key, {
				iv : iv,
				mode : CryptoJS.mode.CBC
			});
			//return encrypted.ciphertext.toString();
			return encrypted.toString();
		},
		
		/**
		 * 解密
		 * @param str 要解密密的字符串
		 * @param skey 解密密钥
		 * @return 解密后的字符串
		 * */
		decrypt : function(str, skey) {
			var key = CryptoJS.enc.Utf8.parse(skey); 
		    var iv = key;
		    var encryptedHexStr = CryptoJS.enc.Base64.parse(str);
		    var srcs = CryptoJS.enc.Base64.stringify(encryptedHexStr);
			var decrypt = CryptoJS.AES.decrypt(srcs, key, {
				iv : iv,
				mode : CryptoJS.mode.CBC
			});  
			var decryptedStr = decrypt.toString(CryptoJS.enc.Utf8);
		    return decryptedStr.toString();
		}
	};
	
}); 