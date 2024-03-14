package com.loc.newsapp.domain.usecases.rss

import com.loc.newsapp.domain.model.User
import com.loc.newsapp.domain.repository.RssRepository

class GetUser(
    private val rssRepository: RssRepository
) {
    suspend operator fun invoke(api: String): User? {
        return rssRepository.getUser(api)
    }
}