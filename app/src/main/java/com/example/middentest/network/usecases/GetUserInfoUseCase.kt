package com.example.middentest.network.usecases

import com.example.middentest.data.models.UserInfoApiResult
import com.example.middentest.network.RandomUserRepositoryImpl
import kotlinx.coroutines.flow.Flow


typealias GetUserInfoUseBaseCase = BaseUseCase<Unit, Flow<UserInfoApiResult>>
class GetUserInfoUseCase(
    private val repository: RandomUserRepositoryImpl
) : GetUserInfoUseBaseCase {
    override suspend fun invoke(params: Unit): Flow<UserInfoApiResult> {
        TODO("Not yet implemented")
    }
    override suspend operator fun invoke(page: Int, results: Int, seed: String): Flow<UserInfoApiResult>  = repository.getUserAPIInfo(page, results, seed)
}