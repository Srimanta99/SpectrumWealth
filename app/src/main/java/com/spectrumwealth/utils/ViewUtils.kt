package com.spectrumwealth.utils

import android.content.Context
import android.view.WindowManager

import android.os.Build
import android.view.Window
import android.app.Activity
import android.app.ProgressDialog
import androidx.appcompat.app.AlertDialog
import org.jsoup.Jsoup

import android.net.ConnectivityManager


class ViewUtils {
    companion object{
        var progressDialog : ProgressDialog? = null
        var customProgressDialog : CustomProgressDialog ?= null
       fun changeStatusBarcolor( context: Activity,  color : Int) {
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
               val window: Window = context.getWindow()
               window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
               window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
               window.statusBarColor = color
           }
       }

        fun showDialog( context: Activity){
              progressDialog = ProgressDialog(context)
             progressDialog?.setCanceledOnTouchOutside(false)
             progressDialog?.show()
          //  customProgressDialog = CustomProgressDialog().getInstance()
           // customProgressDialog?.showProgress(context)
           //  customProgressDialog = CustomProgressDialog(context)
            // customProgressDialog?.showdialo()
        }

        fun dismissDialog(){
           // customProgressDialog?.hideProgress()
            if(progressDialog?.isShowing == true)
                progressDialog?.dismiss()
        }

        fun showdialog( context: Activity,  message : String){
             var alert: AlertDialog? = null
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            alertDialog.setTitle("Spec Finvest")
            alertDialog.setMessage(message)
            alertDialog.setNegativeButton("Ok") { _, _ ->
                    alert?.cancel()
            }
             alert  = alertDialog.create()
            alert.setCanceledOnTouchOutside(false)
            alert.show()
        }

        fun showdialogOkDismiss( context: Activity,  message : String){
            var alert: AlertDialog? = null
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            alertDialog.setTitle("Spec Finvest")
            alertDialog.setMessage(message)
            alertDialog.setNegativeButton("Ok") { _, _ ->
                alert?.cancel()
                context.finish()
            }
            alert  = alertDialog.create()
            alert.setCanceledOnTouchOutside(false)
            alert.show()
        }

        fun html2text(html : String) : String{
            return Jsoup.parse(html).toString()
        }

         fun isNetworkAvailable(activity:Activity): Boolean {
            val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val activeNetworkInfo = connectivityManager?.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }


    }
}