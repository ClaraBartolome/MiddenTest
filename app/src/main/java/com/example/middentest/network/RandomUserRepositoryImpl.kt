package com.example.middentest.network

import com.example.middentest.data.models.UserInfoApiResult
import com.example.middentest.network.repository.RandomUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class RandomUserRepositoryImpl(private val randomUserApi: RandomUserApi): RandomUserRepository {
    override suspend fun getUserAPIInfo(page: Int, results: Int, seed: String): Flow<UserInfoApiResult> =
        flow { emit(randomUserApi.getUserList(page, results, seed)) }
}