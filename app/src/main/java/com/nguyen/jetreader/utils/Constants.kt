package com.nguyen.jetreader.utils

import com.nguyen.jetreader.model.MyBook

object Constants {
    const val BASE_URL = "https://www.googleapis.com/books/v1/"

    val BOOKS = listOf(
        MyBook(
            id = "one",
            title = "The Client",
            authors = "John Grisham",
            notes = "thriller"
        ),
        MyBook(
            id = "two",
            title = "The Rape of Nanking",
            authors = "Iris Chang",
            notes = "history"
        ),
        MyBook(
            id = "three",
            title = "Atomic Habit",
            authors = "James Clear",
            notes = "self-help"
        ),
        MyBook(
            id = "four",
            title = "Pale Blue Dot",
            authors = "Carl Sagan",
            notes = "universe"
        ),
        MyBook(
            id = "four",
            title = "A Brief History of Time",
            authors = "Stephen W. Hawking",
            notes = "science"
        ),
    )

    const val IMAGE_URL =
        "https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=80&q=80"
}