package com.loc.newsapp.data.repository

import com.loc.newsapp.data.remote.RssApi
import com.loc.newsapp.domain.model.User
import com.loc.newsapp.domain.repository.RssRepository
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class RssRepositoryImpl(
    private val rssApi: RssApi
) : RssRepository {
    override suspend fun setUser(name: String): Result<User?> {
        return try {
            val jsonBody = JSONObject().apply {
                put("name", name)
            }
            val requestBody = jsonBody.toString().toRequestBody("application/json".toMediaType())
            Result.Success(rssApi.setUser(requestBody))
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun getUser(api: String): Result<User?> {
        return try {
            Result.Success(rssApi.getUser(api))
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }
}