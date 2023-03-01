package com.example.control.controller

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.control.R
import kotlinx.android.synthetic.main.activity_reg.*
import model.API
import model.AuthAnswer
import model.RequestModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        button.setOnClickListener {
            val login = login_auth.text.toString()
            val pochta = email.text.toString()
            val parol = password.text.toString()
            if (Patterns.EMAIL_ADDRESS.matcher(pochta).matches()) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://strukov-artemii.online:8084/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit.create(API::class.java).Registration(RequestModel(pochta, parol, login))
                    .enqueue(object : Callback<AuthAnswer> {

                        override fun onResponse(
                            call: Call<AuthAnswer>,
                            response: Response<AuthAnswer>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    this@RegAct,
                                    "You're successfully registered",
                                    Toast.LENGTH_LONG
                                ).show()
                                startActivity(Intent(this@RegAct, MainActivity::class.java))
                                finish()
                            }
                        }

                        override fun onFailure(call: Call<AuthAnswer>, t: Throwable) {
                            Toast.makeText(this@RegAct, t.message, Toast.LENGTH_LONG).show()
                        }
                    })
            }
        }
    }
}