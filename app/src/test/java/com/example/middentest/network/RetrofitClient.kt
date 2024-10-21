package com.example.middentest.network

import com.example.middentest.core.common.BASE_URL
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    val retrofit: Retrofit by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit
    }

    val apiService: RandomUserApi by lazy { retrofit.create(RandomUserApi::class.java) }

    private val RESULTS = 10
    private var PAGE = 1

    @Test
    fun testRetrofitInstance(){
        val instance = RetrofitClient().retrofit
        assert(instance.baseUrl().url().toString() == BASE_URL)
    }
}