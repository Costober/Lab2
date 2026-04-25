// viewmodel/ShoppingViewModel.kt
package com.example.lab2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab2.model.ShoppingItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ShoppingViewModel : ViewModel() {

    private val _items = MutableStateFlow<List<ShoppingItem>>(emptyList())
    val items: StateFlow<List<ShoppingItem>> = _items.asStateFlow()

    private val _inputText = MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText.asStateFlow()

    // Реактивний лічильник
    val remainingCount: StateFlow<Int> = _items
        .map { list -> list.count { !it.isBought } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    fun onTextChange(newText: String) {
        _inputText.value = newText
    }

    fun addItem() {
        val name = _inputText.value.trim()
        if (name.isNotEmpty()) {
            _items.value = _items.value + ShoppingItem(name = name)
            _inputText.value = "" // Очищуємо поле після додавання
        }
    }

    fun toggleStatus(itemId: String) {
        _items.value = _items.value.map {
            if (it.id == itemId) it.copy(isBought = !it.isBought) else it
        }
    }
}