package com.example.middentest.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.middentest.data.models.LoadingState
import com.example.middentest.data.models.UserInfoApiResult
import com.example.middentest.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    companion object {
        const val TAG = "MAIN VIEW MODEL"
        const val LOAD_ERROR = "load error"
    }

    private val apiService = RetrofitInstance.api

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _userInfoApiResult = MutableLiveData<UserInfoApiResult>()
    val userInfoApiResult: LiveData<UserInfoApiResult>
        get() = _userInfoApiResult


    fun getUserInfoApiResult() {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                val response = apiService.getUserList(5)
                if (response.results.isNotEmpty()) {
                    Log.v(TAG, "Results loaded")
                    _userInfoApiResult.value = response
                    _loadingState.value = LoadingState.LOADED
                }
            } catch (e: Exception) {
                // Handle errors here
                Log.e(TAG, e.toString())
                _loadingState.value =LoadingState.error(e.toString())
            }
        }
    }
}