package com.loc.newsapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.newsapp.data.remote.NewsPagingSource
import com.loc.newsapp.data.remote.dto.NewsApi
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getNews(source: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,

                ),
            pagingSourceFactory = {
                NewsPagingSource(newsApi, source.joinToString(separator = ","))
            }
        ).flow
    }
}

