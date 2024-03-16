package com.loc.newsapp.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loc.newsapp.domain.repository.NewsRepository
import com.loc.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.loc.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    var state = mutableStateOf(HomeState())
        private set

    val news = newsUseCases.getNews(
        source = listOf("bbc-news", "abc-news", "al-jazeera-english")
    ).cachedIn(viewModelScope)

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.Logout -> {
                viewModelScope.launch {
                    appEntryUseCases.removeUserApi()
                }
            }

        }
    }
}