package com.spectrumwealth.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dazzlebloom.utiles.customtypeface.CustomTypeface
import com.spectrumwealth.R
import com.spectrumwealth.`interface`.OnItemClickInterface
import com.spectrumwealth.adapter.ServiceListItemAdapter
import com.spectrumwealth.adapter.SubCategoryParentItem
import com.spectrumwealth.adapter.ViewMoreApiResponse
import com.spectrumwealth.databinding.ActivitySeeMoreBinding
import com.spectrumwealth.model.ServiceResponseModel
import com.spectrumwealth.viewmodel.SeeMoreActivityViewModel
import com.spectrumwealth.viewmodelfactory.SeeMoreViewModelFactory

class SeeMoreActivity : AppCompatActivity() , View.OnClickListener{
   lateinit var seeMoreActivityViewModel : SeeMoreActivityViewModel
    var servicelist : ArrayList<ViewMoreApiResponse.SubCategory> = ArrayList()
    lateinit var reccategory : RecyclerView

    var id=""
    var serviceListItemAdapter : ServiceListItemAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var activitySeemorebinding : ActivitySeeMoreBinding = DataBindingUtil.setContentView(this,R.layout.activity_see_more )
        setContentView(activitySeemorebinding.root)
         seeMoreActivityViewModel = ViewModelProvider(this, SeeMoreViewModelFactory(this, activitySeemorebinding)).get(SeeMoreActivityViewModel::class.java)
        activitySeemorebinding.imgback.setOnClickListener(this)
        activitySeemorebinding.servicename.text = intent.getStringExtra("catname")!!
        activitySeemorebinding.tvServicenmae.text = "SERVICES IN "+ intent.getStringExtra("catname")!!
        activitySeemorebinding.tvServicenmae.typeface = CustomTypeface(this).openSansBold
        activitySeemorebinding.servicename.typeface = CustomTypeface(this).openSansBold
        id = intent.getStringExtra("id")!!
        seeMoreActivityViewModel.callApiforServiceList()
       // val layoutManager = GridLayoutManager(this, 2)
        val layoutManager = LinearLayoutManager(this)
        activitySeemorebinding.recCategorywiseservice.layoutManager = layoutManager

        seeMoreActivityViewModel.itemMutableList.observe(this, Observer {
             servicelist = it
           /* serviceListItemAdapter = ServiceListItemAdapter(this, servicelist, object : OnItemClickInterface{
                override fun onItemClick(serviceItemData: ServiceResponseModel.ServiceItem) {
                    val webViewActivity = Intent(this@SeeMoreActivity, ServiceWebViewActivity::class.java)
                    webViewActivity.putExtra("serviceurl",serviceItemData.url)
                    webViewActivity.putExtra("servicename", serviceItemData.name)
                    startActivity(webViewActivity)
                }
            })*/

            val serviceParentItem = SubCategoryParentItem(this,servicelist, object  : OnItemClickInterface{
                override fun onItemClick(serviceItemData: ServiceResponseModel.ServiceItem) {

                }
            })

            activitySeemorebinding.recCategorywiseservice.adapter = serviceParentItem

        })
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.imgback->{
                seeMoreActivityViewModel.back()
            }
        }
    }
}