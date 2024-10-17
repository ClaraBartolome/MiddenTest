package com.example.middentest.compose.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.middentest.R
import com.example.middentest.ui.theme.CustomTypography
import com.example.middentest.ui.theme.MiddenTestTheme
import com.example.middentest.ui.theme.sfDisplayFontFamily

@Composable
fun ContactList() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .padding(top = 16.dp)){
        Image(painter = painterResource(R.drawable.img_avatar_placeholder), contentDescription = "",contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(52.dp))
        Column (modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(text = "Andrés Martinez",
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = sfDisplayFontFamily,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "UserDetail", color = MaterialTheme.colorScheme.onBackground)
        }

        Box(modifier = Modifier.fillMaxWidth()){
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_keyboard_arrow_right),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }

}

@Composable
private fun ContactListItem(){
    Row(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://i.pravatar.cc/150")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.img_avatar_placeholder),
            contentDescription = "",//stringResource(R.string.description),
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape)
        )

        Column {
            Text(text = "UserDetail", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, apiLevel = 33, locale = "es", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PrevMainScreen() {
    MiddenTestTheme {
        Surface {
            ContactList()
        }
    }
}