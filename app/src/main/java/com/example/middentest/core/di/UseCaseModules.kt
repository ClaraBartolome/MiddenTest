package com.example.middentest.core.di

import com.example.middentest.network.RandomUserRepositoryImpl
import com.example.middentest.network.usecases.GetUserInfoUseBaseCase
import com.example.middentest.network.usecases.GetUserInfoUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val UseCaseModules = module {
    single(named("get_user_info")) { provideUserInfo(get()) }
}

fun provideUserInfo(repository: RandomUserRepositoryImpl): GetUserInfoUseCase{
    return GetUserInfoUseCase(repository)
}