package com.nguyen.jetreader.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName

data class MyBook(
    @Exclude var id: String? = null,

    var title: String? = null,
    var authors: String? = null,
    var notes: String? = null,
    var categories: String? = null,

    @set:PropertyName("photo_url")
    var photoUrl: String? = null,

    @set:PropertyName("published_date")
    var publishedDate: String? = null,

    var rating: Double? = null,
    var description: String? = null,

    @set:PropertyName("page_count")
    var pageCount: String? = null,

    @set:PropertyName("started_reading")
    var startedReading: Timestamp? = null,

    @set:PropertyName("finished_reading")
    var finishedReading: Timestamp? = null,

    @set:PropertyName("user_id")
    var userId: String? = null,

    @set:PropertyName("google_book_id")
    var googleBookId: String? = null
)
