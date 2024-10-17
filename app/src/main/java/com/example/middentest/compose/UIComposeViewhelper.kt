package com.example.middentest.compose

import android.content.Context
import android.widget.Toast


fun createToast(context: Context, label: String = "Próximamente") {
    Toast.makeText(context, label, Toast.LENGTH_SHORT).show()
}
