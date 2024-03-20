package com.loc.newsapp.presentation.rssfeed.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.Feed
import com.loc.newsapp.domain.model.FollowedFeed
import com.loc.newsapp.presentation.Dimens
import com.loc.newsapp.presentation.common.ArticleCard
import com.loc.newsapp.presentation.common.EmptyScreen

@Composable
fun FeedsList(
    feeds: List<Feed>,
    followedFeeds: List<FollowedFeed>,
    modifier: Modifier = Modifier,
    onUnfollowClick: (String) -> Unit,
    onFollowClicked: (String) -> Unit
) {
    // Create a set of followed feed IDs for efficient lookup
    val followedFeedIds = followedFeeds.map { it.feedId }.toSet()

    // Sort the feeds list so that followed feeds come first
    val sortedFeeds = feeds.sortedBy { !followedFeedIds.contains(it.id) }

    if (sortedFeeds.isEmpty()) {
        EmptyScreen()
    } else {
        LazyColumn(
            contentPadding = PaddingValues(Dimens.ExtraSmallPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
            modifier = modifier.fillMaxSize()
        ) {
            items(sortedFeeds.size) { index ->
                val feed = sortedFeeds[index]

                FeedCard(
                    name = feed.name,
                    isFollowed = followedFeedIds.contains(feed.id),
                    onUnfollowClick = {
                        val followedFeed = followedFeeds.find { it.feedId == feed.id }
                        if (followedFeed != null) {
                            onUnfollowClick(followedFeed.id)
                        }
                    },
                    onFollowClicked = { onFollowClicked(feed.id) }
                )
            }
        }
    }
}