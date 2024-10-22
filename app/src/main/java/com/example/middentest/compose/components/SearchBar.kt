package com.example.middentest.compose.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.middentest.R
import com.example.middentest.data.models.UserInfo
import com.example.middentest.ui.theme.GreyHorizontalDivider
import com.example.middentest.ui.theme.GreyIcon
import com.example.middentest.ui.theme.GreySubtitle
import com.example.middentest.ui.theme.MiddenTestTheme
import com.example.middentest.ui.theme.sfDisplayFontFamily

@Composable
fun SearchBar(
    text: MutableState<String>,
    onSearchInit: (String) -> List<UserInfo>,
    onClickOnSearched: (String) -> Unit,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    Column(Modifier.wrapContentHeight().background(MaterialTheme.colorScheme.background)) {
        Spacer(modifier = Modifier.height(45.dp).background(MaterialTheme.colorScheme.background))
        LazyColumn(modifier = Modifier.wrapContentHeight(), verticalArrangement = Arrangement.Bottom) {
            item {
                SearchBarConfig(
                    text = text,
                    onTextChange = {
                        onTextChange.invoke(it)
                        expanded.value = true
                    },
                    onCloseClicked = onCloseClicked
                )
            }
            item {
                AnimatedVisibility(visible = expanded.value) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background,
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier.heightIn(max = 150.dp),
                        ) {
                            items(onSearchInit.invoke(text.value)) { item ->
                                ItemSearchSuggestion(
                                    userName = item.name.toString(),
                                    userEmail = item.email ?: "",
                                    onClick = onClickOnSearched
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchBarConfig(
    text: MutableState<String>,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(),
        tonalElevation = 0.dp,
        color = MaterialTheme.colorScheme.background
    ) {
        TextField(
            value = text.value,
            onValueChange = {
                onTextChange.invoke(it)
            },
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(0.5f),
                    enabled = false,
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.value.isNotEmpty()) {
                            onTextChange.invoke("")
                        } else {
                            onCloseClicked.invoke()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                disabledContainerColor = MaterialTheme.colorScheme.background,
                cursorColor = MaterialTheme.colorScheme.primary,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                disabledTextColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 4.dp)
                .border(1.dp, MaterialTheme.colorScheme.onBackground, CircleShape),
            shape = CircleShape,
            textStyle = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun ItemSearchSuggestion(userName: String, userEmail: String, onClick: (String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable { onClick.invoke(userName) }
        .padding(top = 8.dp)
        .padding(horizontal = 16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1.0f)) {
                Text(
                    text = userName,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = sfDisplayFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = userEmail,
                    color = GreySubtitle
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(1.0f), horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_keyboard_arrow_right),
                        contentDescription = "",
                        tint = GreyIcon,
                    )
                }
            }
        }
        HorizontalDivider(thickness = 1.dp, color = GreyHorizontalDivider)
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun SearchAppBarPreview() {
    MiddenTestTheme {
        SearchBar(
            text = remember { mutableStateOf("Some random text y") },
            onSearchInit = { listOf() },
            onTextChange = {},
            onClickOnSearched = {},
            onCloseClicked = {}
        )
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun ItemSearchPreview() {
    MiddenTestTheme {
        Column {
            ItemSearchSuggestion(
                userName = "Alexa Pérez",
                userEmail = "a.perez@example.com",
                onClick = {}
            )
            ItemSearchSuggestion(
                userName = "Alexa Pérez",
                userEmail = "a.perez@example.com",
                onClick = {}
            )
        }
    }
}