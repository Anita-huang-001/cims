// 使用外部jq插件加解密
const CryptoJs = require('crypto-js');

// MD5加密
function md5(str) {
  str = CryptoJs.MD5(str).toString();
  return str;
};

// SHA256加密
function sha256(str) {
  str = CryptoJs.SHA256(str).toString();
return str;
};

// AES加密
function AES() {
  /**
   * 加密
   * @param str 要加密的字符串
   * @param skey 加密密钥
   * @return 加密后的字符串
   * */
   function encrypt(str, skey) {
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
  };
  
  /**
   * 解密
   * @param str 要解密密的字符串
   * @param skey 解密密钥
   * @return 解密后的字符串
   * */
   function decrypt(str, skey) {
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

// 外部调用方法
module.exports = {
  md5: md5,
  sha256: sha256,
}