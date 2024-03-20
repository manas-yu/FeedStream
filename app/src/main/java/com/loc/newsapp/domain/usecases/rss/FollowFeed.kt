package com.loc.newsapp.domain.usecases.rss

import com.loc.newsapp.data.repository.Result
import com.loc.newsapp.domain.model.Feed
import com.loc.newsapp.domain.repository.RssRepository
import okhttp3.ResponseBody

class FollowFeed(
    private val rssRepository: RssRepository
) {
    suspend operator fun invoke(token: String, feedId: String): Result<ResponseBody?> {
        return rssRepository.followFeed("ApiKey $token", feedId)
    }
}