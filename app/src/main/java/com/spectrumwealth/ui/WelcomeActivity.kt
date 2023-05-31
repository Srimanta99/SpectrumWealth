package com.spectrumwealth.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dazzlebloom.utiles.customtypeface.CustomTypeface
import com.spectrumwealth.R
import com.spectrumwealth.databinding.ActivityWelcomeBinding
import com.spectrumwealth.viewmodel.WelcomeActivityViewModel
import com.spectrumwealth.viewmodelfactory.WelcomeViewModelFactory

class WelcomeActivity : AppCompatActivity(), View.OnClickListener{

    var activityLoginBinding : ActivityWelcomeBinding?= null
    lateinit var welcomeActivityViewModel : WelcomeActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_welcome)
       // setContentView(activityLoginBinding?.root)
        welcomeActivityViewModel  = ViewModelProvider(this, WelcomeViewModelFactory(this)).get(WelcomeActivityViewModel::class.java)
        activityLoginBinding?.btnLogin?.setOnClickListener(this)
        activityLoginBinding?.btnSignup?.setOnClickListener(this)

        settypeface()
    }

    private fun settypeface() {
        activityLoginBinding?.tvWelcome?.typeface = CustomTypeface(this).openSansBold
        activityLoginBinding?.btnLogin?.typeface = CustomTypeface(this).openSansMedium
        activityLoginBinding?.btnSignup?.typeface = CustomTypeface(this).openSansSemiBold
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_login->{
                welcomeActivityViewModel.loginAsMember()
              //  val logInActivity = Intent(this, LogInActivity::class.java)
               // startActivity(logInActivity)
            }
            R.id.btn_signup->{
                welcomeActivityViewModel.signUp()
               // val signUpActivity = Intent(this, SignUpActivity::class.java)
              //  startActivity(signUpActivity)
            }
        }
    }
}