package com.dazzlebloom.utiles.customtypeface

import android.content.Context
import android.graphics.Typeface

class CustomTypeface(val context : Context){

    var openSansBold : Typeface?= null
    var openSansSemiBold : Typeface?= null
    var openSansMedium : Typeface ? = null
    var openSanslight : Typeface ? = null


    init {
        openSansBold = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Bold.ttf")
        openSansMedium = Typeface.createFromAsset(context.assets,"fonts/OpenSans-Medium.ttf" )
        openSansSemiBold = Typeface.createFromAsset(context.assets,"fonts/OpenSans-SemiBold.ttf" )
        openSanslight = Typeface.createFromAsset(context.assets,"fonts/OpenSans-Light.ttf" )

    }
}