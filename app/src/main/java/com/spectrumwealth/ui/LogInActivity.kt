package com.spectrumwealth.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dazzlebloom.utiles.customtypeface.CustomTypeface
import com.spectrumwealth.R
import com.spectrumwealth.databinding.ActivityLoginBinding
import com.spectrumwealth.utils.ViewUtils

import com.spectrumwealth.viewmodel.LogInActivityViewModel
import com.spectrumwealth.viewmodelfactory.LoginViewModelFactory

class LogInActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var logInActivityViewModel : LogInActivityViewModel
    var activityLoginViewBinding : ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          activityLoginViewBinding  = DataBindingUtil.setContentView(this, R.layout.activity_login)
         setContentView(activityLoginViewBinding?.root)
         logInActivityViewModel = ViewModelProvider(this, LoginViewModelFactory(this,activityLoginViewBinding!!)).get(LogInActivityViewModel::class.java)
         activityLoginViewBinding?.imgBack?.setOnClickListener(this)
         activityLoginViewBinding?.btnlogin?.setOnClickListener(this)
        activityLoginViewBinding?.tvForgotpassword?.setOnClickListener(this)
        settypeface()
    }

    private fun settypeface() {
        activityLoginViewBinding?.tvForgotpassword?.typeface = CustomTypeface(this).openSansSemiBold
        activityLoginViewBinding?.nameEditText?.typeface = CustomTypeface(this).openSansSemiBold
        activityLoginViewBinding?.passEditText?.typeface = CustomTypeface(this).openSansSemiBold
        activityLoginViewBinding?.tvLogin?.typeface = CustomTypeface(this).openSansBold
        activityLoginViewBinding?. btnlogin?.typeface = CustomTypeface(this).openSansBold

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.img_back->{
                logInActivityViewModel.back()
            }
            R.id.tv_forgotpassword ->{
                ViewUtils.showdialog(this, "Under Development")
            }
            R.id.btnlogin->{

                if(logInActivityViewModel.loginValidation()){
                    logInActivityViewModel.callLoginApi()
                }
            }
        }
    }
}