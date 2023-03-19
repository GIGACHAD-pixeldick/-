package com.example.control.controller

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.control.R
import kotlinx.android.synthetic.main.activity_main.*
import model.API
import model.Adapter
import model.ListPlant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var listInfPlant = arrayListOf<ListPlant>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://strukov-artemii.online:8084")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(API:: class.java).getPlants().enqueue(object :
            Callback<List<ListPlant>> {
            override fun onResponse(
                call: Call<List<ListPlant>>,
                response: Response<List<ListPlant>>
            ) {
                if (response.isSuccessful){
                    for(I in response.body()!!){
                        listInfPlant.add(ListPlant(I.title, I.price, I.room))
                    }
                    rec.adapter = Adapter(listInfPlant)
                    rec.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL, false)
                }
            }
            /**
             * Public Class Toast
             * @hide
             */
            override fun onFailure(call: Call<List<ListPlant>>, t: Throwable) {
                Toast.makeText(this@MainActivity,t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}