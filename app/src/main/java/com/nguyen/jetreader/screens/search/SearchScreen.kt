package com.nguyen.jetreader.screens.search

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.nguyen.jetreader.components.ReaderAppBar
import com.nguyen.jetreader.navigation.ReaderScreens

@Preview
@Composable
fun SearchScreen(navController: NavController = NavController(LocalContext.current)) {
    Scaffold(
        topBar = {
            ReaderAppBar(
                icon = Icons.Default.ArrowBack,
                title = "Search Books",
                showProfile = false,
                navController = navController,
            ) {
                navController.navigate(ReaderScreens.HomeScreen.name)
            }
        },
    ) {

    }
}