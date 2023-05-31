package com.spectrumwealth.model

data class ServiceResponseModel(val status : String, val msg: String, val category: ArrayList<ServiceItem> ) {
    data class ServiceItem(val id: String, val category : String, val categoryname : String,  val subcategory :String,  val subcategoryname : String, val name :String, val url :String,  val photo: String,
                           val services: ArrayList<ServiceListItem>)


}