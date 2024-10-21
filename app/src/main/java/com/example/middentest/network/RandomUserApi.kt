package com.example.middentest.network

import com.example.middentest.core.common.BASE_URL
import com.example.middentest.data.models.UserInfoApiResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
    @GET(".")
    suspend fun getUserList(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String,
    ): UserInfoApiResult
}