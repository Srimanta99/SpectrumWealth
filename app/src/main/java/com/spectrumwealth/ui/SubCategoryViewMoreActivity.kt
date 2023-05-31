package com.spectrumwealth.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.spectrumwealth.R
import com.spectrumwealth.`interface`.OnItemClickInterface
import com.spectrumwealth.adapter.ServiceListItemAdapter
import com.spectrumwealth.databinding.ActivitySubCategoryViewMoreBinding
import com.spectrumwealth.model.ServiceResponseModel
import com.spectrumwealth.model.ServicesBycategoryResponseModel
import com.spectrumwealth.viewmodel.SubCategoryViewMoreViewModel
import com.spectrumwealth.viewmodelfactory.SubCatViewMoreViewModelFactory

class SubCategoryViewMoreActivity : AppCompatActivity() {
    var id =""
    lateinit var subCategoryViewMoreViewModel : SubCategoryViewMoreViewModel
    // var serviceListItemAdapter : ServiceListItemAdapter
    var servicelist : ArrayList<ServicesBycategoryResponseModel.ServiceList> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activitySubCategoryViewMoreBinding : ActivitySubCategoryViewMoreBinding= DataBindingUtil.setContentView(this,R.layout.activity_sub_category_view_more )
        subCategoryViewMoreViewModel = ViewModelProvider(this, SubCatViewMoreViewModelFactory(this,activitySubCategoryViewMoreBinding)).get(SubCategoryViewMoreViewModel::class.java)
        id = intent.getStringExtra("id")!!
        subCategoryViewMoreViewModel.callApiforServiceList()

        subCategoryViewMoreViewModel.itemMutableList.observe(this, Observer {
             servicelist = it
           val serviceListItemAdapter  = ServiceListItemAdapter(this, servicelist, object : OnItemClickInterface{
                override fun onItemClick(serviceItemData: ServiceResponseModel.ServiceItem) {
                    val webViewActivity = Intent(this@SubCategoryViewMoreActivity, ServiceWebViewActivity::class.java)
                    webViewActivity.putExtra("serviceurl",serviceItemData.url)
                    webViewActivity.putExtra("servicename", serviceItemData.name)
                    startActivity(webViewActivity)
                }
            })
            val layoutManager = GridLayoutManager(this, 3)
            activitySubCategoryViewMoreBinding.recSubcat.layoutManager = layoutManager
            activitySubCategoryViewMoreBinding.recSubcat.adapter = serviceListItemAdapter
        })

        activitySubCategoryViewMoreBinding.imgback.setOnClickListener {
            subCategoryViewMoreViewModel.back()
        }



    }
}