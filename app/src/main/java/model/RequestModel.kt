package model

import com.google.gson.annotations.SerializedName

data class RequestModel(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String,
    @SerializedName("login") var login: String
)
