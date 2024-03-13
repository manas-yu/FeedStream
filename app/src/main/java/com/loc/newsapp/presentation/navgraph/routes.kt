package com.loc.newsapp.presentation.navgraph

sealed class Route(val route: String) {
    object RssFeedScreen : Route("rss_feed_screen")
    object LoginScreen : Route("login_screen")
    object OnBoardingScreen : Route("onboarding_screen")
    object HomeScreen : Route("home_screen")
    object SearchScreen : Route("search_screen")
    object DetailScreen : Route("detail_screen")
    object BookmarkScreen : Route("bookmark_screen")
    object NewsNavigation : Route("news_navigation")
    object AppStartNavigation : Route("app_start_navigation")
    object NewsNavigatorScreen : Route("news_navigator_screen")
}