package com.nguyen.jetreader.screens.detail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nguyen.jetreader.components.ReaderAppBar
import com.nguyen.jetreader.data.Resource
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
                if (book.data == null)
                    LinearProgressIndicator()
                else
                    Text("Title: ${book.data.volumeInfo.title}")
                Log.d("TAGG", "DetailScreen: ${book.data}")
            }
        }
    }
}