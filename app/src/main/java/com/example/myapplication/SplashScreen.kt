package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class SplashScreen : AppCompatActivity() {

    lateinit var splash: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        splash = findViewById(R.id.splash)
        splash.alpha = 0f
        splash.animate().setDuration(1500).alpha(1f).withEndAction {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}