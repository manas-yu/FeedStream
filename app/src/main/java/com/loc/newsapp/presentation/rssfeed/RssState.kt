package com.loc.newsapp.presentation.rssfeed

import com.loc.newsapp.domain.model.Feed
import com.loc.newsapp.domain.model.FollowedFeed
import com.loc.newsapp.domain.model.Post

data class RssState(
    val inputName: String = "",
    val inputUrl: String = "",
    val posts: List<Post> = emptyList(),
    val feeds: List<Feed> = emptyList(),
    val followedFeeds: List<FollowedFeed> = emptyList()
)
