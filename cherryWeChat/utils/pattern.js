  // 正则表达式（id不可命名为"notNull/min/max"）
  const regulars =[
    // 手机号码
    {id: "phone", value: /^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\d{8}$/},
    // 电子邮件
    {id: "mail", value: /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/},
  ]

  /**
   * 参数校验
   * regularName：校验类型：notNull-非空；min-最小位；max-最大位；其他-正则表达式
   * value：要校验的参数
   * size：min/max需要的大小位数,notNull/其他设为null
   * errorMsg：错误消息：null/""不弹；有值弹出
  */
  function regularCheck(regularName,value,size,errorMsg){
    // 非空校验
    if(regularName == "notNull"){
      // 当参数为空时返回错误，否则返回正确
      if(value == null || value.trim() == "" || value.trim().length == 0 || value == undefined){
        // 当错误消息不为空时弹出
        if(errorMsg != null && errorMsg.length != 0){
          wx.showToast({
            icon: 'none',
            title: errorMsg,
          })
        }
        return false;
      }else{
        return true;
      }
    }
    // 最小长度校验
    else if(regularName == "min"){
      // 当参数符合位数时返回正确，否则返回错误
      if(value != null && size != null && value.trim().length >= size){
        return true;
      }else{
        // 当错误消息不为空时弹出
        if(errorMsg != null && errorMsg.length != 0){
          wx.showToast({
            icon: 'none',
            title: errorMsg,
          })
        }
        return false;
      }
    }
    // 最大长度校验
    else if(regularName == "max"){
      // 当参数符合位数时返回正确，否则返回错误
      if(value != null && size != null && value.trim().length <= size){
        return true;
      }else{
        // 当错误消息不为空时弹出
        if(errorMsg != null && errorMsg.length != 0){
          wx.showToast({
            icon: 'none',
            title: errorMsg,
          })
        }
        return false;
      }
    }
    // 正则校验
    else{
      // 循环取出正则表达式列表与校验类型参数比较
      for(var i=0;i<regulars.length;i++){
        // 当校验类型与正则表达式列表中有相等参数时
        if(regularName == regulars[i].id){
          // 开始正则校验，失败返回错误，成功返回正确
          if(!(regulars[i].value.test(value))){
            // 当错误消息不为空时弹出
            if(errorMsg != null && errorMsg.length != 0){
              wx.showToast({
                icon: 'none',
                title: errorMsg,
              })
            }
            return false;
          }else{
            return true;
          }
        }
      }
    }
    return false;
  }


  // 向外暴露的接口
  module.exports = {
    regularCheck: regularCheck,
  }
  /** 
   *  校验引用示例
   * 
   *  1.在js文件头部引用校验文件
   *  const pattern = require('../../utils/pattern.js');
   *  2.非空校验
   *  pattern.regularCheck("notNull","要检验的变量", null, "要提示的信息（null不提示）");
   *  3.最小位校验
   *  pattern.regularCheck("min","要检验的变量", 最小位数, "要提示的信息（null不提示）");
   *  4.最大位校验
   *  pattern.regularCheck("max","要检验的变量", 最大位数, "要提示的信息（null不提示）");
   *  5.最大位校验
   *  pattern.regularCheck("正则变量","要检验的变量", null, "要提示的信息（null不提示）");
  */
