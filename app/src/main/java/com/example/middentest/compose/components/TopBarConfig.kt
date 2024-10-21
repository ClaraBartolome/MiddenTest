package com.example.middentest.compose.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.middentest.R
import com.example.middentest.core.common.MiddenTestScreens
import com.example.middentest.data.models.UserInfo
import com.example.middentest.ui.theme.oswaldFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarConfig(
    isSearchOpen: MutableState<Boolean>,
    screen: MiddenTestScreens,
    userInfo: UserInfo,
    onNavigationIconClick: () -> Unit = {},
    onMoreVertClick: () -> Unit = {},
    searchText: MutableState<String>,
    onTextChange: (String) -> Unit,
    onSearchInit: (String) -> List<UserInfo>,
    onClickOnSearched: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    if (isSearchOpen.value) {
        SearchBar(
            text = searchText,
            onSearchInit = onSearchInit,
            onClickOnSearched = onClickOnSearched,
            onTextChange = onTextChange,
            onCloseClicked = onCloseClicked
        )
    } else {
        TopAppBar(
            title = { TopAppBarTitle(screen, userInfo) },
            navigationIcon = {
                IconButtonApp(iconId = R.drawable.ic_arrow_back, action = onNavigationIconClick)
            },
            actions = {
                IconButtonApp(iconId = R.drawable.ic_more_vert, action = onMoreVertClick)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = when (screen) {
                    MiddenTestScreens.ContactList -> MaterialTheme.colorScheme.background
                    MiddenTestScreens.UserProfile -> Color.Transparent
                },
                actionIconContentColor = when (screen) {
                    MiddenTestScreens.ContactList -> MaterialTheme.colorScheme.onBackground
                    MiddenTestScreens.UserProfile -> Color.White
                },
                navigationIconContentColor = when (screen) {
                    MiddenTestScreens.ContactList -> MaterialTheme.colorScheme.onBackground
                    MiddenTestScreens.UserProfile -> Color.White
                },
            )
        )
    }
}


@Composable
private fun IconButtonApp(iconId: Int, action: () -> (Unit), contentDescription: String = "") {
    IconButton(onClick = action) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription
        )
    }
}

@Composable
private fun IconButtonApp(
    imageVector: ImageVector,
    action: () -> (Unit),
    contentDescription: String = ""
) {
    IconButton(onClick = action) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}

@Composable
private fun TopAppBarTitle(screen: MiddenTestScreens, userInfo: UserInfo) {
    Text(
        text = when (screen) {
            MiddenTestScreens.ContactList -> stringResource(id = R.string.toolbarTitle)
            MiddenTestScreens.UserProfile -> userInfo.name?.first
                ?: stringResource(id = R.string.toolbarTitle)
        },
        color = when (screen) {
            MiddenTestScreens.ContactList -> MaterialTheme.colorScheme.onBackground
            MiddenTestScreens.UserProfile -> Color.White
        },
        fontFamily = oswaldFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp
        //fontFamily = poppinsFontFamily
    )
}