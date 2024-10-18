package com.example.middentest.compose.screens

import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UICompose(viewModel: MainViewModel) {
    val ctx = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val navController = rememberNavController()

    //Toolbar
    val screen = remember { mutableStateOf(MiddenTestScreens.ContactList) }

    //userList

    val userList = remember {
        mutableStateOf(mutableListOf<UserInfo>())
    }

    val loadingState = remember {
        mutableStateOf(LoadingState.LOADING)
    }

    // User selected
    val userIndex = remember { mutableIntStateOf(0) }

    //viewModel

    viewModel.userInfoApiResult.observe(lifecycleOwner) {
        userList.value.addAll(it.results)
        Log.v("UICOMPOSE", userList.toString())
    }

    viewModel.loadingState.observe(lifecycleOwner) {
        loadingState.value = it
    }

    viewModel.getUserInfoApiResult()

    Scaffold(
        topBar = {
            TopBarConfig(
                screen = screen.value,
                userInfo = if (userList.value.isNotEmpty()) userList.value[userIndex.value] else UserInfo(),
                onNavigationIconClick = { createToast(context = ctx) },
                onMoreVertClick = { createToast(context = ctx) }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.safeDrawing,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MiddenTestScreens.ContactList.name,
        ) {
            composable(route = MiddenTestScreens.ContactList.name) {
                screen.value = MiddenTestScreens.ContactList
                ContactList(
                    paddingValues = innerPadding,
                    userList = userList.value,
                    loadingState = loadingState.value,
                    onClick = { index ->
                        userIndex.intValue = index
                        navController.navigate(MiddenTestScreens.UserProfile.name)
                    },
                    onLoadMore = {
                        viewModel.getUserInfoApiResult()
                    })
            }
            composable(route = MiddenTestScreens.UserProfile.name) {
                screen.value = MiddenTestScreens.UserProfile
                UserProfileScreen(
                    user = userList.value[userIndex.value],
                    paddingValues = innerPadding
                )
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