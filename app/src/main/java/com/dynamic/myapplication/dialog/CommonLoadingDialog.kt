package com.dynamic.myapplication.dialog

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.dynamic.myapplication.R
import com.dynamic.myapplication.base.dialog.BaseDialog

/**
 * @version ：
 * @description : 加载框
 * @data ：2022/8/27
 * @auther ：Zengxiaoping
 */
class CommonLoadingDialog @JvmOverloads constructor(
    context: Context, msg: String = "",
    theme: Int = R.style.LoadingDialog
) : BaseDialog(context, theme) {

    private lateinit var view: View
    private var msgDesc: String? = null

    init {
        msgDesc = msg
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.dialog_common_loading, null)
        view.findViewById<View>(R.id.image)
            ?.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_rotate))
        setContentView(view)
        view.findViewById<TextView>(R.id.tv_msg)?.text = msgDesc
        window?.setGravity(Gravity.TOP)
    }
}
