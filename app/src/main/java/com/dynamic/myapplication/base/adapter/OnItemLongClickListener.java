package com.dynamic.myapplication.base.adapter;

import android.view.View;

/**
 * @version ：
 * @description ：RecyclerView Item 长按
 * @data ：2022/8/27
 * @auther ：Zengxiaoping
 */
public interface OnItemLongClickListener<Data> {

    /**
     * Item 长按事件
     *
     * @param v        item的试图
     * @param data     item的数据
     * @param position item的下标
     */
    boolean onItemLongClick(View v, Data data, int position);
}
