package com.spectrumwealth.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spectrumwealth.databinding.ActivitySeeMoreBinding
import com.spectrumwealth.ui.SeeMoreActivity
import com.spectrumwealth.viewmodel.SeeMoreActivityViewModel


class SeeMoreViewModelFactory(
    val seeMoreActivity : SeeMoreActivity,
    val activitySeeMoreBinding: ActivitySeeMoreBinding) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SeeMoreActivityViewModel::class.java)){
            return SeeMoreActivityViewModel(seeMoreActivity, activitySeeMoreBinding) as T
        }
        throw java.lang.IllegalArgumentException("error")
    }
}