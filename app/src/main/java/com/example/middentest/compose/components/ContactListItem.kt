package com.example.middentest.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.middentest.R
import com.example.middentest.ui.theme.GreyHorizontalDivider
import com.example.middentest.ui.theme.GreyIcon
import com.example.middentest.ui.theme.GreySubtitle
import com.example.middentest.ui.theme.sfDisplayFontFamily
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ContactListItem(
    name: String,
    email: String,
    imageURL: String,
    onClick: () -> Unit = {}) {
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
                        imageModel = imageURL,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(64.dp),
                        placeHolder = painterResource(id = R.drawable.img_user_thumbnail_placeholder)
                    )
                }
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = name,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = sfDisplayFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = email,
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