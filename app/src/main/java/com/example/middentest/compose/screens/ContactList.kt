package com.example.middentest.compose.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.middentest.R
import com.example.middentest.compose.createToast
import com.example.middentest.ui.theme.CustomTypography
import com.example.middentest.ui.theme.GreyHorizontalDivider
import com.example.middentest.ui.theme.GreyIcon
import com.example.middentest.ui.theme.GreySubtitle
import com.example.middentest.ui.theme.MiddenTestTheme
import com.example.middentest.ui.theme.sfDisplayFontFamily
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ContactList(onClick: () -> Unit = {}) {

    LazyColumn {
        items(10) {
            ContactListItem(onClick)
        }
    }

}

@Composable
private fun ContactListItem(onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp)
            .clickable { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row() {
                Column(modifier = Modifier.fillMaxHeight()) {
                    GlideImage(
                        imageModel = "https://i.pravatar.cc/300",
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(CircleShape).size(64.dp),
                        placeHolder = ColorPainter(Color.Red)
                    )
                }
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = "Andrés Martinez",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = sfDisplayFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "andres.mart@gmail.com",
                        color = GreySubtitle
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = GreyHorizontalDivider, thickness = 1.dp)
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_keyboard_arrow_right),
                            contentDescription = "",
                            tint = GreyIcon,
                        )
                    }
                }
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
            ContactList()
        }
    }
}