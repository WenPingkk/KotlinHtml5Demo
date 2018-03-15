package com.wenping.kotlinhtml5demo

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast
import org.jetbrains.anko.toast

/**
 * @author WenPing
 * @date 2018/3/15
 * @decription:
 *<p>
 */
class JavaScriptMethods {
    private var mContext: Context?= null

    constructor(mContext: Context){
        this.mContext = mContext
    }
    @JavascriptInterface
    protected fun showToast(json:String) {
       mContext?.let {
           it.toast(json)
       }
    }
}