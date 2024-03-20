package com.loc.newsapp.presentation.rssfeed

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.NewsApplication
import com.loc.newsapp.data.repository.Result
import com.loc.newsapp.domain.manager.UserDataManager
import com.loc.newsapp.domain.usecases.rss.RssUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

@HiltViewModel
class RssViewModel @Inject constructor(
    private val rssUseCases: RssUseCases,
) : ViewModel() {
    private val _state = mutableStateOf(RssState())
    val state: State<RssState> = _state

    init {
        UserDataManager.getApiKey()?.let {
            getPosts(it)
            getFeeds()
            getFeedFollows(it)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getPosts(token: String) {

        GlobalScope.launch(Dispatchers.IO) {
            try {
                rssUseCases.getPosts(token).catch { exception ->
                    println(exception.message)
                    _state.value = state.value.copy(posts = emptyList())
                }.collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _state.value = state.value.copy(posts = result.data ?: emptyList())
//                        for (post in result.data!!) {
//                            println(post.title)
//                        }
                        }

                        is Result.Error -> {
                            println(result.message)
                            _state.value = state.value.copy(posts = emptyList())
                        }
                    }
                }
            } catch (e: Exception) {
                println(e.message)
                _state.value = state.value.copy(posts = emptyList())
            }

        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getFeeds() {
        println("getting Feeds")
        GlobalScope.launch(Dispatchers.IO) {
            try {
                rssUseCases.getFeeds().catch { exception ->
                    println(exception.message)
                    _state.value = state.value.copy(feeds = emptyList())
                }.collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _state.value = state.value.copy(feeds = result.data ?: emptyList())
//                        for (feed in result.data!!) {
//                            println(feed.name)
//                        }
                        }

                        is Result.Error -> {
                            println(result.message)
                            _state.value = state.value.copy(feeds = emptyList())
                        }
                    }
                }
            } catch (e: Exception) {
                println(e.message)
                _state.value = state.value.copy(posts = emptyList())
            }

        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getFeedFollows(token: String) {
        println("getting followed Feeds")
        GlobalScope.launch(Dispatchers.IO) {
            try {
                rssUseCases.getFollowedFeeds(token).catch { exception ->
                    println(exception.message)
                    _state.value = state.value.copy(followedFeeds = emptyList())
                }.collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _state.value =
                                state.value.copy(followedFeeds = result.data ?: emptyList())
//                        for (feed in result.data!!) {
//                            println(feed.name)
//                        }
                        }

                        is Result.Error -> {
                            println(result.message)
                            _state.value = state.value.copy(followedFeeds = emptyList())
                        }
                    }
                }
            } catch (e: Exception) {
                println(e.message)
                _state.value = state.value.copy(posts = emptyList())
            }

        }

    }

    fun onEvent(event: RssEvent) {
        when (event) {
            is RssEvent.FollowFeed -> {
                viewModelScope.launch(Dispatchers.IO) {
                    println("following feed ${event.feedId}")
                    rssUseCases.followFeed(
                        token = UserDataManager.getApiKey()!!,
                        feedId = event.feedId
                    )
                }
            }

            is RssEvent.AddFeed -> {
                viewModelScope.launch(Dispatchers.IO) {
                    rssUseCases.addFeed(
                        token = UserDataManager.getApiKey()!!,
                        name = event.feedName,
                        url = event.feedUrl
                    )
                }
            }

            is RssEvent.UnFollowFeed -> {
                viewModelScope.launch(Dispatchers.IO) {
                    println("unfollowing feed ${event.feedId}")
                    rssUseCases.unfollowFeed(
                        token = UserDataManager.getApiKey()!!,
                        id = event.feedId
                    )
                }
            }

            is RssEvent.UpdateUrl -> {
                _state.value = state.value.copy(inputUrl = event.inputUrl)
            }

            is RssEvent.UpdateName -> {
                _state.value = state.value.copy(inputName = event.inputName)
            }
        }
    }

}

