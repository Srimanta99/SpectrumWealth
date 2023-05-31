package com.spectrumwealth.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spectrumwealth.databinding.ActivityProfileBinding
import com.spectrumwealth.ui.ProfileActivity
import com.spectrumwealth.viewmodel.ProfileViewViewModel

class ProfileViewModelfactory(
    val profileActivity: ProfileActivity,
     val activityProfileBinding: ActivityProfileBinding) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ProfileViewViewModel::class.java)){
            return ProfileViewViewModel(profileActivity, activityProfileBinding) as T
        }
        throw java.lang.IllegalArgumentException("error")
    }
}