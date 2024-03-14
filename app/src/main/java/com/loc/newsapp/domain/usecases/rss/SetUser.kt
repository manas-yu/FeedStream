package com.loc.newsapp.domain.usecases.rss

import com.loc.newsapp.domain.model.User
import com.loc.newsapp.domain.repository.RssRepository

class SetUser(
    private val rssRepository: RssRepository
) {
    suspend operator fun invoke(name: String): User? {
        return rssRepository.setUser(name)
    }
}