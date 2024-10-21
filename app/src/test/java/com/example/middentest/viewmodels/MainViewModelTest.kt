package com.example.middentest.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.middentest.core.utils.AppDispatcherFactory
import com.example.middentest.data.models.Info
import com.example.middentest.data.models.LoadingState
import com.example.middentest.data.models.Name
import com.example.middentest.data.models.UserInfo
import com.example.middentest.data.models.UserInfoApiResult
import com.example.middentest.network.usecases.GetUserInfoUseCase
import com.example.middentest.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class MainViewModelTest{
    private lateinit var mainViewModel: MainViewModel
    private lateinit var dispatcherFactory: AppDispatcherFactory
    private lateinit var userInfoUseCase: GetUserInfoUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule() // Para LiveData
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val RESULTS = 5
    private val PAGE = 1
    private val SEED = "test_seed"

    val userInfoApiResultCorrect: UserInfoApiResult = UserInfoApiResult(
        info = Info(
            seed = "test_seed",
            results = 5,
            page = 1,
            version = "1.4"
        ),
        results = listOf(
            UserInfo(
                gender = "male",
                name = Name("Mr", "Fritz", "Baltes"),
                email = "fritz.baltes@example.com",
                phone = "0256-4999344"
            ),
            UserInfo(
                gender = "female",
                name = Name("Mrs", "Heather", "Shaw"),
                email = "heather.shaw@example.com",
                phone = "021-888-4190"
            ),
            UserInfo(
                gender = "female",
                name = Name("Ms", "Nanci", "Pinto"),
                email = "nanci.pinto@example.com",
                phone = "(22) 7042-1767"
            )
        )
    )

    @Before
    fun setup(){
        userInfoUseCase = mockk()
        dispatcherFactory = mockk<AppDispatcherFactory>()
        mainViewModel = MainViewModel(dispatcherFactory, userInfoUseCase)
    }

    @Test
    fun `viewmodel returns userApiList`(): Unit = runBlocking{
        coEvery {  userInfoUseCase.invoke(PAGE, RESULTS, SEED) } returns flowOf(userInfoApiResultCorrect)

        mainViewModel.getUserInfoApiResult()
        mainViewModel.userInfoApiResult.observeForever {
            assertEquals(userInfoApiResultCorrect, it)
        }

    }

    @Test
    fun `viewmodel returns error`(): Unit = runBlocking{
        coEvery {  userInfoUseCase.invoke(PAGE, RESULTS, SEED) } throws Exception("Network problems")

        mainViewModel.getUserInfoApiResult()
        mainViewModel.loadingState.observeForever {
            assertEquals(LoadingState.Status.FAILED, it.status)
        }
    }

    @Test
    fun `viewmodel has an empty list when initialized`(){
        assertTrue(mainViewModel.userInfoApiResult.value?.results.isNullOrEmpty())
    }

    @Test
    fun `viewmodel emits loading`(): Unit = runBlocking{
        coEvery {  userInfoUseCase.invoke(PAGE, RESULTS, SEED) } coAnswers {
            delay(3000)
            flowOf(userInfoApiResultCorrect)
        }

        mainViewModel.getUserInfoApiResult()

        withTimeout(2000){
            mainViewModel.loadingState.observeForever {
                assertEquals(LoadingState.LOADING, it)
            }
        }

    }
}