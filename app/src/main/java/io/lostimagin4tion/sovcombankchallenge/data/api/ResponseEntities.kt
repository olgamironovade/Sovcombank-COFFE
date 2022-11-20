package io.lostimagin4tion.sovcombankchallenge.data.api

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    val auth_token: String,
    val refresh: String?,
    val id: Long
)

data class RegisterResponse(
    val message: String,
    val code: Int
)

data class MyAccountsResponse(
    @SerializedName("data") val data: List<Data>
) {
    data class Data(
        @SerializedName("account_id") val accountId: Int,
        @SerializedName("account_name") val accountName: String,
        @SerializedName("account_number") val accountNumber: String,
        @SerializedName("money_amount") val moneyAmount: Double,
    )
}

data class MyProfileResponse(
    @SerializedName("name") val name: String,
    @SerializedName("second_name") val secondName: String,
    @SerializedName("patronymic") val patronymic: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone_number") val phoneNumber: String
)

data class TransferMoneyResponse(
    @SerializedName("execution_code") val execCode: Int,
    @SerializedName("buy_currency") val buyCurrency: String,
    @SerializedName("buy_amount") val buyAmount: String,
    @SerializedName("sell_currency") val sellCurrency: String,
    @SerializedName("sell_amount") val sellAmount: String,
)