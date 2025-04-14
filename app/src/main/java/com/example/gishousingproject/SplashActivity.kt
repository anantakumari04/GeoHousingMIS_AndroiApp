package com.example.gishousingproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed


class SplashActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logoImage = findViewById<ImageView>(R.id.logoImage)
        val titleText = findViewById<TextView>(R.id.appTitle)

        val fadeIn = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val slideUp = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.slide_up)

        logoImage.startAnimation(fadeIn)
        titleText.startAnimation(slideUp)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 5000)
    }

}
