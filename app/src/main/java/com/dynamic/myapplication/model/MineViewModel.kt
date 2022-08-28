package com.dynamic.myapplication.model

import com.dynamic.myapplication.bean.UserInfoBean
import androidx.lifecycle.MutableLiveData
import com.dynamic.myapplication.base.api.Api
import com.dynamic.myapplication.base.model.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *  @version ：
 *  @description ：我的页面
 *  @data ：2022/8/27
 *  @auther ：Zengxiaoping
 */
class MineViewModel : BaseViewModel() {

    var mUserInfoBean = MutableLiveData<UserInfoBean>()

    /**
     * 查询个人用户信息
     */
    fun getUserInfoBean(userName: String) {
        val disposable =
            Api.mInstance.getUserInfoBean(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mUserInfoBean.value = it
                }, {
                    //do nothing
                })
        addDisposable(disposable)
    }

    override fun onCleared() {
        super.onCleared()
    }
}