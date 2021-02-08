// 引入主应用实例
const app = getApp();
// 引入密码加密实例
const crypto = require('../../EDE/crypto.js');
// 引入参数实例
const util = require('../../utils/util.js');

Page({
  data: {
    // 提交的登录信息
    LoginInfoBo: {},
    // 用户登录名
    loginId: "",
    // 用户登录密码
    loginPwd: "",
    // 密码显示
    showPwd: true
  },

  //事件处理函数
  onLoad: function () {

  },

  // 保存用户登录名
  saveLoginId: function(e){
    var that = this;
    that.setData({
      loginId: e.detail.value
    })
  },

  // 保存用户密码
  saveLoginPwd: function(e){
    var that = this;
    that.setData({
      loginPwd: e.detail.value
    })
  },

  // 显示隐藏真实密码
  changeShow: function(){
    var that = this
    if(that.data.showPwd == true){
      that.setData({
        showPwd: false
      })
    }else{
      that.setData({
        showPwd: true
      })
    }
  },

  // 登录前校验
  checkLogin: function(loginId,loginPwd){
    // 校验用户名
    if(loginId == ""){
      wx.showToast({
        title: '用户名不可为空',
        icon: 'none'
      })
      return false
    }else{
      if(loginId.length < 2){
        wx.showToast({
          title: '用户名长度最少为2位',
          icon: 'none'
        })
        return false
      }
    }
    // 校验用户登录密码
    if(loginPwd == ""){
      wx.showToast({
        title: '用户密码不可为空',
        icon: 'none'
      })
      return false
    }else{
      if(loginPwd.length < 6){
        wx.showToast({
          title: '用户密码长度最少为6位',
          icon: 'none'
        })
        return false
      }
    }
    return true
  },

  // 用户登录
  login: function(){
    var that = this;
    // 登录前校验用户名和密码
    var checkResult = that.checkLogin(that.data.loginId,that.data.loginPwd)
    // 校验失败退出
    if(checkResult == false) return
    wx.showLoading({
      title: '正在登陆请稍后',
    })
    // 新建保存登录信息对象
    function LoginInfo(loginId,loginPwd){
      // 用户登录名
      this.loginId= loginId;
      // 用户登录密码
      this.loginPwd= loginPwd;
    }
    // 保存新建对象信息
    that.setData({
      LoginInfoBo: new LoginInfo(that.data.loginId, crypto.md5(crypto.sha256(crypto.md5(that.data.loginPwd))))
    })
    // 发送请求
    wx.request({
      url: util.URL + '/login',
      method: "POST",
      data: that.data.LoginInfoBo,
      header: {
        'content-type': 'application/json;charset=utf-8'
      },
      success:  function(res) {
        wx.hideLoading();
        var ret = res.data;
        // 返回信息成功标识，否则弹出错误信息
        if(ret.resultType == "0"){
          // 弹出成功信息
          wx.showToast({
            title: '登陆成功',
          })
          // 延时进入页面
          setTimeout(function() {
            // 保存用户登录信息
            app.globalData.userInfo = ret.resultData;
            wx.setStorageSync('userInfo', ret.resultData);
            // 跳转相应页面
            wx.navigateTo({
              url: '/pages/role/role',
            })
          }, 1500);
        }else{
          // 弹出错误信息
          wx.showToast({
            icon: "none",
            title: ret.resultMsg
          })
          // 清空用户名密码
          that.setData({
            LoginInfoBo: {},
            loginId: "",
            loginPwd: "",
          })
        }
      },
      fail: function(res){
        wx.hideLoading();
        wx.showToast({
          title: "网络请求失败"
        })
      },
      complete: function(){
      }
    })
  }
})