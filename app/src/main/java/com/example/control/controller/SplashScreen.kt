package com.example.control.controller

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.control.R
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_reg.*
import kotlinx.android.synthetic.main.activity_splash_screen.*


@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val put = AnimationUtils.loadAnimation(this, R.anim.alpha)
        val lay = findViewById<ImageView>(R.id.ImageView)
        val anim: Animation = AnimationUtils.loadAnimation(this, R.anim.tv_anim)
        val tv: TextView = findViewById(R.id.Flowwow)
        tv.startAnimation(anim)
        lay.startAnimation(put)
        Handler().postDelayed({
            val dd = getSharedPreferences("data_base2",0)
            if (dd.getString("Auth","False")!=="False"){
                startActivity(Intent(this@SplashScreen,MainActivity::class.java))
            }
            else{
                startActivity(Intent(this@SplashScreen,AuthAct::class.java))
            }
        }, 3000)
    }
}

