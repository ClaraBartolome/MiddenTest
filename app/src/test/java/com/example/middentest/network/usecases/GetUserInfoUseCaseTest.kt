package com.example.middentest.network.usecases

import com.example.middentest.data.models.Info
import com.example.middentest.data.models.Name
import com.example.middentest.data.models.UserInfo
import com.example.middentest.data.models.UserInfoApiResult
import com.example.middentest.network.RandomUserRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.DefaultAsserter.fail
import kotlin.test.assertNotNull

class GetUserInfoUseCaseTest(){
    private lateinit var postRepository: RandomUserRepositoryImpl
    private lateinit var userInfoUseCase: GetUserInfoUseCase

    private val RESULTS = 10
    private val PAGE = 1
    private val SEED = "test_seed"

    @Before
    fun setUp() {
        // Inicializa el repositorio con el mock
        postRepository = mockk()
        userInfoUseCase = GetUserInfoUseCase(postRepository)
    }

    @Test
    fun useCaseReturnsUserInfoApi() = runBlocking {
        // Datos simulados
        val mockPosts = UserInfoApiResult(
            info = Info(
                seed = "test_seed",
                results = 5,
                page = 1,
                version = "1.4"
            ),
            results = listOf())

        // Configura el comportamiento simulado del repositorio
        coEvery {  postRepository.getUserAPIInfo(PAGE, RESULTS, SEED) } returns flowOf(mockPosts)

        // Llama al método del caso de uso y recoge el primer valor emitido
        val result = userInfoUseCase.invoke(PAGE, RESULTS, SEED).first()

        // Verifica que el resultado sea el esperado
        assertEquals(mockPosts, result)
    }

    @Test(expected = Exception::class)
    fun useCaseReturnsError(): Unit = runBlocking {
        coEvery {  postRepository.getUserAPIInfo(PAGE, RESULTS, SEED) } throws Exception("Network Error")
        userInfoUseCase.invoke(PAGE, RESULTS, SEED).first()
    }

    @Test
    fun repositoryReturnsEmptyResponse() = runBlocking{
        val mockPosts = UserInfoApiResult(
            info = Info(
                seed = "test_seed",
                results = 5,
                page = 1,
                version = "1.4"
            ),
            results = listOf())

        // Configura el comportamiento simulado de la API
        coEvery {  postRepository.getUserAPIInfo(PAGE, RESULTS, SEED) } returns flowOf(mockPosts)

        // Llama al método del repositorio y recoge el primer valor emitido
        val result = userInfoUseCase.invoke(PAGE, RESULTS, SEED).first()

        // Verifica que el resultado sea el esperado
        assertEquals(mockPosts, result)
    }

    @Test
    fun repositoryReturnsMultiplePages() = runBlocking {
        // Datos simulados
        val infoAPI1 = UserInfoApiResult(
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
                )))

        val infoAPI2 = UserInfoApiResult(
            info = Info(
                seed = "test_seed",
                results = 5,
                page = 1,
                version = "1.4"
            ),
            results = listOf(
                UserInfo(
                    gender = "female",
                    name = Name("Miss", "Jennie", "Nichols"),
                    email = "jennie.nichols@example.com",
                    phone = "(272) 790-0888"
                )))

        // Configura el comportamiento simulado de la API
        coEvery { postRepository.getUserAPIInfo(1, RESULTS, SEED) } returns flowOf(infoAPI1)
        coEvery { postRepository.getUserAPIInfo(2, RESULTS, SEED) } returns flowOf(infoAPI2)

        // Llama al método del repositorio y recoge el primer valor emitido
        val resultPage1 = userInfoUseCase.invoke(1, RESULTS, SEED).first()
        val resultPage2 = userInfoUseCase.invoke(2, RESULTS, SEED).first()

        // Verifica que el resultado sea el esperado
        assertEquals(infoAPI1, resultPage1)
        assertEquals(infoAPI2, resultPage2)
    }

    @Test(expected = Exception::class)
    fun repositoryResponseDelays() = runBlocking {
        // Datos simulados
        val mockPosts = UserInfoApiResult(
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
                )))

        // Configura el comportamiento simulado de la API
        coEvery { postRepository.getUserAPIInfo(PAGE, RESULTS, SEED) } coAnswers {
            delay(3000)
            flowOf(mockPosts)
        }

        // Configura el timeout en el repositorio o la función de prueba
        withTimeout(2000) {
            try {
                userInfoUseCase.invoke(PAGE, RESULTS, SEED).first()
                fail("Repository isn't throwing exception")
            } catch (e: TimeoutCancellationException) {
                // La prueba pasa si se lanza TimeoutCancellationException
            }
        }
    }

    @Test
    fun repositoryReturnsPartialData() = runBlocking {
        // Datos simulados
        val mockPosts = UserInfoApiResult(
            info = Info(
                seed = "test_seed",
                results = 5,
                page = 1,
                version = "1.4"
            ),
            results = listOf(
                UserInfo(
                    gender = "male",
                    name = Name("Mr", null, "Baltes"),
                    email = null,
                    phone = "0256-4999344"
                )))

        // Configura el comportamiento simulado de la API
        coEvery { postRepository.getUserAPIInfo(PAGE, RESULTS, SEED) } returns flowOf(mockPosts)

        // Llama al método del repositorio y recoge el primer valor emitido
        val result = userInfoUseCase.invoke(PAGE, RESULTS, SEED).first()

        // Verifica que el resultado sea el esperado
        assertNotNull(result)
        assertEquals("Baltes", result.results[0].name?.last)
        assertEquals(null, result.results[0].email)
    }
}