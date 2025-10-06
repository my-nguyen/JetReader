package com.nguyen.jetreader.repository

import com.nguyen.jetreader.data.MyResult
import com.nguyen.jetreader.model.Book
import com.nguyen.jetreader.network.BookService
import javax.inject.Inject

class Repository @Inject constructor(private val service: BookService) {

    suspend fun getAllBooks(query: String): MyResult<List<Book>> {
        return try {
            val data = service.getAllBooks(query).items
            if (data.isNotEmpty()) MyResult.Success(data)
            else MyResult.Error(Exception("No books found"))
        } catch (e: Exception) {
            MyResult.Error(e)
        }
    }

    suspend fun getBookById(bookId: String): MyResult<Book> {
        return try {
            val data = service.getBookById(bookId)
            if (data.toString().isNotEmpty())
                MyResult.Success(data)
            else
                MyResult.Error(Exception("No books found"))
        } catch (e: Exception) {
            MyResult.Error(e)
        }
    }
}