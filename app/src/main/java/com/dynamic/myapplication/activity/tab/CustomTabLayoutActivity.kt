package com.dynamic.myapplication.activity.tab

import com.dynamic.myapplication.activity.tab.TabDataGenerator.getFragments
import com.dynamic.myapplication.activity.tab.TabDataGenerator.getTabView
import com.google.android.material.tabs.TabLayout
import com.dynamic.myapplication.R
import android.view.View
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dynamic.myapplication.base.activity.BaseActivity
import com.dynamic.myapplication.databinding.ActivityBottomTabBinding

/**
 * @version ：
 * @description ：底部tab的Activity
 * @data ：2022/8/27
 * @auther ：Zengxiaoping
 */
class CustomTabLayoutActivity : BaseActivity<ActivityBottomTabBinding>() {

    private lateinit var mTabLayout: TabLayout
    private lateinit var mFragmentList: Array<Fragment?>

    override fun layoutId(): Int = R.layout.activity_bottom_tab

    override fun bindData() {
        mFragmentList = getFragments()
        initView()
    }

    private fun initView() {
        mTabLayout = findViewById<View>(R.id.bottom_tab_layout) as TabLayout
        mTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                onTabItemSelected(tab.position)
                for (i in 0 until mTabLayout.tabCount) {
                    val view = mTabLayout.getTabAt(i)!!.customView
                    val icon = view!!.findViewById<View>(R.id.tab_content_image) as ImageView
                    val text = view.findViewById<View>(R.id.tab_content_text) as TextView
                    if (i == tab.position) {
                        icon.setImageResource(TabDataGenerator.mTabResPressed[i])
                        text.setTextColor(resources.getColor(android.R.color.black))
                    } else {
                        icon.setImageResource(TabDataGenerator.mTabRes[i])
                        text.setTextColor(resources.getColor(android.R.color.darker_gray))
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        for (i in 0..3) {
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(getTabView(this, i)))
        }
    }

    private fun onTabItemSelected(position: Int) {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = mFragmentList[0]
            1 -> fragment = mFragmentList[1]
            2 -> fragment = mFragmentList[2]
            3 -> fragment = mFragmentList[3]
        }
        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.home_container, fragment)
                .commit()
        }
    }
}