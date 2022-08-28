package com.dynamic.myapplication.api

import com.dynamic.myapplication.bean.FollowingBean
import com.dynamic.myapplication.bean.UserInfoBean
import io.reactivex.Observable
import retrofit2.http.*


/**
 * @version ：声明接口
 * @description ：
 * @data ：2022/8/27
 * @auther ：Zengxiaoping
 */
interface ApiService {

    /**
     * 查询个人信息
     */
    @GET("user/{username}")
    fun getUserInfoBean(@Path("username") username: String): Observable<UserInfoBean>

    /**
     * 查询某人的关注列表
     */
    @GET("users/{username}/following")
    fun getFollowingList(@Path("username") username: String): Observable<List<FollowingBean>>
}

