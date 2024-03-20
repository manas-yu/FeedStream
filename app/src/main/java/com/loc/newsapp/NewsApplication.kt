package com.loc.newsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class NewsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
}