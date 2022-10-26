package com.rajdeepsingh.birthdayreminderapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rajdeepsingh.birthdayreminderapp.R
import com.rajdeepsingh.birthdayreminderapp.activities.utils.Constant

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
        }, Constant.setTimeOutDuration)

    }
}