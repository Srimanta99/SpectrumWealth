package com.spectrumwealth.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.dazzlebloom.retrofit.ApiInterface
import com.dazzlebloom.retrofit.RetrofitHelper
import com.dazzlebloom.utiles.sheardpreference.AppSheardPreference
import com.spectrumwealth.databinding.ActivityLoginBinding
import com.spectrumwealth.ui.LogInActivity
import com.spectrumwealth.ui.MainActivity
import com.spectrumwealth.utils.AppConstant
import com.spectrumwealth.utils.ViewUtils

class LogInActivityViewModel (
   val logInActivity: LogInActivity,
    val activityLoginViewBinding: ActivityLoginBinding) :ViewModel() {
    init {
    }

    fun back(){
        logInActivity.finish()
    }

    fun loginValidation(): Boolean{
        if (activityLoginViewBinding?.nameEditText?.text.toString().equals("")) {
            ViewUtils.showdialog(logInActivity, "Enter User name")
            return false
        }
        if (activityLoginViewBinding?.passEditText?.text.toString().equals("")){
            ViewUtils.showdialog(logInActivity, "Enter Password")
            return false
        }
        return true

    }

    fun callLoginApi(){
        if(ViewUtils.isNetworkAvailable(logInActivity)) {
            var retrofit = RetrofitHelper.getInstance()
            var apiInterface = retrofit.create(ApiInterface::class.java)
            logInActivity.lifecycleScope.launchWhenCreated {
                try {
                    ViewUtils.showDialog(logInActivity)
                    val response = apiInterface.callLoginApi("login", activityLoginViewBinding.nameEditText.text.toString(),
                        activityLoginViewBinding.passEditText.text.toString())
                    ViewUtils.dismissDialog()
                     if(response.isSuccessful){
                         if(response.body()?.status == 1) {
                             AppSheardPreference.storeStringAppPreference(logInActivity,AppConstant.USER_NAME, response?.body()?.name!!)
                             AppSheardPreference.storeStringAppPreference(logInActivity,AppConstant.USER_ID, response?.body()?.id!!)
                             AppSheardPreference.storeStringAppPreference(logInActivity,AppConstant.EMAIL, response?.body()?.email!!)
                             AppSheardPreference.storeStringAppPreference(logInActivity,AppConstant.PHONE, response?.body()?.phone!!)
                             AppSheardPreference.storeStringAppPreference(logInActivity,AppConstant.ADDRESS, response?.body()?.address!!)
                             AppSheardPreference.storeStringAppPreference(logInActivity,AppConstant.AGE, response?.body()?.age!!)

                             val mainActivity = Intent(logInActivity, MainActivity::class.java)
                             mainActivity.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                             logInActivity.startActivity(mainActivity)
                             logInActivity.finish()
                         }else
                             response.body()?.msg?.let { ViewUtils.showdialog(logInActivity,it) }

                     }else
                         ViewUtils.showdialog(logInActivity,"Something error, Try again later")

                }catch (e : Exception)
                {
                    e.printStackTrace()
                }
            }

        }else{
            ViewUtils.showdialog(logInActivity, "Please Enable your Internet Connection")
        }
    }

}