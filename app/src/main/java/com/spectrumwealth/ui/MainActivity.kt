package com.spectrumwealth.ui

import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dazzlebloom.utiles.sheardpreference.AppSheardPreference
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.tasks.Task
import com.spectrumwealth.R
import com.spectrumwealth.`interface`.OnItemClickInterface
import com.spectrumwealth.adapter.ServiceListItemAdapter
import com.spectrumwealth.adapter.ServiceParentItem
import com.spectrumwealth.databinding.ActivityMainBinding
import com.spectrumwealth.model.CategoryApiResponseModel
import com.spectrumwealth.model.ServiceResponseModel
import com.spectrumwealth.utils.AppConstant
import com.spectrumwealth.utils.ViewUtils
import com.spectrumwealth.utils.customdialog.CustomCategoryDialog
import com.spectrumwealth.viewmodel.MainActivityViewModel
import com.spectrumwealth.viewmodelfactory.MainViewModelFactory


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var activityMainBinding : ActivityMainBinding
    lateinit var mainActivityViewModel : MainActivityViewModel
    var serviceListItemAdapter : ServiceListItemAdapter ?= null
    var servicelist : ArrayList<ServiceResponseModel.ServiceItem> = ArrayList()
    var tempservicelist : ArrayList<ServiceResponseModel.ServiceItem> = ArrayList()
    var mainservicelist : ArrayList<ServiceResponseModel.ServiceItem> = ArrayList()
    var categoryList : ArrayList<CategoryApiResponseModel.CategoryItem> = ArrayList()

    var customCategoryDialog : CustomCategoryDialog? = null
    var catSelected =""
    var subSelected =""
    private var installStateUpdatedListener: InstallStateUpdatedListener? = null
    private val FLEXIBLE_APP_UPDATE_REQ_CODE = 123
    private var appUpdateManager: AppUpdateManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setContentView(activityMainBinding.root)
        mainActivityViewModel = ViewModelProvider(this, MainViewModelFactory(this, activityMainBinding)).get(MainActivityViewModel::class.java)
        appUpdateManager = AppUpdateManagerFactory.create(getApplicationContext());

        checkAppStoreVersion()

        val layoutManager = LinearLayoutManager(this@MainActivity)
        activityMainBinding.recService?.layoutManager = layoutManager


        activityMainBinding?.tvHeader?.text = "Hello, \n"  +AppSheardPreference.fetchStringFromAppPreference(AppConstant.USER_NAME)
        activityMainBinding?.imgLogout?.setOnClickListener(this)
       // activityMainBinding?.imgprofile?.setOnClickListener(this)
        activityMainBinding?.btnFilter?.setOnClickListener(this)



       // mainActivityViewModel.callApiforServiceList()
        mainActivityViewModel.itemMutableList.observe(this , Observer{
            if (it.size>0){
                servicelist = it
                 mainservicelist = servicelist
               //  val servicechild = ServiceListItem("https://www.tenacioushub.in/cms/uploads/product/2.png", "GIGUUSD")

               // for(i in 0 until  servicelist.size)
              //  servicelist.get(i).serviceListItem.add(servicechild)
                //tempservicelist =
                //Toast.makeText(this@MainActivity,mainservicelist.size.toString(),Toast.LENGTH_LONG).show()
                /*serviceListItemAdapter = ServiceListItemAdapter(this, servicelist, object : OnItemClickInterface{
                    override fun onItemClick(serviceItemData: ServiceResponseModel.ServiceItem) {
                        val webViewActivity = Intent(this@MainActivity, ServiceWebViewActivity::class.java)
                        webViewActivity.putExtra("serviceurl",serviceItemData.url)
                        webViewActivity.putExtra("servicename", serviceItemData.name)
                        startActivity(webViewActivity)
                    }
                })*/
                val serviceParentItem = ServiceParentItem(this@MainActivity,servicelist, object  : OnItemClickInterface{
                    override fun onItemClick(serviceItemData: ServiceResponseModel.ServiceItem) {


                    }
                })

                activityMainBinding?.recService?.adapter = serviceParentItem
                // serviceListItemAdapter?.notifyDataSetChanged()
            }else{
                ViewUtils.showdialog(this,"No service found")
            }
        })

        mainActivityViewModel.callApiforServiceList()

        activityMainBinding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tempservicelist.clear()
                //  mainservicelist = servicelist
                for ( i in 0 until mainservicelist.size){
                    if (mainservicelist.get(i).category.trim().contains(p0.toString(), true)){
                        tempservicelist.add(mainservicelist.get(i))
                       // Log.d("afterTextChanged:", servicelist.get(i).name)
                        //   Toast.makeText(this@MainActivity,servicelist.get(i).name,Toast.LENGTH_LONG).show()
                    }
                }
               // mainservicelist = servicelist;
                if (tempservicelist.size > 0) {
                   // servicelist.clear()
                   // servicelist.addAll(tempservicelist)
                    //runOnUiThread {
                        //Toast.makeText(this@MainActivity,servicelist.size.toString(),Toast.LENGTH_LONG).show()
                      //  serviceListItemAdapter?.notifyDataSetChanged()
                  //  }

                   /* serviceListItemAdapter = ServiceListItemAdapter(this@MainActivity, tempservicelist, object : OnItemClickInterface{
                        override fun onItemClick(serviceItemData: ServiceResponseModel.ServiceItem) {
                            val webViewActivity = Intent(this@MainActivity, ServiceWebViewActivity::class.java)
                            webViewActivity.putExtra("serviceurl",serviceItemData.url)
                            webViewActivity.putExtra("servicename", serviceItemData.name)
                            startActivity(webViewActivity)
                        }
                    })*/

                   val serviceParentItem = ServiceParentItem(this@MainActivity,tempservicelist, object  : OnItemClickInterface{
                        override fun onItemClick(serviceItemData: ServiceResponseModel.ServiceItem) {

                        }
                    })
                    activityMainBinding?.recService?.adapter = serviceParentItem

                }

                //Toast.makeText(this@MainActivity,mainservicelist.size.toString(),Toast.LENGTH_LONG).show()

            }

            override fun afterTextChanged(p0: Editable?) {
                 // servicelist.clear()
                 // servicelist.addAll(mainservicelist)
               // mainservicelist.clear()
              //  servicelist = mainservicelist
            }

        })

    }

    private fun checkAppStoreVersion() {
        installStateUpdatedListener = InstallStateUpdatedListener { state: InstallState ->
            if (state.installStatus() == InstallStatus.DOWNLOADED) {
                popupSnackBarForCompleteUpdate()
            } else if (state.installStatus() == InstallStatus.INSTALLED) {
                removeInstallStateUpdateListener()
            } else {
                Toast.makeText(
                    applicationContext,
                    "InstallStateUpdatedListener: state: " + state.installStatus(),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        appUpdateManager!!.registerListener(installStateUpdatedListener)
        checkUpdate();
    }

    fun checkUpdate(){
        val appUpdateInfoTask: Task<AppUpdateInfo> = appUpdateManager!!.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() === UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                startUpdateFlow(appUpdateInfo)
            } else if (appUpdateInfo.installStatus() === InstallStatus.DOWNLOADED) {
                popupSnackBarForCompleteUpdate()
            }
        }
    }

    private fun startUpdateFlow(appUpdateInfo: AppUpdateInfo) {
        try {
            appUpdateManager!!.startUpdateFlowForResult(appUpdateInfo,
                AppUpdateType.FLEXIBLE, this, FLEXIBLE_APP_UPDATE_REQ_CODE)
        } catch (e: IntentSender.SendIntentException) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FLEXIBLE_APP_UPDATE_REQ_CODE) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Update canceled by user! Result Code: " + resultCode, Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(),"Update success! Result Code: " + resultCode, Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getApplicationContext(), "Update Failed! Result Code: " + resultCode, Toast.LENGTH_LONG).show();
                checkUpdate();
            }

        }
    }

    private fun popupSnackBarForCompleteUpdate() {
        Snackbar.make(findViewById<View>(android.R.id.content).rootView, "New app is ready!", Snackbar.LENGTH_INDEFINITE)
            .setAction("Install") { view: View? ->
                if (appUpdateManager != null) {
                    appUpdateManager!!.completeUpdate()
                }
            }
            .setActionTextColor(resources.getColor(R.color.blue))
            .show()
    }

    private fun removeInstallStateUpdateListener() {
        if (appUpdateManager != null) {
            appUpdateManager!!.unregisterListener(installStateUpdatedListener)
        }
    }

    override fun onStop() {
        super.onStop()
        removeInstallStateUpdateListener()
    }

    fun callServiceAccService(selectedSubcat: String) {
        mainActivityViewModel.callApiforServiceAccordingCatSubCat(catSelected, selectedSubcat)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
           R.id.img_logout ->{
               mainActivityViewModel.logout()
           }

            R.id.imgprofile ->{
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }

            R.id.btn_filter ->{
                customCategoryDialog = CustomCategoryDialog(this)
                customCategoryDialog!!.show()
                customCategoryDialog!!.setCanceledOnTouchOutside(false)
            }

            /*R.id.tv_seemoreitems ->{
                val intent = Intent(this, SeeMoreActivity::class.java)
                startActivity(intent)
            }*/
        }
    }

    override fun onResume() {
        super.onResume()
        if (customCategoryDialog?.isShowing == true)
            customCategoryDialog?.dismiss()
    }

    override fun onStart() {
        super.onStart()
    }
}