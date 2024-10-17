package com.example.middentest.compose.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.middentest.R
import com.example.middentest.ui.theme.oswaldFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarConfig(
    onNavigationIconClick: () -> Unit = {},
    onMoreVertClick: () -> Unit = {}
) {
    TopAppBar(
        title = { TopAppBarTitle() },
        navigationIcon = {
            IconButtonApp(iconId = R.drawable.ic_arrow_back, action = onNavigationIconClick)
        },
        actions = {
            IconButtonApp(iconId = R.drawable.ic_more_vert, action = onMoreVertClick)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}


@Composable
private fun IconButtonApp(iconId: Int, action: () -> (Unit), contentDescription: String = "") {
    IconButton(onClick = action) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onBackground,
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
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun TopAppBarTitle() {
    Text(
        text = "CONTACTS",
        color = MaterialTheme.colorScheme.onBackground,
        fontFamily = oswaldFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontSize = 24.sp
        //fontFamily = poppinsFontFamily
    )
}