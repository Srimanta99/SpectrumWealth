package com.spectrumwealth.model

data class LoginApiResponseModel(val status : Int, val msg: String, val id :String , val name :String, val email :String ,val phone : String, val address :String,
val age :String, val message: String) {
}