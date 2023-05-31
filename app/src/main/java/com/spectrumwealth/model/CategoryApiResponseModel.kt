package com.spectrumwealth.model

data class CategoryApiResponseModel(val status : String, val msg : String , val category : ArrayList<CategoryItem>) {

    data class CategoryItem(val id : String, val category : String, var isselect : Boolean)
}
