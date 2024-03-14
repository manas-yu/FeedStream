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
    override suspend fun setUser(name: String): User? {
        val jsonBody = JSONObject().apply {
            put("name", name)
        }
        val requestBody = jsonBody.toString().toRequestBody("application/json".toMediaType())

        return rssApi.setUser(requestBody)

    }

    override suspend fun getUser(api: String): User? {
        return rssApi.getUser(api)
    }
}