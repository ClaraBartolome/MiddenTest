package com.example.middentest

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.middentest.compose.screens.UICompose
import com.example.middentest.ui.theme.MiddenTestTheme
import com.example.middentest.viewmodels.MainViewModel
import com.example.middentest.viewmodels.MainViewModel.Companion.TAG

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.v("MAIN ACTIVITY", "HOWDY")
        setContent {
            MiddenTestTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    UICompose(viewModel)
                }
            }
        }
    }
}