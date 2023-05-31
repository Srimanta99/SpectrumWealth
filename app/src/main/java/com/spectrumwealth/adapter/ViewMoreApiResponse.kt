package com.spectrumwealth.adapter

data class ViewMoreApiResponse(val status : String,val msg: String, val categoryname : String,  val subcategory: ArrayList<SubCategory>) {
    data class SubCategory(val id : String, val subcategory : String , val services : ArrayList<Services>)

    data class  Services(val id : String, val subcategory : String, val name : String, val photo : String )
}