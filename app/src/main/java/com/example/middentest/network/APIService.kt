package com.example.middentest.network

import com.example.middentest.data.models.UserInfoApiResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(".")
    suspend fun getUserList(
        @Query("results") number: Int
    ): UserInfoApiResult
}

object RetrofitInstance {
    private
    const val BASE_URL = "https://randomuser.me/api/"
    val api: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}