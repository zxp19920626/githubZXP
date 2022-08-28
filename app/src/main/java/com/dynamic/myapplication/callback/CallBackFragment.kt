package com.dynamic.myapplication.callback

import android.os.Bundle
import androidx.annotation.Nullable

/**
 *  @version ：
 *  @description ：Fragment回调Activity
 *  @data ：2022/8/27
 *  @auther ：Zengxiaoping
 */
interface CallBackFragment {
    fun callBack(type: Int, @Nullable bundle: Bundle)
}