package com.example.lab2.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.lab2.model.ShoppingItem

@Composable
fun ShoppingItemCard(item: ShoppingItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(
                text = item.name,
                textDecoration = if (item.isBought) TextDecoration.LineThrough else null,
                modifier = Modifier.weight(1f)
            )
            if (item.isBought) Text("✅")
        }
    }
}