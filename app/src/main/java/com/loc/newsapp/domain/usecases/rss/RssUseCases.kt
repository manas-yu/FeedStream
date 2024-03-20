package com.loc.newsapp.domain.usecases.rss

data class RssUseCases(
    val setUser: SetUser,
    val getUser: GetUser,
    val getPosts: GetPosts,
    val addFeed: AddFeed,
    val unfollowFeed: UnfollowFeed,
    val getFeeds: GetFeeds,
    val getFollowedFeeds: GetFollowedFeeds,
    val followFeed: FollowFeed,
)
