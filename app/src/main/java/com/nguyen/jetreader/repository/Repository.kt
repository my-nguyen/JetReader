package com.nguyen.jetreader.repository

import com.nguyen.jetreader.data.Resource
import com.nguyen.jetreader.model.Book
import com.nguyen.jetreader.network.BookService
import javax.inject.Inject

class Repository @Inject constructor(private val service: BookService) {

    suspend fun getAllBooks(query: String): Resource<List<Book>> {
        return try {
            Resource.Loading(data = true)
            val data = service.getAllBooks(query).items
            if (data.isNotEmpty()) Resource.Loading(data = false)
            Resource.Success(data = data)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }

    suspend fun getBookById(bookId: String): Resource<Book> {
        return try {
            Resource.Loading(data = true)
            val data = service.getBookById(bookId)
            Resource.Success(data = data)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}