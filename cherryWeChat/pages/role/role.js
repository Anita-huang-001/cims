const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 用户当前所拥有的全部权限
    roleList: null,
    // 角色id
    roleId: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    // 获取当前用户全部的角色信息
    that.setData({
      roleList: app.globalData.userInfo.roleInfoList
    })
  },

  // 选择角色
  selectRole: function(e){
    var that = this
    that.setData({
      roleId: e.detail.value
    })
  },

  // 进入相应的角色页面
  goRole: function(){
    var that = this
    if(that.data.roleId == "1"){
      wx.navigateTo({
        url: '/pages/manager/manager',
      })
    }else{
      wx.showToast({
        title: '请选择超级管理员',
        icon: 'none'
      })
    }
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