package com.wenping.kotlinhtml5demo

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup

/**
 * @author WenPing
 * @date 2018/3/15
 * @decription:底部弹出的dialog
 * alt+insert ->生成构造方法
 *<p>
 */
class BottomUpDialog:Dialog{

    constructor(context: Context?) : this(context,0)
    constructor(context: Context?, themeResId: Int) : super(context, R.style.btm_dialog){
        //传入布局
//        setContentView(R.layout.dialog_layout)
        //设置显示位置
        window.setGravity(Gravity.BOTTOM)
        //设置大小,宽度和高度
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}