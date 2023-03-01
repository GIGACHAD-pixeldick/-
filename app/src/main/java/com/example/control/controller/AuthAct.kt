package com.example.control.controller

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.control.R
import kotlinx.android.synthetic.main.activity_auth.*
import model.API
import model.AuthAnswer
import model.ReqMod2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class AuthAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        val dd = getSharedPreferences("data_base2",0)
        val editor = dd.edit()
        if(Patterns.EMAIL_ADDRESS.matcher(login_auth.text.toString()).matches()){
            retrofit2.Retrofit.Builder()
                .baseUrl("http://strukov-artemii.online:8084/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(API::class.java)
                .Authorization(ReqMod2(login_auth.text.toString(),password_auth.text.toString()))
                .enqueue(object: Callback<AuthAnswer>{

                    override fun onResponse(
                        call: Call<AuthAnswer>,
                        response: Response<AuthAnswer>
                    ) {
                        editor.putString("Auth","True")
                        editor.apply()
                        startActivity(Intent(this@AuthAct, MainActivity::class.java))
                        finish()
                    }

                    override fun onFailure(call: Call<AuthAnswer>, t: Throwable) {
                        Toast.makeText(this@AuthAct, t.message, Toast.LENGTH_LONG).show()
                    }

                })
        }
    }
}