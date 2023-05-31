package com.spectrumwealth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.dazzlebloom.retrofit.ApiInterface
import com.dazzlebloom.retrofit.RetrofitHelper

import com.spectrumwealth.databinding.ActivitySubCategoryViewMoreBinding

import com.spectrumwealth.model.ServicesBycategoryResponseModel

import com.spectrumwealth.ui.SubCategoryViewMoreActivity
import com.spectrumwealth.utils.ViewUtils

class SubCategoryViewMoreViewModel(
    val viewMoreActivity: SubCategoryViewMoreActivity,
    val activitySeeMoreBinding: ActivitySubCategoryViewMoreBinding) : ViewModel() {
    var serviceList : ArrayList<ServicesBycategoryResponseModel.ServiceList> = ArrayList();
    var itemMutableList: MutableLiveData<ArrayList<ServicesBycategoryResponseModel.ServiceList>> = MutableLiveData()
    init {
    }

    fun back(){
        viewMoreActivity.finish()
    }

    fun callApiforServiceList() {
        if(ViewUtils.isNetworkAvailable(viewMoreActivity)) {
            ViewUtils.showDialog(viewMoreActivity)
            var retrofit = RetrofitHelper.getInstance()
            var apiInterface = retrofit.create(ApiInterface::class.java)
            viewMoreActivity.lifecycleScope.launchWhenCreated {
                val response = apiInterface.getSubCategoryItemList("servicebysubcategory", viewMoreActivity.id)
                try {
                    ViewUtils.dismissDialog()
                    if (response.isSuccessful ){
                        serviceList = response.body()?.servicelist!!
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