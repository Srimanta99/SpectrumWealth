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
import com.spectrumwealth.adapter.CategoryListItemAdapter
import com.spectrumwealth.model.CategoryApiResponseModel
import com.spectrumwealth.ui.MainActivity
import com.spectrumwealth.utils.ViewUtils

class CustomCategoryDialog(val mainActivity: MainActivity) : Dialog(mainActivity), View.OnClickListener {


     var recyclerView: RecyclerView? = null
     var mLayoutManager: RecyclerView.LayoutManager? = null
     var categoryList : ArrayList<CategoryApiResponseModel.CategoryItem> = ArrayList()
    var categoryListItemAdapter : CategoryListItemAdapter? =null
    var cancel : Button ?= null
    var done : Button?= null
    var mCheckedPostion  : Int = -1
    var selectedCategoryId : String = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_category_dialog)
        recyclerView = findViewById(R.id.recycler_view)
        done = findViewById(R.id.done)
        cancel = findViewById(R.id.cancel)
        mLayoutManager = LinearLayoutManager(mainActivity)
        recyclerView?.layoutManager = mLayoutManager

        done?.setOnClickListener(this)
        cancel?.setOnClickListener(this)

        if (mainActivity.categoryList.size>0){
            categoryListItemAdapter = CategoryListItemAdapter(mainActivity, mainActivity.categoryList, this@CustomCategoryDialog, object : OnCategoryItemClickInterface {
                override fun onItemClick(categoryitem: CategoryApiResponseModel.CategoryItem, position: Int) {
                    mainActivity.catSelected = mainActivity.categoryList.get(mCheckedPostion).id
                    selectedCategoryId = mainActivity.categoryList.get(mCheckedPostion).id
                    openSubCategorydialog()
                }
            })
            recyclerView?.adapter = categoryListItemAdapter

        }else
          callApiForCategory()
    }

    private fun callApiForCategory() {
        if(ViewUtils.isNetworkAvailable(mainActivity)) {
            var retrofit = RetrofitHelper.getInstance()
            var apiInterface = retrofit.create(ApiInterface::class.java)
           // ViewUtils.showDialog(mainActivity)
            mainActivity.lifecycleScope.launchWhenCreated {
                try {
                   // ViewUtils.showDialog(mainActivity)
                    val response = apiInterface.getCategory("category")

                    if (response.isSuccessful){
                        if (response.body()?.category!= null){
                            mainActivity.categoryList = response.body()?.category!!
                            categoryListItemAdapter = CategoryListItemAdapter(mainActivity, mainActivity.categoryList, this@CustomCategoryDialog, object : OnCategoryItemClickInterface {
                                override fun onItemClick(categoryitem: CategoryApiResponseModel.CategoryItem, position: Int) {
                                    mainActivity.catSelected = mainActivity.categoryList.get(mCheckedPostion).id
                                    selectedCategoryId = mainActivity.categoryList.get(mCheckedPostion).id
                                    openSubCategorydialog()
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
        val subcategoryDialog = CustomSubCategoryDialog(mainActivity,selectedCategoryId)
        subcategoryDialog.setCanceledOnTouchOutside(false)
        subcategoryDialog.show()
        dismiss()

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.done->{
               dismiss()
            }
            R.id.cancel ->{
                dismiss()
            }
        }
    }
}