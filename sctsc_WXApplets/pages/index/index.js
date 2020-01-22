const app = getApp();

Page({
  data: {
    PageCur: 'basics'
  },
  onLoad(){
    // wx.request({
    //   url: app.globalData.requestUrl,
    //   success:function(e){
    //     console.log(e)
    //   }
    // })
  },
  NavChange(e) {
    this.setData({
      PageCur: e.currentTarget.dataset.cur
    })
  },
  onShareAppMessage() {
    return {
      title: 'ColorUI-高颜值的小程序UI组件库',
      imageUrl: '/images/share.jpg',
      path: '/pages/index/index'
    }
  },
})