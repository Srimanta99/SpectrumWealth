package com.spectrumwealth.viewmodel

import androidx.lifecycle.ViewModel
import com.spectrumwealth.databinding.ActivityProfileBinding
import com.spectrumwealth.ui.ProfileActivity

class ProfileViewViewModel(
   val profileActivity: ProfileActivity,
   val activityProfileBinding: ActivityProfileBinding) : ViewModel()
{


}