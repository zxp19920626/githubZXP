package com.dynamic.myapplication.activity.tab.fragment

import com.dynamic.myapplication.R
import com.dynamic.myapplication.base.fragment.BaseFragment
import com.dynamic.myapplication.databinding.FragmentTvCenterLayoutBinding

/**
 *  @version ：
 *  @description ：我的页面
 *  @data ：2022/8/27
 *  @auther ：Zengxiaoping
 */
class MineFragment : BaseFragment<FragmentTvCenterLayoutBinding>() {

    companion object {
        fun newInstance(): MineFragment = MineFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_tv_center_layout

    override fun bindData() {
        mDataBinding.name = "我的"
    }
}