package com.nguyen.jetreader.model

data class Books(
    val items: List<Book>,
    val kind: String,
    val totalItems: Int
)