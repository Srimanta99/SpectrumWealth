package com.spectrumwealth.viewmodel

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import com.spectrumwealth.ui.LogInActivity
import com.spectrumwealth.ui.WelcomeActivity
import com.spectrumwealth.ui.SignUpActivity

class WelcomeActivityViewModel(val loginActivity: WelcomeActivity) : ViewModel() {
    init {
        Log.d("hg","jh")
    }

    fun loginAsMember(){
        val intent  = Intent(loginActivity , LogInActivity::class.java)
        loginActivity.startActivity(intent)
    }

    fun signUp(){
        val signUpActivity = Intent(loginActivity, SignUpActivity::class.java)
          loginActivity.startActivity(signUpActivity)
    }

}