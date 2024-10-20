package com.example.middentest.compose.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.middentest.data.models.LoadingState
import com.example.middentest.data.models.UserInfo
import com.example.middentest.ui.theme.ErrorLoadingUsers
import com.example.middentest.ui.theme.MiddenTestTheme
import com.example.middentest.ui.theme.openSansFontFamily
import com.example.middentest.ui.theme.oswaldFontFamily
import com.example.middentest.ui.theme.sfDisplayFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorLoadingScreen(
    isRefreshing: Boolean,
    onRefresh: () -> Unit = {}
) {
    val pullToRefreshState = rememberPullToRefreshState()
    Box(modifier = Modifier
        .nestedScroll(pullToRefreshState.nestedScrollConnection), contentAlignment = Alignment.Center){
        LazyColumn(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            item {
                Text(
                    text = "Error loading users data",
                    color = ErrorLoadingUsers,
                    fontFamily = openSansFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontSize = 20.sp
                )
                Text(
                    text = "Pull to refresh",
                    color = ErrorLoadingUsers,
                    fontFamily = openSansFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontSize = 14.sp
                )
            }
        }
        if(pullToRefreshState.isRefreshing){
            LaunchedEffect(true) {
                onRefresh.invoke()
            }
        }
        LaunchedEffect(isRefreshing) {
            if(isRefreshing){
                pullToRefreshState.startRefresh()
            }else{
                pullToRefreshState.endRefresh()
            }
        }
        if(pullToRefreshState.isRefreshing){
            PullToRefreshContainer(state = pullToRefreshState)
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
        Surface {
            ErrorLoadingScreen(true)
        }
    }
}