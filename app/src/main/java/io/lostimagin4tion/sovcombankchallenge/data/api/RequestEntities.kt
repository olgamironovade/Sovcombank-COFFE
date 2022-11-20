package io.lostimagin4tion.sovcombankchallenge.data.api

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class AuthRequest(
    val password: String,
    val username: String,
)

data class RegisterRequest(
    @SerializedName("name") val name: String,
    @SerializedName("surname") val surname: String,
    @SerializedName("patronymic") val patronymic: String,
    @SerializedName("date_of_birth") val dateOfBirth: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("password") val password: String
)