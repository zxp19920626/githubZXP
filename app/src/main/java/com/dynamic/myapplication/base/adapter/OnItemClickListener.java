package com.dynamic.myapplication.base.adapter;

import android.view.View;

/**
 * @version ：
 * @description ：RecyclerView Item 点击事件
 * @data ：2022/8/27
 * @auther ：Zengxiaoping
 */
public interface OnItemClickListener<Data> {

    /**
     * Item 点击事件
     *
     * @param v        item的视图
     * @param data     item的数据
     * @param position item的下标
     */
    void onItemClick(View v, Data data, int position);
}
