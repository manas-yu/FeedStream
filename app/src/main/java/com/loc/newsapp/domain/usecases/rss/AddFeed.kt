package com.loc.newsapp.domain.usecases.rss

import com.loc.newsapp.data.repository.Result
import com.loc.newsapp.domain.model.Feed
import com.loc.newsapp.domain.model.User
import com.loc.newsapp.domain.repository.RssRepository

class AddFeed(
    private val rssRepository: RssRepository
) {
    suspend operator fun invoke(token: String, name: String, url: String): Result<Feed?> {
        return rssRepository.addFeed("ApiKey $token", name, url)
    }
}