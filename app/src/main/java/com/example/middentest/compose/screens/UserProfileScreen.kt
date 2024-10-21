package com.example.middentest.compose.screens

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.middentest.R
import com.example.middentest.compose.bitmapDescriptorFromVector
import com.example.middentest.compose.castGender
import com.example.middentest.compose.formatDate
import com.example.middentest.data.models.UserInfo
import com.example.middentest.ui.theme.GreyHorizontalDivider
import com.example.middentest.ui.theme.GreyTitleProfile
import com.example.middentest.ui.theme.MiddenTestTheme
import com.example.middentest.ui.theme.openSansFontFamily
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.skydoves.landscapist.glide.GlideImage


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserProfileScreen(
    user: UserInfo = UserInfo(),
    paddingValues: PaddingValues,
    onClickOnIcon: () -> Unit = {}
) {
    LazyColumn(contentPadding = PaddingValues(bottom = paddingValues.calculateBottomPadding())) {
        item {
            BannerProfilePicture(thumbnailUrl = user.picture?.large ?: "", onClickOnIcon)
        }
        item {
            ProfileListItem(
                iconId = R.drawable.ic_user,
                title = stringResource(id = R.string.name_label),
                subtitle = user.name.toString()
            )
        }
        item {
            ProfileListItem(
                iconId = R.drawable.ic_mail,
                title = stringResource(id = R.string.email_label),
                subtitle = user.email ?: ""
            )
        }
        item {
            ProfileListItem(
                iconId = R.drawable.ic_female,
                title = stringResource(id = R.string.gender_label),
                subtitle = castGender(user.gender ?: "")
            )
        }
        item {
            ProfileListItem(
                iconId = R.drawable.ic_calendar,
                title = stringResource(id = R.string.register_date_label),
                subtitle = formatDate(user.registered?.date ?: "")
            )
        }
        item {
            ProfileListItem(
                iconId = R.drawable.ic_phone,
                title = stringResource(id = R.string.phone_label),
                subtitle = user.phone ?: ""
            )
        }
        item {
            AddressItem(user = user)
        }
    }
}

@Composable
private fun BannerProfilePicture(thumbnailUrl: String, onClickOnIcon: () -> Unit = {}) {
    Box(Modifier.height(238.dp)) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Column {
                GlideImage(
                    imageModel = "https://picsum.photos/2500/1667",
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopStart,
                    modifier = Modifier.height(200.dp),
                    placeHolder = painterResource(id = R.drawable.img_banner_placeholder)
                )
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    IconButton(onClick = { onClickOnIcon.invoke() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = "",
                        )
                    }
                    IconButton(onClick = { onClickOnIcon.invoke() }) {
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
            GlideImage(
                imageModel = thumbnailUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopStart,
                modifier = Modifier
                    .size(77.dp)
                    .border(
                        border = BorderStroke(3.dp, MaterialTheme.colorScheme.background),
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                placeHolder = painterResource(id = R.drawable.img_user_thumbnail_placeholder)
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

@Composable
fun AddressItem(user: UserInfo) {
    val coordinates = LatLng(
        user.location?.coordinates?.latitude?.toDouble() ?: 0.0,
        user.location?.coordinates?.longitude?.toDouble() ?: 0.0
    )
    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(coordinates, 10f)
    }
    Column(modifier = Modifier.padding(start = 77.dp, end = 16.dp, top = 17.dp)) {
        Column {
            Text(
                text = stringResource(id = R.string.adress_label),
                color = GreyTitleProfile,
                fontFamily = openSansFontFamily,
                fontStyle = FontStyle.Normal,
                fontSize = 11.sp
            )
            Spacer(modifier = Modifier.height(11.dp))
            GoogleMap(
                cameraPositionState = cameraPosition,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(143.dp)
            ) {
                Marker(
                    state = MarkerState(position = coordinates),
                    title = user.location?.street?.toString(),
                    snippet = user.location?.country,
                    icon = bitmapDescriptorFromVector(LocalContext.current, R.drawable.ic_marker)
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
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
            UserProfileScreen(paddingValues = PaddingValues())
        }
    }
}