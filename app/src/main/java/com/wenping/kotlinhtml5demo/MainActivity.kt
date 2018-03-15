package com.wenping.kotlinhtml5demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mWebView: WebView by lazy {
        webview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setWebView()
    }

    var setWebView = {
        //1.开启Kotlin和H5通信的开关
        mWebView.settings.javaScriptEnabled = true
        //2.设置2个WebViewClient
        mWebView.webViewClient = WebViewClientImpl()
        mWebView.webChromeClient = WebChromeClientImpl()

        mWebView.addJavascriptInterface(JavaScriptMethods(this@MainActivity),"jsInterface")//参数1:对象:方法名 ,参数2:字符串:参数1对应对象的别名

        //3.加载网页
        mWebView.loadUrl("http://10.0.3.2:8080/index.html")
    }

    private class WebViewClientImpl : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            //调用h5方法;

        }
    }

    private class WebChromeClientImpl : WebChromeClient() {
        //控制加载进度条
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)

        }
    }
}
