package com.nguyen.jetreader.repository

import com.nguyen.jetreader.data.DataOrException
import com.nguyen.jetreader.model.Book
import com.nguyen.jetreader.network.BookService
import javax.inject.Inject

class Repository @Inject constructor(private val service: BookService) {
    private val books = DataOrException<List<Book>, Boolean, Exception>()
    private val book = DataOrException<Book, Boolean, Exception>()

    suspend fun getAllBooks(query: String): DataOrException<List<Book>, Boolean, Exception> {
        try {
            books.loading = true
            books.data = service.getAllBooks(query).items
            if (books.data!!.isNotEmpty())
                books.loading = false
        } catch (e: Exception) {
            books.e = e
        }
        return books
    }

    suspend fun getBookById(bookId: String): DataOrException<Book, Boolean, Exception> {
        val response = try {
            book.loading = true
            book.data = service.getBookById(bookId)
            if (book.data.toString().isNotEmpty())
                book.loading = false
            else {
            }
        } catch (e: Exception) {
            book.e = e
        }
        return book
    }
}