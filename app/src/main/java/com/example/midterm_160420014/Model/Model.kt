package com.example.midterm_160420014.Model

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("name")
    val name:String?,
    @SerializedName("email")
    val email:String?,
    @SerializedName("password")
    val password:String?
)

data class ApiResponse(
    @SerializedName("status")
    val status:String?,
    @SerializedName("message")
    val message:String?,
)
