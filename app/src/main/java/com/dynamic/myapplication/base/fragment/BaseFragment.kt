package com.dynamic.myapplication.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.dynamic.myapplication.callback.CallBackFragment

import android.widget.TextView
import com.dynamic.myapplication.R
import com.dynamic.myapplication.base.activity.BaseActivity


/**
 *  @version ：
 *  @description ：所有的基类fragment
 *  @data ：2022/8/27
 *  @auther ：Zengxiaoping
 */
abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {
    protected lateinit var mDataBinding: DB
    protected lateinit var mContext: Context
    protected lateinit var mViewModel: ViewModel
    protected lateinit var mCallBackFragment: CallBackFragment

    protected var mActivity: FragmentActivity? = null
    protected var mRootView: View? = null

    override fun onAttach(@Nullable context: Context) {
        super.onAttach(context)
        this.mContext = context
        if (context is CallBackFragment) {
            mCallBackFragment = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding = initDataBinding(inflater, layoutId(), container)
        mRootView = mDataBinding.root
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.mActivity = activity
        bindData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDataBinding.unbind()
    }

    protected fun <T : ViewModel> initViewModel(modelClass: Class<T>): T = ViewModelProviders.of(this).get(modelClass)

    private fun initDataBinding(
        inflater: LayoutInflater, @LayoutRes layoutId: Int,
        container: ViewGroup?
    ): DB = DataBindingUtil.inflate(inflater, layoutId, container, false)

    /**
     * 绑定布局
     */
    abstract fun layoutId(): Int

    /**
     * 绑定数据
     */
    abstract fun bindData()

    /**
     * 显示dialog
     */
    protected fun showLoading() {
        if (mActivity != null && mActivity is BaseActivity<*>) {
            (mActivity as BaseActivity<*>).showLoading()
        }
    }

    /**
     * 隐藏dialog
     */
    protected fun hideLoading() {
        if (mActivity != null && mActivity is BaseActivity<*>) {
            (mActivity as BaseActivity<*>).hideLoading()
        }
    }

    /**
     * 数据为空
     */
    protected fun showEmptyLayout(normalView: View, emptyView: TextView, block: () -> Unit) {
        normalView.visibility = View.GONE
        emptyView.visibility = View.VISIBLE
        emptyView.text = activity?.resources?.getString(R.string.request_data_empty)
        emptyView.setOnClickListener {
            normalView.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
            block()
        }
    }

    /**
     * 数据异常
     */
    protected fun showErrorLayout(normalView: View, errorView: TextView, block: () -> Unit) {
        normalView.visibility = View.GONE
        errorView.visibility = View.VISIBLE
        errorView.text = activity?.resources?.getString(R.string.request_data_error)
        errorView.setOnClickListener {
            normalView.visibility = View.VISIBLE
            errorView.visibility = View.GONE
            block()
        }
    }
}