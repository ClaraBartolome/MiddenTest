package com.example.middentest.compose.screens

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.middentest.ui.theme.MiddenTestTheme

@Composable
fun UserProfile() {
    Text(text = "UserDetail", color = MaterialTheme.colorScheme.onBackground)
}

@Preview(showBackground = true, showSystemUi = true, apiLevel = 33, locale = "es", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PrevMainScreen() {
    MiddenTestTheme {
        Surface {
            UserProfile()
        }
    }
}