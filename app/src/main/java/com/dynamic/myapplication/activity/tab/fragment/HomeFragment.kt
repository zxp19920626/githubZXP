package com.dynamic.myapplication.activity.tab.fragment

import com.dynamic.myapplication.R
import com.dynamic.myapplication.base.fragment.BaseFragment
import com.dynamic.myapplication.databinding.FragmentTvCenterLayoutBinding

/**
 *  @version ：
 *  @description ：首页
 *  @data ：2022/8/27
 *  @auther ：Zengxiaoping
 */
class HomeFragment : BaseFragment<FragmentTvCenterLayoutBinding>() {

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_tv_center_layout

    override fun bindData() {
        mDataBinding.name = "首页"
    }
}