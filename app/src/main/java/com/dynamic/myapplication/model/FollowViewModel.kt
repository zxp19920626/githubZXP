package com.dynamic.myapplication.model

import com.dynamic.myapplication.bean.FollowingBean
import androidx.lifecycle.MutableLiveData
import com.dynamic.myapplication.base.api.Api
import com.dynamic.myapplication.base.model.BaseViewModel
import com.dynamic.myapplication.callback.IFollowViewCallBack
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *  @version ：
 *  @description ：某人的关注页面
 *  @data ：2022/8/27
 *  @auther ：Zengxiaoping
 */
class FollowViewModel : BaseViewModel() {

    //观测数据
    var mFollowListLiveData = MutableLiveData<List<FollowingBean>>()

    /**
     * 获取某人关注用户列表信息
     */
    fun getUserInfoBean(userName: String, iFollowViewCallBack: IFollowViewCallBack) {
        val disposable =
            Api.mInstance.getFollowingList(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isNotEmpty()) {
                        mFollowListLiveData.value = it
                    } else {
                        iFollowViewCallBack.requestNoData()
                    }
                }, {
                    iFollowViewCallBack.requestError()
                })
        addDisposable(disposable)
    }
}