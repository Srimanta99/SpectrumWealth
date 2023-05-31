package com.spectrumwealth.model

data class ServicesBycategoryResponseModel(val status : String, val msg: String, val servicelist: ArrayList<ServiceList> ){
    data class ServiceList(val id: String, val category : String, val categoryname : String,  val subcategory :String,  val subcategoryname : String, val name :String, val url :String,  val photo: String)
}
