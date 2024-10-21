package com.example.middentest.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.middentest.R
import com.example.middentest.ui.theme.MiddenTestTheme
import com.example.middentest.ui.theme.openSansFontFamily

@Composable
fun CustomDropdownMenu(
    paddingValues: PaddingValues = PaddingValues(),
    isOpen: MutableState<Boolean> = remember { mutableStateOf(true) },
    onSearchClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopEnd)
            .padding(paddingValues)
    ) {
        DropdownMenu(
            expanded = isOpen.value,
            onDismissRequest = { isOpen.value = false },
            modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
        ) {
            DropdownMenuItem(text = {
                Text(
                    text = stringResource(id = R.string.search_label),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontFamily = openSansFontFamily,
                    fontSize = 14.sp
                )
            },
                colors = MenuDefaults.itemColors(

                ),
                onClick = { onSearchClick.invoke() })
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PrevAddExpenseScreen() {
    MiddenTestTheme {
        Scaffold { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding), color = Color.Blue
            ) {
                CustomDropdownMenu()
            }
        }
    }
}