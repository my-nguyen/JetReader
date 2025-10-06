package com.nguyen.jetreader.network

import com.nguyen.jetreader.model.Books
import com.nguyen.jetreader.model.Book
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface BookService {
    @GET("volumes")
    suspend fun getAllBooks(@Query("q") query: String): Books

    @GET("volumes/{bookId}")
    suspend fun getBookById(@Path("bookId") bookId: String): Book
}