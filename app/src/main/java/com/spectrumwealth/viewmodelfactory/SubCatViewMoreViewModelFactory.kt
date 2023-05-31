package com.spectrumwealth.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spectrumwealth.databinding.ActivitySubCategoryViewMoreBinding
import com.spectrumwealth.ui.SubCategoryViewMoreActivity
import com.spectrumwealth.viewmodel.SubCategoryViewMoreViewModel


class SubCatViewMoreViewModelFactory(
    val subViewMoreactivity : SubCategoryViewMoreActivity,
    val activitySubCategoryViewMoreBinding: ActivitySubCategoryViewMoreBinding) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SubCategoryViewMoreViewModel::class.java)){
            return SubCategoryViewMoreViewModel(subViewMoreactivity, activitySubCategoryViewMoreBinding) as T
        }
        throw java.lang.IllegalArgumentException("error")
    }
}