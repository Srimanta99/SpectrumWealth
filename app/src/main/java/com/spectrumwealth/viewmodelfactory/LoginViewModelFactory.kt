package com.spectrumwealth.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spectrumwealth.databinding.ActivityLoginBinding
import com.spectrumwealth.ui.LogInActivity
import com.spectrumwealth.viewmodel.LogInActivityViewModel


class LoginViewModelFactory(
    val logInActivity: LogInActivity,
    val activityLoginViewBinding: ActivityLoginBinding) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(LogInActivityViewModel::class.java)){
            return LogInActivityViewModel(logInActivity, activityLoginViewBinding) as T
        }
        throw java.lang.IllegalArgumentException("error")
    }
}