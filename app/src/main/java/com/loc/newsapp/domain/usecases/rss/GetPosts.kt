package com.loc.newsapp.domain.usecases.rss

import com.loc.newsapp.data.repository.Result
import com.loc.newsapp.domain.model.Post
import com.loc.newsapp.domain.repository.RssRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

class GetPosts(
    private val rssRepository: RssRepository
) {
    operator fun invoke(token: String): Flow<Result<List<Post>>> {
        return rssRepository.getPosts("ApiKey $token")
    }
}