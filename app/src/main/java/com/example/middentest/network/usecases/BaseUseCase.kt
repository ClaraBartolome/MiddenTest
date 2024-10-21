package com.example.middentest.network.usecases

import com.example.middentest.data.models.UserInfoApiResult
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter): Result
    suspend fun invoke(page: Int, results: Int, seed: String): Flow<UserInfoApiResult>
}