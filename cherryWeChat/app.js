//app.js
App({
  onLaunch: function () {
    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })

    // 判断用户登录是否失效，未失效免登录直接进入，失效重新登录
    var userInfo = wx.getStorageSync('userInfo') || [];
    // 未登录过或小程序无缓存时，需要登录，否则进入失效判断
    if(userInfo.length == 0){
      return
    }else{
      // 当小程序登录时间超过有效时期后，须重新登录，否则无需登录直接进入画面
      if(new Date() - userInfo.lastLoginTime > this.globalData.validLoginTime*60*60*1000){
        // 失效删除缓存
        wx.clearStorageSync('userInfo');
        // 提示信息
        wx.showToast({
          icon: 'none',
          title: '登录已失效，请重新登陆'
        })
      }else{
        // 保存用户登录信息
        this.globalData.userInfo = userInfo;
        // 跳转相应页面
        wx.navigateTo({
          url: '/pages/role/role',
        })
      }
    }
  },
  // 全局通用变量信息
  globalData: {
    // 用户完整信息
    userInfo: null,
    // 登录有效时间(小时)
    validLoginTime: 0.01
  }
})