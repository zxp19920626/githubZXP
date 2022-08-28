package com.dynamic.myapplication.activity.tab

import android.content.Context
import com.dynamic.myapplication.R
import com.dynamic.myapplication.activity.tab.fragment.FollowFragment
import com.dynamic.myapplication.activity.tab.fragment.DiscoveryFragment
import com.dynamic.myapplication.activity.tab.fragment.HomeFragment
import com.dynamic.myapplication.activity.tab.fragment.MineFragment
import android.view.View
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * @version ：
 * @description ：底部tab数据
 * @data ：2022/8/27
 * @auther ：Zengxiaoping
 */
object TabDataGenerator {
    @JvmField
    val mTabRes = intArrayOf(
        R.drawable.tab_attention_selector,
        R.drawable.tab_discovery_selector,
        R.drawable.tab_home_selector,
        R.drawable.tab_profile_selector
    )

    @JvmField
    val mTabResPressed = intArrayOf(
        R.drawable.ic_tab_strip_icon_pgc_selected,
        R.drawable.ic_tab_strip_icon_category_selected,
        R.drawable.ic_tab_strip_icon_feed_selected,
        R.drawable.ic_tab_strip_icon_profile_selected
    )

    private val mTabTitle = arrayOf("关注", "发现", "首页", "我的")

    @JvmStatic
    fun getFragments(): Array<Fragment?> {
        val fragments = arrayOfNulls<Fragment>(4)
        fragments[0] = FollowFragment.newInstance()
        fragments[1] = DiscoveryFragment.newInstance()
        fragments[2] = HomeFragment.newInstance()
        fragments[3] = MineFragment.newInstance()
        return fragments
    }

    /**
     * 获取Tab 显示的内容
     *
     * @param context  上下文
     * @param position 位置
     * @return 视图
     */
    @JvmStatic
    fun getTabView(context: Context?, position: Int): View {
        val view = LayoutInflater.from(context).inflate(R.layout.home_tab_content, null)
        val tabIcon = view.findViewById<View>(R.id.tab_content_image) as ImageView
        tabIcon.setImageResource(mTabRes[position])
        val tabText = view.findViewById<View>(R.id.tab_content_text) as TextView
        tabText.text = mTabTitle[position]
        return view
    }
}