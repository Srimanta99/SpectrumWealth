package com.spectrumwealth.model

data class SubCategoryApiResponseModel(val status : String, val msg : String, val subcategory : ArrayList<SubCategoryItem>) {

    data class SubCategoryItem(val id : String, val category : String,  var subcategory: String, var isselect : Boolean)
}
