package com.dynamic.myapplication.activity.tab.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dynamic.myapplication.R
import com.dynamic.myapplication.adater.FollowListAdapter
import com.dynamic.myapplication.base.adapter.OnItemClickListener
import com.dynamic.myapplication.base.fragment.BaseFragment
import com.dynamic.myapplication.callback.IFollowViewCallBack
import com.dynamic.myapplication.databinding.FragmentBaseLayoutBinding
import com.dynamic.myapplication.model.FollowViewModel

/**
 *  @version ：
 *  @description ：某人的关注列表页面
 *  @data ：2022/8/27
 *  @auther ：Zengxiaoping
 */
class FollowFragment : BaseFragment<FragmentBaseLayoutBinding>(), IFollowViewCallBack {

    private lateinit var mRcv: RecyclerView

    //数据
    private lateinit var mFollowViewModel: FollowViewModel

    //适配器
    private lateinit var mFollowListAdapter: FollowListAdapter

    companion object {
        fun newInstance(): FollowFragment {
            val fragment = FollowFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun layoutId(): Int = R.layout.fragment_base_layout

    override fun bindData() {
        initData()
        initAdapter()
        initEvent()
        requestData()
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        mFollowViewModel = initViewModel(FollowViewModel::class.java)
        mDataBinding.lifecycleOwner = this
        mRcv = mDataBinding.rcv
    }

    /**
     * 初始化适配器
     */
    private fun initAdapter() {
        mFollowListAdapter = FollowListAdapter()
        val linearLayoutManager = LinearLayoutManager(mContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        mRcv.layoutManager = linearLayoutManager
        mRcv.adapter = mFollowListAdapter
    }

    /**
     * 初始化监听
     */
    private fun initEvent() {
        mFollowViewModel.mFollowListLiveData.observe(this, {
            hideLoading()
            mFollowListAdapter.addData(it)
        })
    }

    /**
     * 请求数据
     */
    private fun requestData() {
        showLoading()
        mFollowViewModel.getUserInfoBean("zxp", this)
    }

    /**
     * 数据请求异常
     */
    override fun requestError() {
        hideLoading()
        showErrorLayout(mDataBinding.rcv, mDataBinding.tvError) {
            requestData()
        }
    }

    /**
     * 数据请求返回为空
     */
    override fun requestNoData() {
        hideLoading()
        showEmptyLayout(mDataBinding.rcv, mDataBinding.tvError) {
            requestData()
        }
    }
}