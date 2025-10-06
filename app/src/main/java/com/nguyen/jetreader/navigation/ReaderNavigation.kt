package com.nguyen.jetreader.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nguyen.jetreader.screens.SplashScreen
import com.nguyen.jetreader.screens.detail.DetailScreen
import com.nguyen.jetreader.screens.home.HomeScreen
import com.nguyen.jetreader.screens.login.LoginScreen
import com.nguyen.jetreader.screens.search.SearchScreen
import com.nguyen.jetreader.screens.search.SearchViewModel
import com.nguyen.jetreader.screens.stats.StatsScreen
import com.nguyen.jetreader.screens.update.UpdateScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name) {
        composable(ReaderScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(ReaderScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(ReaderScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }

        composable(ReaderScreens.SearchScreen.name) {
            val viewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(navController = navController, viewModel)
        }

        composable(ReaderScreens.DetailScreen.name) {
            DetailScreen(navController = navController)
        }

        composable(ReaderScreens.UpdateScreen.name) {
            UpdateScreen(navController = navController)
        }

        composable(ReaderScreens.StatsScreen.name) {
            StatsScreen(navController = navController)
        }
    }
}