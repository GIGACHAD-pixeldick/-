package model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API {
    @POST("/signUp")
    fun Registration(@Body Posts: RequestModel): Call<AuthAnswer>
    @POST("/signIn")
    fun Authorization(@Body Get: ReqMod2): Call<AuthAnswer>
    @GET("/plants")
    fun getPlants(): Call<List<ListPlant>>
}