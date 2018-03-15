package com.wenping.kotlinhtml5demo

import android.content.Context
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.net.URL

/**
 * @author WenPing
 * @date 2018/3/15
 * @decription:
 *<p>
 */
class JavaScriptMethods {
    private var mContext: Context?= null
    private var mWebView:WebView? = null

    constructor(mContext: Context,webView: WebView){
        this.mContext = mContext
        this.mWebView = webView
    }

    @JavascriptInterface
    protected fun showToast(json:String) {
       mContext?.let {
           it.toast(json)
       }
    }

    /**
     * callBack 步骤:
     * 1.js代码中调用本对象中的getData方法,
     * 2.在子线程中获取具体数据,返回主线程在主线程中把数据回传给js页面
     */
    @JavascriptInterface
    fun getData(json:String) {
        //解析
        var jsJson  = JSONObject(json)
        val callBack = jsJson.optString("callback")
        //println("获取数据")

        //在子线程中请求服务器的数据
        doAsync {
            var url = URL("http://")//这里需要有对应json的url
            val result = url.readText()
            println("获取数据=$result")

            //第二步把获得的数据回传给js;callBack步骤
            //调用js代码必须在主线程中
            callbackJavaScript(callBack, result)
        }
    }

    /**
     * 统一管理所有kotlin回调
     */
    private fun callbackJavaScript(callBack: String?, result: String) {
        mContext?.let {
            it.runOnUiThread {
                mWebView?.let {
                    it.loadUrl("javascript:" + callBack + "(" + result + ")")
                }
            }
        }
    }
}
