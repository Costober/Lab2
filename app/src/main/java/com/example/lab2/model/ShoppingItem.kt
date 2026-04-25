package com.example.lab2.model

import java.util.UUID

data class ShoppingItem(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val isBought: Boolean = false
)