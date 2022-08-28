package com.dynamic.myapplication

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * @version ：
 * @description ：
 * @data ：2022/8/27
 * @auther ：Zengxiaoping
 */
class GithubApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this

        Fresco.initialize(this)
    }
}
