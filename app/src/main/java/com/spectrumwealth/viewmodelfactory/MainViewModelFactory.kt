package com.spectrumwealth.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spectrumwealth.databinding.ActivityMainBinding
import com.spectrumwealth.ui.MainActivity
import com.spectrumwealth.viewmodel.MainActivityViewModel


class MainViewModelFactory(val mainActivity: MainActivity,val activityMainBinding: ActivityMainBinding) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(mainActivity, activityMainBinding) as T
        }
        throw java.lang.IllegalArgumentException("error")
    }
}