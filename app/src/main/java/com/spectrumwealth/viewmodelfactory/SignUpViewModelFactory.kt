package com.spectrumwealth.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spectrumwealth.databinding.ActivitySignUpBinding
import com.spectrumwealth.ui.SignUpActivity
import com.spectrumwealth.viewmodel.SignUpActivityViewModel

class SignUpViewModelFactory(
    val signUpActivity: SignUpActivity,
    val activitySignUpBinding: ActivitySignUpBinding, ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SignUpActivityViewModel::class.java)){
            return SignUpActivityViewModel(signUpActivity, activitySignUpBinding) as T
        }
        throw java.lang.IllegalArgumentException("error")
    }

}