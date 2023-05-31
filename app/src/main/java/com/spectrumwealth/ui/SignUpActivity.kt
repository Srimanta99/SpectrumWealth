package com.spectrumwealth.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dazzlebloom.utiles.customtypeface.CustomTypeface
import com.spectrumwealth.R
import com.spectrumwealth.databinding.ActivitySignUpBinding

import com.spectrumwealth.viewmodel.SignUpActivityViewModel
import com.spectrumwealth.viewmodelfactory.SignUpViewModelFactory

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var signUpActivityViewModel : SignUpActivityViewModel
    lateinit var activitySignUpBinding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         activitySignUpBinding  = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        signUpActivityViewModel = ViewModelProvider(this, SignUpViewModelFactory(this,activitySignUpBinding)).get(SignUpActivityViewModel::class.java)
        setContentView(activitySignUpBinding.root)
        activitySignUpBinding.imgBack.setOnClickListener(this)
        activitySignUpBinding.btnsignup.setOnClickListener(this)
        settypeface()
    }

    private fun settypeface() {
      activitySignUpBinding.tvSignup.typeface = CustomTypeface(this).openSansBold
      activitySignUpBinding?.nameEditText?.typeface = CustomTypeface(this).openSansSemiBold
        activitySignUpBinding?.emil?.typeface = CustomTypeface(this).openSansSemiBold
        activitySignUpBinding?.nameEditText?.typeface = CustomTypeface(this).openSansSemiBold
        activitySignUpBinding?.phoneEditName?.typeface = CustomTypeface(this).openSansSemiBold
        activitySignUpBinding?.addressEditName?.typeface = CustomTypeface(this).openSansSemiBold
        activitySignUpBinding?.passEditText?.typeface = CustomTypeface(this).openSansSemiBold
        activitySignUpBinding?.conPassEditText?.typeface = CustomTypeface(this).openSansSemiBold
        activitySignUpBinding?.etAge?.typeface = CustomTypeface(this).openSansSemiBold
        activitySignUpBinding?.btnsignup?.typeface = CustomTypeface(this).openSansBold

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.img_back->{
                signUpActivityViewModel.back()
            }
            R.id.btnsignup->{
                if(signUpActivityViewModel.signUp()){
                      signUpActivityViewModel.callSignUpApi()
                }
            }
        }
    }


}