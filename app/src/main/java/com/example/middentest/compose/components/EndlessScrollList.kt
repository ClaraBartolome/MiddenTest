package com.example.middentest.compose.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.middentest.compose.screens.LoadingScreen
import com.example.middentest.data.models.LoadingState
import com.example.middentest.data.models.UserInfo

private val buffer = 1 // load more when scroll reaches last n item, where n >= 1

@Composable
fun EndlessScrollList(
    userList: List<UserInfo>,
    paddingValues: PaddingValues,
    modifier: Modifier,
    loadingState: LoadingState,
    loadMore: () -> Unit = {},
    onClick: (index: Int) -> Unit = {}
) {
    val listState = rememberLazyListState()

    // observe list scrolling
    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - buffer
        }
    }

    // load more if scrolled to bottom
    LaunchedEffect(reachedBottom) {
        if (reachedBottom) loadMore()
    }

    // display our list
    LazyColumn(contentPadding = paddingValues, modifier = modifier, state = listState) {
        itemsIndexed(items = userList){ index, user ->
            ContactListItem(name = user.name.toString(), email = user.email?: "", imageURL = user.picture?.large?: ""){
                onClick.invoke(index)
            }
        }
        if(loadingState == LoadingState.LOADING){
            item {
                Spacer(modifier = Modifier.height(10.dp))
                LoadingScreen(paddingValues = PaddingValues())
            }
        }
    }
}