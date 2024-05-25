package com.devina.ekacareformassignment.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.devina.ekacareformassignment.R
import com.devina.ekacareformassignment.ui.form.FormActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        CoroutineScope(Dispatchers.Main).launch{

            delay(3000)

            startActivity(Intent(this@SplashScreen, FormActivity::class.java))
            finish()
        }
    }
}