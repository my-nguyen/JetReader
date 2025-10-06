package com.nguyen.jetreader.di

import com.nguyen.jetreader.network.BookService
import com.nguyen.jetreader.repository.Repository
import com.nguyen.jetreader.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideBookService(): BookService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(bookService: BookService): Repository {
        return Repository(bookService)
    }
}