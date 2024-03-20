package com.loc.newsapp.domain.usecases.rss

import com.loc.newsapp.data.repository.Result
import com.loc.newsapp.domain.model.Feed
import com.loc.newsapp.domain.model.FollowedFeed
import com.loc.newsapp.domain.repository.RssRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

class GetFollowedFeeds(
    private val rssRepository: RssRepository
) {
    operator fun invoke(token: String): Flow<Result<List<FollowedFeed>>> {
        return rssRepository.getFollowedFeeds("ApiKey $token")
    }
}