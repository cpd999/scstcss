const app = getApp();
const qiniuUploader = require("../../../utils/qiniuUploader");
const util = require("../../../utils/util");
Page({
  data: {
    // 地址
    region: ['四川省', '成都市', ''],
    //简介
    modalName: null,
    //上传图片
    imgList: '',
    servletLogoPath:"",
    //上传Logo状态
    lodingSt: "0%",
    loading: false,
    //表单提交状态
    submitSt: false,
    sData: {
      saddress: "",
      sid: 0,
      slogoPath: "",
      sname: "",
      snumber: 0,
      sqq: "",
      ssummary: ""
    },
    redisToken:''
    
  },

  titleInput:function(e){
    var that = this
    var sname = "sData.sname"
    that.setData({
      [sname]: e.detail.value
    })
  },
  sqqInput: function (e) {
    var that = this
    var sqq = "sData.sqq"
    that.setData({
      [sqq]: e.detail.value
    })
  },
  snumberInput: function (e) {
    var that = this
    var snumber = "sData.snumber"
    that.setData({
      [snumber]: e.detail.value
    })
  },
  ssummaryInput: function (e) {
    var that = this
    var ssummary = "sData.ssummary"
    that.setData({
      [ssummary]: e.detail.value
    })
  },
  uplado:function(){
    var that = this;
    if (that.data.sData.sname != "" && that.data.sData.saddress != "" && that.data.sData.slogoPath != "" && that.data.sData.sqq != "" && that.data.sData.ssummary != "" && that.data.sData.snumber != 0 ){
      wx.request({
        url: app.globalData.requestUrl + "addStudio",
        data: that.data.sData,
        header: {
          'content-type': 'application/json',
          "token":that.data.redisToken
        },
        method: 'POST',
        dataType: 'json',
        success: function(res) {
          console.log(res)
          if (res.data.statusCode !="200200"){
            wx.showModal({
              title: '召唤师',
              content: '上传错误',
              cancelText: '确定',
              confirmText: '取消',
            })
          }
          wx.showToast({
            title: '上传成功',
            duration: 1000
          })
          wx.reLaunch({
            url: '../dataList/dataList'
          })
        },
        fail: function(res) {},
        complete: function(res) {},
      })
    }else{
      wx.showModal({
        title: '召唤师',
        content: '信息未填写完整',
        cancelText: '确定',
        confirmText: '取消',
      })
    }
  },
  RegionChange: function(e) {
    var saddress = "sData.saddress"
    this.setData({
      region: e.detail.value,
      [saddress]: e.detail.value[0] + e.detail.value[1] + e.detail.value[2]
    })
    console.log(this.data.sData.saddress)
  },
  ViewImage(e) {
    wx.previewImage({
      urls: this.data.imgList,
      current: e.currentTarget.dataset.url
    });
  },
  DelImg(e) {
    wx.showModal({
      title: '召唤师',
      content: '确定要删除这段回忆吗？',
      cancelText: '再看看',
      confirmText: '再见',
      success: res => {
        if (res.confirm) {
          this.data.imgList.splice(e.currentTarget.dataset.index, 1);
          this.setData({
            imgList: this.data.imgList,
            lodingSt: "0%",
            loading: false
          })
        }
      }
    })
  },
  ChooseImage() {
    var that = this;
    wx.chooseImage({
      count: 1, //默认9
      sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album'], //从相册选择
      success: (res) => {
        // console.log(res)
          that.setData({
            imgList: res.tempFilePaths
          })
        that.getToken(that.data.imgList[0])
      }
    });
  },

  //获取上传token
  getToken: function (filePath) {
    var that = this
    var rUrl = app.globalData.requestUrl + "getToken"
    wx.request({
      url: rUrl,
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        wx.hideLoading();
        that.updateImg(filePath, res.data.uptoken);
      },
      fail: function (res) {
        wx.hideLoading();
      }
    })
  },
  //上传图片到服务器
  updateImg: function (filePath, uptoken) {
    var slogoPath = "sData.slogoPath"
    const qiniuUploader = require("../../../utils/qiniuUploader")
    //随机生成文件名
    var prname = util.guid2()
    var that = this
    //上传图片
    qiniuUploader.upload(filePath, (res) => {
      if (res.imageURL == ""){
        that.setData({
          imgList:''
        })
      }else{
        that.setData({
          [slogoPath]: res.imageURL,
          servletLogoPath: res.imageURL,
          lodingSt: "100%",
          loading:true
        })
      }
    }, (error) => {
      console.log('error: ' + error);
    }, {
        key: prname,
        region: 'ECN',
        uptoken: uptoken,
        uploadURL: 'https://upload.qiniup.com',
        domain: 'https://image.xiaoandx.club/'
      });

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    wx.request({
      url: app.globalData.requestUrl + "redisToken",
      method: 'GET',
      dataType: 'json',
      success: function (res) {
        that.setData({
          redisToken: res.data
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})