package com.loc.newsapp.domain.repository

import com.loc.newsapp.domain.model.User

interface RssRepository {
    suspend fun setUser(name: String): User?
    suspend fun getUser(api: String): User?
}