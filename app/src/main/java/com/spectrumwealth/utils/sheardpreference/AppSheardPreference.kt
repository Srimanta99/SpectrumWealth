package com.dazzlebloom.utiles.sheardpreference

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import kotlin.reflect.javaType
import kotlin.reflect.typeOf

class AppSheardPreference(val context: Activity) {

    companion object{
        var sharedPreference : SharedPreferences ?= null
        var editor : SharedPreferences.Editor ? = null
        fun storeStringAppPreference(context: Activity, key :String, value : String){
             sharedPreference =  context.getSharedPreferences("APP_PREFERENCE",Context.MODE_PRIVATE)
             editor = sharedPreference?.edit()
            editor?.putString(key,value)
            editor?.commit()
        }

        fun fetchStringFromAppPreference(key : String) : String{
            var value : String = ""
             if(sharedPreference!= null)
                 value = sharedPreference?.getString(key,"")!!

            return value
        }

        fun storeIntAppPreference(context: Activity, key :String, value : Int){
            sharedPreference =  context.getSharedPreferences("APP_PREFERENCE",Context.MODE_PRIVATE)
            editor = sharedPreference?.edit()
            editor?.clear()
            editor?.putInt(key,value)
            editor?.commit()
        }


        fun fetchIntFromAppPreference(key : String) : Int {
            var value : Int = 0
            if(sharedPreference!= null)
                value = sharedPreference?.getInt(key,0)!!

            return value
        }

        fun clearSheardpreference(context: Context){
            sharedPreference =  context.getSharedPreferences("APP_PREFERENCE",Context.MODE_PRIVATE)
            editor = sharedPreference?.edit()
            editor?.clear();
            editor?.apply();
        }

        fun getsheardPreference(context: Context){
            sharedPreference =  context.getSharedPreferences("APP_PREFERENCE",Context.MODE_PRIVATE)
            editor = sharedPreference?.edit()
        }

    }
}