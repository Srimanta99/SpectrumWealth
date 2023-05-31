package com.spectrumwealth.viewmodel

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.dazzlebloom.retrofit.ApiInterface
import com.dazzlebloom.retrofit.RetrofitHelper
import com.dazzlebloom.utiles.sheardpreference.AppSheardPreference
import com.spectrumwealth.databinding.ActivityMainBinding
import com.spectrumwealth.model.ServiceResponseModel
import com.spectrumwealth.ui.MainActivity
import com.spectrumwealth.ui.WelcomeActivity
import com.spectrumwealth.utils.ViewUtils

class MainActivityViewModel(val mainActivity: MainActivity, val activityMainBinding: ActivityMainBinding) : ViewModel() {

    var serviceList : ArrayList<ServiceResponseModel.ServiceItem> = ArrayList();
    var itemMutableList: MutableLiveData<ArrayList<ServiceResponseModel.ServiceItem>> = MutableLiveData()
   // var mainservicelist : ArrayList<ServiceResponseModel.ServiceItem> = ArrayList()

    fun logout(){
        AppSheardPreference.clearSheardpreference(mainActivity)
        val welcomeActivity  = Intent(mainActivity, WelcomeActivity::class.java)
        welcomeActivity.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        mainActivity.startActivity(welcomeActivity)
        mainActivity.finish()
    }


    fun callApiforServiceAccordingCatSubCat( cat: String, subcat : String ){
        if(ViewUtils.isNetworkAvailable(mainActivity)) {
            ViewUtils.showDialog(mainActivity)
            var retrofit = RetrofitHelper.getInstance()
            var apiInterface = retrofit.create(ApiInterface::class.java)
            mainActivity.lifecycleScope.launchWhenCreated {
                val response = apiInterface.getlistingCatSUbcat("filter", cat, subcat)
                try {
                    ViewUtils.dismissDialog()
                    if (response.isSuccessful ){
                        if (response.body()?.status.equals("1")){
                        serviceList = response.body()?.category!!
                            mainActivity.servicelist.clear()
                            mainActivity.mainservicelist = serviceList
                            itemMutableList.value = serviceList
                        //if (serviceList.size>0) {
                           // mainActivity.servicelist.clear()
                            mainActivity.serviceListItemAdapter?.notifyDataSetChanged()


                        //}
                    }else
                            mainActivity.runOnUiThread {
                                ViewUtils.showdialog(mainActivity, "No Service Found. Try using another category")

                            }
                    }
                }catch (e: Exception){
                    ViewUtils.dismissDialog()
                    e.printStackTrace()

                }
            }

        }

    }


     fun callApiforServiceList() {
        if(ViewUtils.isNetworkAvailable(mainActivity)) {
            ViewUtils.showDialog(mainActivity)
            var retrofit = RetrofitHelper.getInstance()
            var apiInterface = retrofit.create(ApiInterface::class.java)
            mainActivity.lifecycleScope.launchWhenCreated {
                val response = apiInterface.getlisting("home")
                try {
                    ViewUtils.dismissDialog()
                    if (response.isSuccessful ){
                        serviceList = response.body()?.category!!
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

