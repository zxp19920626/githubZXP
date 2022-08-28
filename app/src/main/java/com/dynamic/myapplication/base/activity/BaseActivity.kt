package com.dynamic.myapplication.base.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.dynamic.myapplication.dialog.CommonLoadingDialog
import com.dynamic.myapplication.utils.ActivityUtil


/**
 *  @version ：
 *  @description ：所有的基类Activity
 *  @data ：2022/8/27
 *  @auther ：Zengxiaoping
 */
abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var mActivity: AppCompatActivity
    protected lateinit var mContext: Context
    protected lateinit var mViewModel: ViewModel
    protected lateinit var mDataBinding: DB
    protected var mCommonLoadingDialog: CommonLoadingDialog? = null

    protected var mIsResume: Boolean = false        //生命周期stop

    override fun onStop() {
        super.onStop()
        mIsResume = false
    }

    override fun onResume() {
        super.onResume()
        mIsResume = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        mContext = this
        ActivityUtil.instance.addActivity(this)
        setContentView(layoutId())
        mDataBinding = initDataBinding(layoutId())
        bindData()
    }

    @SuppressLint("SetTextI18n")
    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityUtil.instance.removeActivity(this)
        mDataBinding.unbind()
        hideLoading()
    }

    private fun initDataBinding(@LayoutRes layoutId: Int): DB =
        DataBindingUtil.setContentView(this, layoutId)

    protected fun <T : ViewModel> initViewModel(modelClass: Class<T>): T =
        ViewModelProviders.of(this).get(modelClass)

    abstract fun layoutId(): Int
    abstract fun bindData()

    /**
     * 显示dialog
     */
    fun showLoading() {
        showLoading("", true)
    }

    /**
     * 显示dialog
     * @params msg 提示内容
     * @params cancelable 是否能边界取消
     */
    fun showLoading(msg: String, cancelable: Boolean) {
        mCommonLoadingDialog = CommonLoadingDialog(mContext, msg)
        mCommonLoadingDialog?.setCancelable(cancelable)
        mCommonLoadingDialog?.setCanceledOnTouchOutside(cancelable)
        mCommonLoadingDialog?.show()
    }

    /**
     * 隐藏dialog
     */
    fun hideLoading() {
        mCommonLoadingDialog?.dismiss()
        mCommonLoadingDialog = null
    }
}