package com.loc.newsapp.data.remote

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)