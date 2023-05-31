package com.spectrumwealth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.dazzlebloom.retrofit.ApiInterface
import com.dazzlebloom.retrofit.RetrofitHelper
import com.spectrumwealth.databinding.ActivitySignUpBinding
import com.spectrumwealth.ui.SignUpActivity
import com.spectrumwealth.utils.ViewUtils

class SignUpActivityViewModel(
    val signUpActivity: SignUpActivity,
    val  activitySignUpBinding: ActivitySignUpBinding) : ViewModel() {
       fun back(){
           signUpActivity.finish()
       }

       fun signUp() : Boolean{
        //ViewUtils.showdialog(signUpActivity,"Under Development")
           if (activitySignUpBinding.nameEditText.text.toString().equals("")){
               ViewUtils.showdialog(signUpActivity,"Please enter User name")
               return false
           }
           if (activitySignUpBinding.emil.text.toString().equals("")){
               ViewUtils.showdialog(signUpActivity,"Please enter Email")
               return false
           }
           if (activitySignUpBinding.phoneEditName.text.toString().equals("")){
               ViewUtils.showdialog(signUpActivity,"Please enter Phone")
               return false
           }
           if (activitySignUpBinding.addressEditName.text.toString().equals("")){
               ViewUtils.showdialog(signUpActivity,"Please enter Address")
               return false
           }
           if (activitySignUpBinding.passEditText.text.toString().equals("")){
               ViewUtils.showdialog(signUpActivity,"Please enter Password")
               return false
           }
           if (activitySignUpBinding.conPassEditText.text.toString().equals("")){
               ViewUtils.showdialog(signUpActivity,"Please enter Confirm Password")
               return false
           }
           if (!activitySignUpBinding.passEditText.text.toString().equals(activitySignUpBinding.passEditText.text.toString())){
               ViewUtils.showdialog(signUpActivity,"Password and confirm dose not match")
               return false
           }
           if (activitySignUpBinding.etAge.text.toString().equals("")){
               ViewUtils.showdialog(signUpActivity,"Please enter Age")
               return false
           }
           return true
       }
    fun callSignUpApi(){
        if(ViewUtils.isNetworkAvailable(signUpActivity)) {
            var retrofit = RetrofitHelper.getInstance()
            var apiInterface = retrofit.create(ApiInterface::class.java)
            signUpActivity.lifecycleScope.launchWhenCreated {
                try {
                    ViewUtils.showDialog(signUpActivity)
                    val response = apiInterface.callSignUpApi("register", activitySignUpBinding.emil.text.toString(),
                        activitySignUpBinding.passEditText.text.toString(), activitySignUpBinding?.nameEditText?.text.toString(),
                        activitySignUpBinding.phoneEditName.text.toString(), activitySignUpBinding.addressEditName.text.toString(),
                        activitySignUpBinding.etAge.text.toString(),activitySignUpBinding.conPassEditText.text.toString())
                    ViewUtils.dismissDialog()
                    if(response.isSuccessful){

                            //AppSheardPreference.storeStringAppPreference(logInActivity, AppConstant.USER_NAME, response?.body()?.name!!)
                           // AppSheardPreference.storeStringAppPreference(logInActivity, AppConstant.USER_ID, response?.body()?.id!!)

                           if (response.body()?.status.equals("1")) {
                               response.body()?.msg?.let { ViewUtils.showdialogOkDismiss(signUpActivity, it) }
                              // signUpActivity.finish()
                           }else{
                               response.body()?.msg?.let { ViewUtils.showdialog(signUpActivity, it) }
                           }

                    }else
                        ViewUtils.showdialog(signUpActivity,"Something error, Try again later.")

                }catch (e : Exception)
                {
                    e.printStackTrace()
                }
            }

        }else{
            ViewUtils.showdialog(signUpActivity, "Please Enable your Internet Connection.")
        }
    }
}