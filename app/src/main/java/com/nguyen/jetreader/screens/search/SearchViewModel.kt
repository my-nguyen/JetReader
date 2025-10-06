package com.nguyen.jetreader.screens.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nguyen.jetreader.data.MyResult
import com.nguyen.jetreader.model.Book
import com.nguyen.jetreader.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val books: MutableState<List<Book>> = mutableStateOf(listOf())

    init {
        searchBooks("android")
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            if (query.isEmpty()) return@launch

            val data = repository.getAllBooks(query)
            when (data) {
                is MyResult.Success -> books.value = data.data
                is MyResult.Error -> Log.d("TAGG", "searchBooks: ${data.exception}")
                else -> {}
            }
        }
    }
}