package com.example.middentest.compose.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.middentest.R
import com.example.middentest.ui.theme.GreyHorizontalDivider
import com.example.middentest.ui.theme.GreyTitleProfile
import com.example.middentest.ui.theme.MiddenTestTheme
import com.example.middentest.ui.theme.openSansFontFamily

@Composable
fun UserProfile() {
    Column {
        BannerProfilePicture()
        ProfileListItem(
            iconId = R.drawable.ic_user,
            title = "Nombre y apellidos",
            subtitle = "Laura Navarro Martinez"
        )
        ProfileListItem(
            iconId = R.drawable.ic_mail,
            title = "Nombre y apellidos",
            subtitle = "Laura Navarro Martinez"
        )
        ProfileListItem(
            iconId = R.drawable.ic_female,
            title = "Nombre y apellidos",
            subtitle = "Laura Navarro Martinez"
        )
        ProfileListItem(
            iconId = R.drawable.ic_calendar,
            title = "Nombre y apellidos",
            subtitle = "Laura Navarro Martinez"
        )
        ProfileListItem(
            iconId = R.drawable.ic_phone,
            title = "Nombre y apellidos",
            subtitle = "Laura Navarro Martinez"
        )
    }

}

@Composable
private fun BannerProfilePicture() {
    Box(Modifier.height(238.dp)) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.img_banner_placeholder),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopStart,
                    modifier = Modifier.height(200.dp)
                )
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = "",
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_edit),
                            contentDescription = "",
                        )
                    }
                }
            }

        }

        Box(
            contentAlignment = Alignment.BottomStart, modifier = Modifier
                .fillMaxSize()
                .padding(start = 17.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_avatar_placeholder),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopStart,
                modifier = Modifier
                    .size(77.dp)
                    .border(
                        border = BorderStroke(3.dp, MaterialTheme.colorScheme.background),
                        shape = CircleShape
                    )
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
private fun ProfileListItem(iconId: Int, title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 5.dp, start = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.wrapContentHeight(), verticalArrangement = Arrangement.Center) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = "",
                modifier = Modifier.size(32.dp)
            )
        }
        Column(modifier = Modifier.padding(start = 20.dp)) {
            Column {
                Text(
                    text = title,
                    color = GreyTitleProfile,
                    fontFamily = openSansFontFamily,
                    fontStyle = FontStyle.Normal,
                    fontSize = 11.sp
                )
                Text(
                    text = subtitle,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = openSansFontFamily,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = GreyHorizontalDivider, thickness = 1.dp)
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
        Surface {
            UserProfile()
        }
    }
}