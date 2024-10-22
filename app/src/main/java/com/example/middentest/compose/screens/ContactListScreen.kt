package com.example.middentest.compose.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.middentest.compose.components.EndlessScrollList
import com.example.middentest.data.models.LoadingState
import com.example.middentest.data.models.UserInfo
import com.example.middentest.ui.theme.MiddenTestTheme

@Composable
fun ContactListScreen(
    userList: List<UserInfo>,
    paddingValues: PaddingValues,
    loadingState: LoadingState,
    isRefreshing: Boolean,
    onClick: (index: Int) -> Unit = {},
    onLoadMore: () -> Unit = {},
    onRefresh: () -> Unit = {}
) {
    if (loadingState.status == LoadingState.Status.FAILED) {
        ErrorLoadingScreen(isRefreshing, onRefresh)
    } else {
        EndlessScrollList(
            userList = userList,
            paddingValues = paddingValues,
            modifier = Modifier,
            loadingState = loadingState,
            onClick = onClick,
            loadMore = onLoadMore
        )
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
            ContactListScreen(
                paddingValues = PaddingValues(),
                userList = mutableListOf<UserInfo>(),
                loadingState = LoadingState.LOADING,
                isRefreshing = false
            )
        }
    }
}