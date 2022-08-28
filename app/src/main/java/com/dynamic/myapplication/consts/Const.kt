package com.dynamic.myapplication.consts

import androidx.databinding.ktx.BuildConfig


/**
 * 常量池
 */
class Const {

    /**
     * 版本
     */
    class InnerBuildConfig {
        companion object {
            var isDebug = BuildConfig.DEBUG
        }
    }

    /***
     * Api地址
     */
    class InnerHttp {
        companion object {
            var BASE_URL =
                if (InnerBuildConfig.isDebug) AppAddress.BASE_URL_DEBUG     //测试环境
                else AppAddress.BASE_URL_RELEASE                            //正式环境
        }
    }

    /**
     * http地址
     */
    class AppAddress {
        companion object {
            const val BASE_URL_DEBUG = "https://api.github.com/"     //测试环境
            const val BASE_URL_RELEASE = "https://api.github.com/"   //正式环境
        }
    }
}