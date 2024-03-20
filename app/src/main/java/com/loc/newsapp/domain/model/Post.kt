package com.loc.newsapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val createdAt: String,
    val description: String,
    val feedId: String,
    val id: String,
    val publishedAt: String,
    val title: String,
    val updatedAt: String,
    val url: String
) : Parcelable