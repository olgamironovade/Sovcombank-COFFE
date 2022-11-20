package io.lostimagin4tion.sovcombankchallenge.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SovcombankApi {
    companion object {
        const val AUTH_ENDPOINTS = "auth"
        const val REFRESH_ENDPOINT = "auth/refresh"
        const val BASE_URL = "https://213.247.168.75:80/"
    }

    @POST("/auth")
    suspend fun auth(@Body authRequest: AuthRequest): AuthResponse

    @POST("/sign-up")
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse

    /**
     * Accounts
     */

    @GET("/{id}/accounts/all")
    suspend fun getMyAccounts(@Path("id") userId: Int): MyAccountsResponse

    @POST("/{id}/accounts/transfer")
    suspend fun transferMoney(
        @Path("id") userId: Int,
        @Query("currencyToBuy") buyCurrency: String,
        @Query("buyAmount") buyAmount: Int,
        @Query("currencyToSell") sellCurrency: String,
        @Query("sellAmount") sellAmount: Int
    ): TransferMoneyResponse

    /**
     * Profile
     */

    @GET("/{id}/profile/info")
    suspend fun getMyProfile(@Path("id") userId: Int): MyProfileResponse
}