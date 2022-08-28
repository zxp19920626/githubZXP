package com.dynamic.myapplication.base.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * @description ：Retrfit基类
 * @version ：
 * @data ：2022/8/27
 * @auther ：Zengxiaoping
 */
abstract class BaseApi {

    /**
     * 初始化Retrofit
     */
    fun initRetrofit(baseUrl: String): Retrofit {
        val builder = Retrofit.Builder()
        //支持返回Call<String>
        builder.addConverterFactory(ScalarsConverterFactory.create())
        //支持直接格式化json返回Bean对象
        builder.addConverterFactory(GsonConverterFactory.create(buildGson()))
        //支持RxJava
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        builder.baseUrl(baseUrl)
        val client = setClient()
        if (client != null) {
            builder.client(client)
        }
        return builder.build()
    }

    /**
     * 设置OkHttpClient，添加拦截器等
     *
     * @return 可以返回为null
     */
    protected abstract fun setClient(): OkHttpClient?

    companion object {
        fun buildGson(): Gson {
            return GsonBuilder().serializeNulls().create()
        }
    }
}
