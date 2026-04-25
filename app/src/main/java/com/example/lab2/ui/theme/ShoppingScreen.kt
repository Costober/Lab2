package com.example.lab2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lab2.viewmodel.ShoppingViewModel

@Composable
fun ShoppingScreen(viewModel: ShoppingViewModel) {
    // Підписка на стан з ViewModel
    val items by viewModel.items.collectAsStateWithLifecycle()
    val inputText by viewModel.inputText.collectAsStateWithLifecycle()
    val count by viewModel.remainingCount.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Залишилось купити: $count", style = MaterialTheme.typography.headlineSmall)

        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            TextField(
                value = inputText,
                onValueChange = { viewModel.onTextChange(it) },
                modifier = Modifier.weight(1f),
                label = { Text("Назва товару") }
            )
            Button(
                onClick = { viewModel.addItem() },
                enabled = inputText.isNotBlank(),
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Додати")
            }
        }

        LazyColumn {
            items(items, key = { it.id }) { item ->
                ShoppingItemCard(item) {
                    viewModel.toggleStatus(item.id)
                }
            }
        }
    }
}