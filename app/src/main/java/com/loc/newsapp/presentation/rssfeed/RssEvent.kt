package com.loc.newsapp.presentation.rssfeed

sealed class RssEvent {
    data class FollowFeed(val feedId: String) : RssEvent()
    data class UnFollowFeed(val feedId: String) : RssEvent()
    data class AddFeed(val feedUrl: String, val feedName: String) : RssEvent()
    data class UpdateName(val inputName: String) : RssEvent()
    data class UpdateUrl(val inputUrl: String) : RssEvent()
}