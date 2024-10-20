package com.example.middentest.compose.screens

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.middentest.R
import com.example.middentest.compose.components.ContactListItem
import com.example.middentest.compose.components.EndlessScrollList
import com.example.middentest.data.models.LoadingState
import com.example.middentest.data.models.UserInfo
import com.example.middentest.ui.theme.GreyHorizontalDivider
import com.example.middentest.ui.theme.GreyIcon
import com.example.middentest.ui.theme.GreySubtitle
import com.example.middentest.ui.theme.MiddenTestTheme
import com.example.middentest.ui.theme.sfDisplayFontFamily
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ContactList(userList: List<UserInfo>, paddingValues: PaddingValues, loadingState: LoadingState, onClick: (index: Int) -> Unit = {}, onLoadMore: () -> Unit = {}) {
    EndlessScrollList(userList = userList, paddingValues = paddingValues, modifier = Modifier, loadingState = loadingState, onClick = onClick, loadMore = onLoadMore)
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
            ContactList(paddingValues = PaddingValues(), userList = mutableListOf<UserInfo>(), loadingState = LoadingState.LOADING)
        }
    }
}