package com.loc.newsapp.domain.repository

import com.loc.newsapp.data.repository.Result
import com.loc.newsapp.domain.model.User

interface RssRepository {
    suspend fun setUser(name: String): Result<User?>
    suspend fun getUser(name: String): Result<User?>
}