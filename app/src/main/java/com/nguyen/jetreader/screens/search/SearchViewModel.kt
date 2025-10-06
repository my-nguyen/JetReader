package com.nguyen.jetreader.screens.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nguyen.jetreader.data.DataOrException
import com.nguyen.jetreader.model.Book
import com.nguyen.jetreader.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val books: MutableState<DataOrException<List<Book>, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        searchBooks("android")
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            if (query.isEmpty()) return@launch

            books.value.loading = true
            books.value = repository.getAllBooks(query)
            Log.d("TAGG", "searchBooks: ${books.value.data.toString()}")
            if (books.value.data.toString().isNotEmpty())
                books.value.loading = false
        }
    }
}