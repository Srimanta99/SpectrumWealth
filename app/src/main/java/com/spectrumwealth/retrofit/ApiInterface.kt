package com.dazzlebloom.retrofit

import com.spectrumwealth.adapter.ViewMoreApiResponse
import com.spectrumwealth.model.*

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

     @Headers("Content-Type: application/json")
     @GET("api.php?")
     suspend fun callLoginApi( @Query("action") action : String, @Query("email") email :String , @Query("password")  password :String) : Response<LoginApiResponseModel>

     @Headers("Content-Type: application/json")
     @POST("api.php?")
     suspend fun callSignUpApi( @Query("action") action : String, @Query("email") email :String , @Query("password")  password :String,
                                @Query("name") name :String , @Query("phone")  phone :String, @Query("address")  address :String,
                                @Query("age") age :String , @Query("confirmpassword")  confirmpassword :String) : Response<SignupResponseModel>


    // https://www.tenacioushub.in/cms/api/api.php?action=register
     // &email=puja@gmail.com&password=111&name=puja&phone=9999999999&address=abcnagar&age=30&confirmpassword=111

     @Headers("Accept: application/json")
     @GET("api.php")
     suspend fun getlisting(@Query("action")  action : String) : Response<ServiceResponseModel>

     @Headers("Accept: application/json")
     @GET("api.php")
     suspend fun getlistingCatSUbcat(@Query("action")  action : String, @Query("category")  category : String, @Query("subcategory")  subcategory : String ) : Response<ServiceResponseModel>


     @Headers("Accept: application/json")
     @GET("api.php")
     suspend fun getCategory(@Query("action")  action : String) : Response<CategoryApiResponseModel>

     @Headers("Accept: application/json")
     @GET("api.php")
     suspend fun getSubCategory(@Query("action")  action : String, @Query("id")  id : String) : Response<SubCategoryApiResponseModel>


     @Headers("Accept: application/json")
     @GET("api.php")
     suspend fun getlistingbyCategory(@Query("action")  action : String, @Query("cat")  id : String) : Response<ViewMoreApiResponse>

     @Headers("Accept: application/json")
     @GET("api.php")
     suspend fun getSubCategoryItemList(@Query("action")  action : String, @Query("id")  id : String) : Response<ServicesBycategoryResponseModel>


     @GET("wp-json/menu/get")
     suspend fun getMenu() : Response<ResponseBody>


}