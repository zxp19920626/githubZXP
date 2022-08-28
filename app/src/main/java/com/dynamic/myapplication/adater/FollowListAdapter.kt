package com.dynamic.myapplication.adater

import android.net.Uri
import android.text.TextUtils
import com.dynamic.myapplication.bean.FollowingBean
import com.dynamic.myapplication.R
import com.dynamic.myapplication.base.adapter.BaseDBRVAdapter
import com.dynamic.myapplication.databinding.ItemFollowListBinding
import com.dynamic.myapplication.BR
import com.dynamic.myapplication.base.adapter.BaseDBRVHolder
import com.facebook.drawee.view.SimpleDraweeView

/**
 *  @version ：
 *  @description ：某人关注列表适配器
 *  @data ：2022/8/27
 *  @auther ：Zengxiaoping
 */
class FollowListAdapter : BaseDBRVAdapter<FollowingBean, ItemFollowListBinding>(
    R.layout.item_follow_list,
    BR.followingBean
)