package com.example.middentest.core.di

import android.app.Application
import com.example.middentest.core.common.BASE_URL
import com.example.middentest.network.RandomUserApi
import com.example.middentest.network.RandomUserRepository
import com.example.middentest.viewmodels.MainViewModel
import com.example.proyecto2.utils.AppDispatcherFactory
import com.example.proyecto2.utils.DispatcherFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule: Module = module {
    viewModel {
        MainViewModel(get(),
            getUserInfoUseCase = get(named("get_user_info"))
            )
    }
}

val dispatcherFactoryModule = module {
    single<DispatcherFactory> {
        AppDispatcherFactory()
    }
}

val netModule: Module = module {

    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(cache)

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }
    single {
        provideHttpClient(get())
    }

    single { provideCache(androidApplication()) }

    single {
        provideRetrofit(get())
    }
}

val randomUserAPIModule: Module = module {
    fun provideRandomUserApi(retrofit: Retrofit): RandomUserApi {
        return retrofit.create(RandomUserApi::class.java)
    }

    single {
        provideRandomUserApi(get())
    }

}

val repositoryModule = module {
    single {
        RandomUserRepository(get())
    }
}



