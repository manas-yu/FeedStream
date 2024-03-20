package com.loc.newsapp.domain.model

data class FollowedFeed(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val feedId: String,
    val userId: String,
    val name: String
)
