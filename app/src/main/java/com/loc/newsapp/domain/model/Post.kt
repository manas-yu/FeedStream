package com.loc.newsapp.domain.model

data class PostsItem(
    val created_at: String,
    val description: String,
    val feed_id: String,
    val id: String,
    val published_at: String,
    val title: String,
    val updated_at: String,
    val url: String
)