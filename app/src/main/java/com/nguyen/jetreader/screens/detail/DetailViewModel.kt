package com.nguyen.jetreader.screens.detail

import androidx.lifecycle.ViewModel
import com.nguyen.jetreader.data.Resource
import com.nguyen.jetreader.model.Book
import com.nguyen.jetreader.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    suspend fun getBookById(bookId: String): Resource<Book> = repository.getBookById(bookId)
}