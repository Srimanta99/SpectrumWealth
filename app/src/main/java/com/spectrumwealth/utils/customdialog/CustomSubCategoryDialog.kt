package com.spectrumwealth.utils.customdialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dazzlebloom.retrofit.ApiInterface
import com.dazzlebloom.retrofit.RetrofitHelper
import com.spectrumwealth.R
import com.spectrumwealth.`interface`.OnCategoryItemClickInterface
import com.spectrumwealth.adapter.SubCategoryListItemAdapter
import com.spectrumwealth.model.CategoryApiResponseModel
import com.spectrumwealth.model.SubCategoryApiResponseModel
import com.spectrumwealth.ui.MainActivity
import com.spectrumwealth.utils.ViewUtils

class CustomSubCategoryDialog(val mainActivity: MainActivity, val categoryId : String) : Dialog(mainActivity), View.OnClickListener {


     var recyclerView: RecyclerView? = null
     var mLayoutManager: RecyclerView.LayoutManager? = null
     var subcategoryList : ArrayList<SubCategoryApiResponseModel.SubCategoryItem> = ArrayList()
    var categoryListItemAdapter : SubCategoryListItemAdapter? =null
    var cancel : Button ?= null
    var done : Button?= null
    var mCheckedPostion  : Int = -1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_sub_category_dialog)
        recyclerView = findViewById(R.id.recycler_view)
        done = findViewById(R.id.done)
        cancel = findViewById(R.id.cancel)
        mLayoutManager = LinearLayoutManager(mainActivity)
        recyclerView?.layoutManager = mLayoutManager

        done?.setOnClickListener(this)
        cancel?.setOnClickListener(this)

        callApiForSubCategory()
    }

    private fun callApiForSubCategory() {
        if(ViewUtils.isNetworkAvailable(mainActivity)) {
            var retrofit = RetrofitHelper.getInstance()
            var apiInterface = retrofit.create(ApiInterface::class.java)
            //ViewUtils.showDialog(mainActivity)
            mainActivity.lifecycleScope.launchWhenCreated {
                try {
                   // ViewUtils.showDialog(mainActivity)
                    val response = apiInterface.getSubCategory("subcategoryofcategory",categoryId)
                    ViewUtils.dismissDialog()
                    if (response.isSuccessful){
                        if (response.body()?.subcategory!= null){
                            subcategoryList = response.body()?.subcategory!!
                            categoryListItemAdapter = SubCategoryListItemAdapter(mainActivity, subcategoryList, this@CustomSubCategoryDialog, object : OnCategoryItemClickInterface {
                                override fun onItemClick(categoryitem: CategoryApiResponseModel.CategoryItem, position: Int) {

                                }
                            })
                            recyclerView?.adapter = categoryListItemAdapter
                        }
                    }

                }   catch ( e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    private fun openSubCategorydialog() {

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.done->{
               dismiss()
                 val selectedSubcat = subcategoryList.get(mCheckedPostion).id
                  mainActivity.callServiceAccService(selectedSubcat)
            }
            R.id.cancel ->{
                val  customCategoryDialog = CustomCategoryDialog(mainActivity)
                customCategoryDialog!!.show()
                customCategoryDialog!!.setCanceledOnTouchOutside(false)
                dismiss()
            }
        }
    }
}