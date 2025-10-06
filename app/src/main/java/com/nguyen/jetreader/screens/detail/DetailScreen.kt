package com.nguyen.jetreader.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.nguyen.jetreader.components.ReaderAppBar
import com.nguyen.jetreader.data.Resource
import com.nguyen.jetreader.model.Book
import com.nguyen.jetreader.navigation.ReaderScreens

@Composable
fun DetailScreen(
    navController: NavController,
    bookId: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        ReaderAppBar(
            title = "Book Details",
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            showProfile = false,
            navController = navController
        ) {
            navController.navigate(ReaderScreens.SearchScreen.name)
        }
    }) { padding ->
        Surface(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(top = 12.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val book = produceState(initialValue = Resource.Loading()) {
                    value = viewModel.getBookById(bookId)
                }.value
                if (book.data == null) {
                    Row {
                        LinearProgressIndicator()
                        Text("Loading...")
                    }
                } else {
                    BookDetail(book.data!!, navController)
                }
            }
        }
    }
}

@Composable
fun BookDetail(book: Book, navController: NavController) {
    val volumeInfo = book.volumeInfo
    val bookId = book.id

    Card(
        modifier = Modifier.padding(34.dp),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = volumeInfo?.imageLinks?.thumbnail),
            contentDescription = "Book Image",
            modifier = Modifier
                .size(90.dp)
                .padding(1.dp)
        )
    }
    Text(
        text = volumeInfo.title,
        style = MaterialTheme.typography.titleMedium,
        overflow = TextOverflow.Ellipsis,
        maxLines = 19
    )
    Text(text = "Authors: ${volumeInfo.authors}")
    Text(text = "Page Count: ${volumeInfo.pageCount}")
    Text(text = "Categories: ${volumeInfo.categories}", style = MaterialTheme.typography.titleSmall)
    Text(
        text = "Published: ${volumeInfo.publishedDate}",
        style = MaterialTheme.typography.titleSmall
    )
    Spacer(modifier = Modifier.height(5.dp))
}