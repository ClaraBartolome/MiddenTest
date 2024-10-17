package com.example.middentest.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable


fun createToast(context: Context, label: String = "Próximamente") {
    Toast.makeText(context, label, Toast.LENGTH_SHORT).show()
}
