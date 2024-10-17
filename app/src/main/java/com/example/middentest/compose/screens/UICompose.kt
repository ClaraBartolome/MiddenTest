package com.example.middentest.compose.screens

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.middentest.common.MiddenTestScreens
import com.example.middentest.compose.components.TopBarConfig
import com.example.middentest.compose.createToast
import com.example.middentest.data.models.LoadingState
import com.example.middentest.data.models.UserInfo
import com.example.middentest.ui.theme.MiddenTestTheme
import com.example.middentest.viewmodels.MainViewModel

@Composable
fun UICompose(viewModel: MainViewModel) {
    val ctx = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val navController = rememberNavController()

    //Toolbar
    val screen = remember { mutableStateOf(MiddenTestScreens.ContactList) }

    //userList

    val userList = remember {
        mutableStateOf(listOf<UserInfo>())
    }

    val loadingState = remember {
        mutableStateOf(LoadingState.LOADING)
    }

    //viewModel

    viewModel.userInfoApiResult.observe(lifecycleOwner) {
        userList.value = it.results
        Log.v("UICOMPOSE", userList.toString())
    }

    viewModel.loadingState.observe(lifecycleOwner) {
        loadingState.value = it
    }

    viewModel.getUserInfoApiResult()

    Scaffold(
        topBar = {
            TopBarConfig(
                onNavigationIconClick = { createToast(context = ctx) },
                onMoreVertClick = { createToast(context = ctx) }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MiddenTestScreens.ContactList.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MiddenTestScreens.ContactList.name) {
                screen.value = MiddenTestScreens.ContactList
                ContactList(userList.value) { navController.navigate(MiddenTestScreens.UserProfile.name) }
            }
            composable(route = MiddenTestScreens.UserProfile.name) {
                screen.value = MiddenTestScreens.UserProfile
                UserProfile()
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    apiLevel = 33,
    locale = "es",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun PrevMainScreen() {
    MiddenTestTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            //UICompose()
        }
    }
}