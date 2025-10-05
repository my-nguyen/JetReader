package com.nguyen.jetreader.navigation

enum class ReaderScreens {
    SplashScreen,
    LoginScreen,
    AccountScreen,
    HomeScreen,
    SearchScreen,
    DetailScreen,
    UpdateScreen,
    StatsScreen;

    companion object {
        fun fromRoute(route: String?): ReaderScreens =
            when (route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                LoginScreen.name -> LoginScreen
                AccountScreen.name -> AccountScreen
                HomeScreen.name -> HomeScreen
                SearchScreen.name -> SearchScreen
                DetailScreen.name -> DetailScreen
                UpdateScreen.name -> UpdateScreen
                StatsScreen.name -> StatsScreen
                null -> SplashScreen
                else -> throw java.lang.IllegalArgumentException("Route $route is not recognized")
            }
    }
}