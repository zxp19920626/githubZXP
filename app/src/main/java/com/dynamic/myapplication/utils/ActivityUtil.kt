package com.dynamic.myapplication.utils

import android.app.Activity

import java.util.Stack

/**
 * @version ：
 * @description ：管理所有Activity的实例
 * @data ：2022/8/27
 * @auther ：Zengxiaoping
 */
class ActivityUtil {

    /**
     * 添加Activity
     */
    @Synchronized
    fun addActivity(activity: Activity) {
        stack.add(activity)
    }

    /**
     * 移除Activity
     */
    @Synchronized
    fun removeActivity(activity: Activity) {
        stack.remove(activity)
    }

    /**
     * 结束指定类名的Activity
     */
    @Synchronized
    fun finishActivity(cls: Class<*>) {
        for (activity in stack) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
                return
            }
        }
    }

    /**
     * 结束指定的Activity
     */
    @Synchronized
    fun finishActivity(activity: Activity?) {
        if (activity != null) {
            activity.finish()
            stack.remove(activity)
        }
    }

    /**
     * 是否存在Activity
     */
    @Synchronized
    fun containsActivity(cls: Class<*>): Boolean {
        for (activity in stack) {
            if (activity.javaClass == cls) {
                return true
            }
        }
        return false
    }

    /**
     * 结束所有Activity
     */
    @Synchronized
    fun finishAllActivity() {
        for (activity in stack) {
            activity?.finish()
        }
        stack.clear()
    }

    companion object {

        @Volatile
        private lateinit var stack: Stack<Activity>
        @Volatile
        private lateinit var manager: ActivityUtil

        /**
         * 获取实例
         */
        val instance: ActivityUtil
            @Synchronized get() {
                synchronized(ActivityUtil::class.java) {
                    manager = ActivityUtil()
                    stack = Stack()
                }
                return manager
            }
    }
}
