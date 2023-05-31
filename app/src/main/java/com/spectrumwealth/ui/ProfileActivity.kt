package com.spectrumwealth.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.spectrumwealth.R
import com.spectrumwealth.databinding.ActivityProfileBinding
import com.spectrumwealth.viewmodel.ProfileViewViewModel
import com.spectrumwealth.viewmodelfactory.ProfileViewModelfactory

class ProfileActivity : AppCompatActivity() , OnClickListener{
    var activityProfileBinding : ActivityProfileBinding ? =null
    var profileViewViewModel : ProfileViewViewModel ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        profileViewViewModel = ViewModelProvider(this, ProfileViewModelfactory(this, activityProfileBinding!!)).get(ProfileViewViewModel::class.java)
        activityProfileBinding?.imgBack?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
           R.id.img_back ->{
               finish()
           }
        }
    }
}