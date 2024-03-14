package com.loc.newsapp.domain.model

data class Feed(
    val created_at: String,
    val id: String,
    val name: String,
    val updated_at: String,
    val url: String,
    val user_id: String
)