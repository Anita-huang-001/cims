const app = getApp();
const crypto = require('../../EDE/crypto.js');
const util = require('../../utils/util.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 提交的修改密码信息
    ChangePwdBo: {},
    // 用户登录名
    loginId: "",
    // 旧密码
    oldPwd: "",
    // 新密码
    newPwd: "",
    // 确认密码
    confirmPwd: "",
    // 原密码显示
    oldShowPwd: true,
    // 原密码显示
    newShowPwd: true,
    // 原密码显示
    confirmShowPwd: true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    // 获取当前用户的登录名
    that.setData({
      loginId: app.globalData.userInfo.loginId
    })
  },

  // 保存旧密码
  saveOldPwd: function(e){
    var that = this
    that.setData({
      oldPwd: e.detail.value
    })
  },

  // 保存新密码
  saveNewPwd: function(e){
    var that = this
    that.setData({
      newPwd: e.detail.value
    })
  },

  // 保存确认密码
  saveConfirmPwd: function(e){
    var that = this
    that.setData({
      confirmPwd: e.detail.value
    })
  },

  // 显示隐藏真实密码
  changeShow: function(e){
    var that = this
    if(e.currentTarget.dataset.id == "oldPwd"){
      // 显示原密码
      if(that.data.oldShowPwd == true){
        that.setData({
          oldShowPwd: false
        })
      }else{
        that.setData({
          oldShowPwd: true
        })
      }
    }else if(e.currentTarget.dataset.id == "newPwd"){
      // 显示新密码
      if(that.data.newShowPwd == true){
        that.setData({
          newShowPwd: false
        })
      }else{
        that.setData({
          newShowPwd: true
        })
      }
    }else{
      // 显示确认密码
      if(that.data.confirmShowPwd == true){
        that.setData({
          confirmShowPwd: false
        })
      }else{
        that.setData({
          confirmShowPwd: true
        })
      }
    }
  },

  // 修改密码前校验
  checkPwd: function(oldPwd,newPwd,confirmPwd){
    // 校验原密码
    if(oldPwd == ""){
      wx.showToast({
        title: '原密码不可为空',
        icon: 'none'
      })
      return false
    }else{
      if(oldPwd.length < 6){
        wx.showToast({
          title: '原密码长度最少为6位',
          icon: 'none'
        })
        return false
      }
    }
    // 校验新密码和确认密码
    if(newPwd == "" || confirmPwd == ""){
      if(newPwd == ""){
        wx.showToast({
          title: '新密码不可为空',
          icon: 'none'
        })
        return false
      }else{
        wx.showToast({
          title: '确认密码不可为空',
          icon: 'none'
        })
        return false
      }
    }else{
      if(newPwd.length < 6){
        wx.showToast({
          title: '新密码长度最少为6位',
          icon: 'none'
        })
        return false
      }else if(confirmPwd.length < 6){
        wx.showToast({
          title: '确认密码长度最少为6位',
          icon: 'none'
        })
        return false
      }else if(newPwd != confirmPwd){
        wx.showToast({
          title: '新密码与确认密码不一致',
          icon: 'none'
        })
        return false
      }
    }
    return true
  },

  // 加密密码
  encryptedPwd: function(pwd){
    return crypto.md5(crypto.sha256(crypto.md5(pwd)));
  },
  // 修改密码
  changePwd: function(){
    var that = this;
    // 登录前校验密码
    var checkResult = that.checkPwd(that.data.oldPwd,that.data.newPwd,that.data.confirmPwd)
    // 校验失败退出
    if(checkResult == false) return
    wx.showLoading({
      title: '正在修改请稍后',
    })
    // 新建修改密码实例对象
    function ChangePwd(loginId,oldPwd,newPwd,confirmPwd){
      this.loginId = loginId;
      this.oldPwd = oldPwd;
      this.newPwd = newPwd;
      this.confirmPwd = confirmPwd;
    }
    // 保存对象信息
    that.setData({
      ChangePwdBo: new ChangePwd(that.data.loginId, that.encryptedPwd(that.data.oldPwd),that.encryptedPwd(that.data.newPwd),that.encryptedPwd(that.data.confirmPwd))
    })
    // 发送请求
    wx.request({
      url: util.URL + '/changePwd',
      method: "PUT",
      data: that.data.ChangePwdBo,
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
            title: ret.resultMsg,
          })
          // 延时进入页面
          setTimeout(function() {
            wx.navigateTo({
              url: '/pages/role/role',
            })
          }, 1500);
        }else{
          // 弹出错误信息
          wx.showToast({
            title: ret.resultMsg,
            icon: "none"
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
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})