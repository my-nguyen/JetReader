package com.nguyen.jetreader.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.nguyen.jetreader.components.FABContent
import com.nguyen.jetreader.components.ListCard
import com.nguyen.jetreader.components.ReaderAppBar
import com.nguyen.jetreader.components.TitleSection
import com.nguyen.jetreader.model.Book
import com.nguyen.jetreader.navigation.ReaderScreens

// @Preview
@Composable
fun HomeScreen(navController: NavController = NavController(LocalContext.current)) {
    Scaffold(
        topBar = { ReaderAppBar(title = "Bookworm", navController = navController) },
        floatingActionButton = { FABContent {} }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            HomeContent(navController)
        }
    }
}

@Composable
fun HomeContent(navController: NavController) {
    val books = listOf(
        Book(
            id = "one",
            title = "The Client",
            authors = "John Grisham",
            notes = "thriller"
        ),
        Book(
            id = "two",
            title = "The Rape of Nanking",
            authors = "Iris Chang",
            notes = "history"
        ),
        Book(
            id = "three",
            title = "Atomic Habit",
            authors = "James Clear",
            notes = "self-help"
        ),
        Book(
            id = "four",
            title = "Pale Blue Dot",
            authors = "Carl Sagan",
            notes = "universe"
        ),
        Book(
            id = "four",
            title = "A Brief History of Time",
            authors = "Stephen W. Hawking",
            notes = "science"
        ),
    )

    val email = Firebase.auth.currentUser?.email
    val currentUserName =
        if (!email.isNullOrEmpty()) Firebase.auth.currentUser?.email?.split('@')?.get(0)
        else "N/A"

    Column(modifier = Modifier.padding(2.dp), verticalArrangement = Arrangement.Top) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(title = "Your reading \n activity right now...")
            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            Column {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clickable { navController.navigate(ReaderScreens.StatsScreen.name) }
                        .size(45.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = currentUserName!!,
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
                HorizontalDivider()
            }
        }
        ReadingRightNow(listOf(), navController)
        TitleSection(title = "Reading List")
        BookListArea(books, navController)
    }
}

@Composable
fun BookListArea(books: List<Book>, navController: NavController) {
    HorizontalScrollable(books) {
        Log.d("TAGG", "BookListArea: $it")
        // TODO: onCardPressed, navigate to details
    }
}

@Composable
fun HorizontalScrollable(books: List<Book>, onCardPressed: (String) -> Unit = {}) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(280.dp)
            .horizontalScroll(scrollState),
    ) {
        for (book in books) {
            ListCard(book) {
                onCardPressed(it)
            }
        }
    }
}

@Composable
fun ReadingRightNow(listOfBooks: List<Book>, navController: NavController) {
    ListCard()
}