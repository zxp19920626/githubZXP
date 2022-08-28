package com.dynamic.myapplication.base.api


import com.dynamic.myapplication.api.ApiService
import com.dynamic.myapplication.consts.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


/**
 * @version ：
 * @description ：使用Retrofit基础服务
 * @data ：2022/8/27
 * @auther ：Zengxiaoping
 */
class Api : BaseApi() {

    private val mTime: Long = 10 //设置请求超时时间

    companion object {
        val mInstance: ApiService
            get() = ApiHolder.apiService
    }

    /**
     * 静态内部类单例
     */
    private object ApiHolder {
        private val api = Api()
        val apiService: ApiService = api.initRetrofit(Const.InnerHttp.BASE_URL)
            .create(ApiService::class.java)
    }

    /**
     * 做自己需要的操作
     */
    override fun setClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Content-Type", "application/json")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(mTime, TimeUnit.SECONDS)
            .readTimeout(mTime, TimeUnit.SECONDS)
            .build()
    }
}
