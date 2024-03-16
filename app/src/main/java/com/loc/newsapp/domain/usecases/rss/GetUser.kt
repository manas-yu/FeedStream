package com.loc.newsapp.domain.usecases.rss

import com.loc.newsapp.data.repository.Result
import com.loc.newsapp.domain.model.User
import com.loc.newsapp.domain.repository.RssRepository

class GetUser(
    private val rssRepository: RssRepository
) {
    suspend operator fun invoke(name: String): Result<User?> {
        return rssRepository.getUser(name)
    }
}