package com.spectrumwealth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.dazzlebloom.retrofit.ApiInterface
import com.dazzlebloom.retrofit.RetrofitHelper
import com.spectrumwealth.adapter.ViewMoreApiResponse
import com.spectrumwealth.databinding.ActivitySeeMoreBinding
import com.spectrumwealth.ui.SeeMoreActivity
import com.spectrumwealth.utils.ViewUtils

class SeeMoreActivityViewModel(
    val seeMoreActivity: SeeMoreActivity,
    val activitySeeMoreBinding: ActivitySeeMoreBinding) : ViewModel() {
    var serviceList : ArrayList<ViewMoreApiResponse.SubCategory> = ArrayList();
    var itemMutableList: MutableLiveData<ArrayList<ViewMoreApiResponse.SubCategory>> = MutableLiveData()
    init {
    }

    fun back(){
        seeMoreActivity.finish()
    }

    fun callApiforServiceList() {
        if(ViewUtils.isNetworkAvailable(seeMoreActivity)) {
            ViewUtils.showDialog(seeMoreActivity)
            var retrofit = RetrofitHelper.getInstance()
            var apiInterface = retrofit.create(ApiInterface::class.java)
            seeMoreActivity.lifecycleScope.launchWhenCreated {
                val response = apiInterface.getlistingbyCategory("viewmore", seeMoreActivity.id)
                try {
                    ViewUtils.dismissDialog()
                    if (response.isSuccessful ){
                        serviceList = response.body()?.subcategory!!
                        itemMutableList.value = serviceList
                    }
                }catch (e: Exception){
                    ViewUtils.dismissDialog()
                    e.printStackTrace()

                }
            }

        }
    }
}