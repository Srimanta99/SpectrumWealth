package com.spectrumwealth.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spectrumwealth.ui.WelcomeActivity
import com.spectrumwealth.viewmodel.WelcomeActivityViewModel

class WelcomeViewModelFactory(private val loginActivity: WelcomeActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(WelcomeActivityViewModel::class.java)){
            return WelcomeActivityViewModel(loginActivity) as T
        }
        throw java.lang.IllegalArgumentException("error")
    }
}