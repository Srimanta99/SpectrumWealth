package com.spectrumwealth.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dazzlebloom.utiles.sheardpreference.AppSheardPreference
import com.spectrumwealth.R
import com.spectrumwealth.utils.AppConstant

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AppSheardPreference.getsheardPreference(this)
        Handler().postDelayed( Runnable {
            if ( AppSheardPreference.fetchStringFromAppPreference(AppConstant.USER_ID).equals("") ||
                AppSheardPreference.fetchStringFromAppPreference(AppConstant.USER_ID)==null) {

                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, AppConstant.App_SPLASH_TIME)
    }
}