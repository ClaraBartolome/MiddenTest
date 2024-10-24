package com.example.middentest.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.middentest.core.common.SEED
import com.example.middentest.core.utils.BaseViewModel
import com.example.middentest.core.utils.DispatcherFactory
import com.example.middentest.data.models.LoadingState
import com.example.middentest.data.models.UserInfoApiResult
import com.example.middentest.network.usecases.GetUserInfoUseBaseCase
import com.example.middentest.network.usecases.GetUserInfoUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val dispatcherFactory: DispatcherFactory,
    private val getUserInfoUseCase: GetUserInfoUseCase
): BaseViewModel(dispatcherFactory){

    companion object {
        const val TAG = "MAIN VIEW MODEL"
        const val LOAD_ERROR = "load error"
    }

    //private val apiService = RetrofitInstance.api
    private val RESULTS = 10
    private var PAGE = 1


    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _userInfoApiResult = MutableLiveData<UserInfoApiResult>()
    val userInfoApiResult: LiveData<UserInfoApiResult>
        get() = _userInfoApiResult


    fun getUserInfoApiResult() = viewModelScope.launch{
        launch {
           withContext(dispatcherFactory.getIO()){
               getUserInfoUseCase(page = PAGE, results = RESULTS, seed = SEED).onStart { _loadingState.postValue(LoadingState.LOADING) }
                   .catch {
                       _loadingState.postValue(LoadingState.error(LOAD_ERROR))
                       Log.e(TAG, LOAD_ERROR)
                   }
                   .collect { response ->
                       if (response.results.isNotEmpty()) {
                           Log.v(TAG, "Results loaded")
                           PAGE++
                           _userInfoApiResult.postValue(response)
                           _loadingState.postValue(LoadingState.LOADED)
                       }
                   }

           }
        }
    }
}