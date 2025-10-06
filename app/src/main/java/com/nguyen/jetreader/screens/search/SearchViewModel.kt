package com.nguyen.jetreader.screens.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nguyen.jetreader.data.Resource
import com.nguyen.jetreader.model.Book
import com.nguyen.jetreader.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val books: MutableState<List<Book>> = mutableStateOf(listOf())
    val isLoading: MutableState<Boolean> = mutableStateOf(true)

    init {
        searchBooks("android")
    }

    fun searchBooks(query: String) {
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) return@launch

            try {
                val data = repository.getAllBooks(query)
                when (data) {
                    is Resource.Success -> {
                        books.value = data.data!!
                        if (books.value.isNotEmpty()) isLoading.value = false
                    }
                    is Resource.Error -> Log.d("TAGG", "searchBooks: FAILED")
                    else -> {
                        isLoading.value = false
                    }
                }
            } catch (e: Exception) {
                isLoading.value = false
                Log.d("TAGG", "searchBooks: Exception ${e.message}")
            }
        }
    }
}