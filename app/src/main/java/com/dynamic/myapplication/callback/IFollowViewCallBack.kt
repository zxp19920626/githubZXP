package com.dynamic.myapplication.callback

interface IFollowViewCallBack {
    //请求数据异常
    fun requestError()
    //请求数据为空
    fun requestNoData()
}