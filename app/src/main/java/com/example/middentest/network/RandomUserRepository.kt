package com.example.middentest.network

import com.example.middentest.data.models.UserInfoApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RandomUserRepository(private val randomUserApi: RandomUserApi) {
    fun getUsers(page: Int, results: Int, seed: String): Flow<UserInfoApiResult> =
        flow { emit(randomUserApi.getUserList(page, results, seed)) }
}