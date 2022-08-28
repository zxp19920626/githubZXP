package com.dynamic.myapplication.base.model

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @version ：
 * @description ：ViewModel基类，管理rxJava发出的请求，ViewModel销毁同时也取消请求
 * @data ：2022/8/27
 * @auther ：Zengxiaoping
 */
abstract class BaseViewModel : ViewModel() {
    /**
     * 管理RxJava请求
     */
    private var compositeDisposable: CompositeDisposable? = null

    /**
     * 添加 rxJava 发出的请求
     */
    protected fun addDisposable(disposable: Disposable?) {
        if (compositeDisposable == null || compositeDisposable!!.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(disposable!!)
    }

    /**
     * ViewModel销毁同时也取消请求
     */
    override fun onCleared() {
        super.onCleared()
        if (compositeDisposable != null) {
            compositeDisposable!!.dispose()
            compositeDisposable = null
        }
    }
}