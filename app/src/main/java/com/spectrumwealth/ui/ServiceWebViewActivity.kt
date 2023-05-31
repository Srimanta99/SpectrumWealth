package com.spectrumwealth.ui

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.spectrumwealth.R
import com.spectrumwealth.databinding.ActivityServiceWebViewBinding
import com.spectrumwealth.utils.ViewUtils

class ServiceWebViewActivity : AppCompatActivity() {
    var serviceWebViewBinding : ActivityServiceWebViewBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.getStringExtra("serviceurl")
        val servicename = intent.getStringExtra("servicename")
        serviceWebViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_service_web_view)

        serviceWebViewBinding?.servicename?.text = servicename
        serviceWebViewBinding?.imagBack?.setOnClickListener {
            finish()
        }
        serviceWebViewBinding?.webView?.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                ViewUtils.showDialog(this@ServiceWebViewActivity)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                ViewUtils.dismissDialog()
            }
        }
        serviceWebViewBinding?.webView?.loadUrl(url!!)
    }
}