package com.loc.newsapp.domain.usecases.rss

import com.loc.newsapp.data.repository.Result
import com.loc.newsapp.domain.model.Feed
import com.loc.newsapp.domain.repository.RssRepository
import kotlinx.coroutines.flow.Flow

class GetFeeds(
    private val rssRepository: RssRepository
) {
    operator fun invoke(): Flow<Result<List<Feed>>> {
        return rssRepository.getFeeds()
    }
}