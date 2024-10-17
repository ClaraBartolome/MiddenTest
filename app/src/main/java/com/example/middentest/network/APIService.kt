package com.example.middentest.network

import com.example.middentest.common.BASE_URL
import com.example.middentest.data.models.UserInfoApiResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(".")
    suspend fun getUserList(
        @Query("results") number: Int,
        @Query("seed") seed: String,
    ): UserInfoApiResult
}

object RetrofitInstance {
    val api: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}