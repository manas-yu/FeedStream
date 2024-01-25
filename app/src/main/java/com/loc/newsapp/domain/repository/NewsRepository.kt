package com.loc.newsapp.domain.repository

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(source: List<String>): Flow<PagingData<Article>>
}