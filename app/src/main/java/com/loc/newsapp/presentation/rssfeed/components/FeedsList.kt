package com.loc.newsapp.presentation.rssfeed.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.Feed
import com.loc.newsapp.presentation.Dimens
import com.loc.newsapp.presentation.common.ArticleCard
import com.loc.newsapp.presentation.common.EmptyScreen

@Composable
fun FeedsList(
    feeds: List<Feed>,
    onClick: (Feed) -> Unit,
    modifier: Modifier = Modifier, onUnfollowClick: (Feed) -> Unit,
) {
    if (feeds.isEmpty()) {
        EmptyScreen()
    }
    LazyColumn(
        contentPadding = PaddingValues(Dimens.ExtraSmallPadding),
        verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
        modifier = modifier.fillMaxSize()
    ) {
        items(feeds.size) { index ->
            val feed = feeds[index]
            FeedCard(
                name = feed.name,
                onFeedClick = { onClick(feed) },
                onUnfollowClick = { onUnfollowClick(feed) })


        }
    }

}