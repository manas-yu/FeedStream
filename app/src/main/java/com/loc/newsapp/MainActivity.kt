package com.loc.newsapp

import android.os.Bundle
import android.provider.CalendarContract.Colors

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.loc.newsapp.presentation.mainActivity.MainViewModel

import com.loc.newsapp.presentation.navgraph.NavGraph

import com.loc.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = { viewModel.splashCondition.value })
        }
        setContent {
            NewsAppTheme(dynamicColor = false) {
                // set color of status bar and navigation bar
                
                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()
                SideEffect {
                    systemController.setStatusBarColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )

                }
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                    NavGraph(startDestination = viewModel.startDestination.value)
                }
            }
        }
    }


}
