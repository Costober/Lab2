// MainActivity.kt
package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.lab2.ui.ShoppingScreen
import com.example.lab2.viewmodel.ShoppingViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ShoppingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingScreen(viewModel)
        }
    }
}