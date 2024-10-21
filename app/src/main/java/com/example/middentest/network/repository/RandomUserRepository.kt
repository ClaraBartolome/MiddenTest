package com.example.middentest.network.repository

import com.example.middentest.data.models.UserInfoApiResult
import com.example.middentest.network.RandomUserApi
import kotlinx.coroutines.flow.Flow

interface RandomUserRepository {
    suspend fun getUserAPIInfo(page: Int, results: Int, seed: String): Flow<UserInfoApiResult>
}