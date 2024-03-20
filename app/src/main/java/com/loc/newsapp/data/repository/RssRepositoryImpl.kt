package com.loc.newsapp.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loc.newsapp.data.remote.RssApi
import com.loc.newsapp.domain.manager.UserDataManager
import com.loc.newsapp.domain.model.Feed
import com.loc.newsapp.domain.model.FollowedFeed
import com.loc.newsapp.domain.model.Post
import com.loc.newsapp.domain.model.User
import com.loc.newsapp.domain.repository.RssRepository
import com.loc.newsapp.util.Constants.RSS_BASE_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

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

    override suspend fun getUser(name: String): Result<User?> {
        return try {

            Result.Success(rssApi.getUser(name))
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun addFeed(token: String, name: String, url: String): Result<Feed?> {
        return try {
            val jsonBody = JSONObject().apply {
                put("name", name)
                put("url", url)
            }
            val requestBody = jsonBody.toString().toRequestBody("application/json".toMediaType())
            Result.Success(rssApi.addFeed(token, requestBody))
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }


    override suspend fun followFeed(token: String, feedId: String): Result<ResponseBody?> {
        return try {
            val jsonBody = JSONObject().apply {
                put("feedId", feedId)
            }
            val requestBody = jsonBody.toString().toRequestBody("application/json".toMediaType())
            Result.Success(rssApi.followFeed(token, requestBody))
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }

    override fun getFeeds(): Flow<Result<List<Feed>>> {
        return flow {
            coroutineScope {
                try {
                    val url = RSS_BASE_URL + "feeds"
                    val conn = (URL(url).openConnection() as HttpURLConnection).also {
                        it.setRequestProperty(
                            "Accept",
                            "text/event-stream"
                        ) // set this Header to stream
                        it.doInput = true // enable inputStream
                    }

                    conn.connect()
                    val inputReader = conn.inputStream.bufferedReader()
                    while (isActive) {
                        val line = inputReader.readLine()
                        when {

                            line.startsWith("data: ") -> {
                                val json = line.substring(5).trim()
                                val feeds = parseJsonToFeeds(json)
                                emit(Result.Success(feeds))
                            }

                        }
                    }
                } catch (e: Exception) {
                    emit(Result.Error(e.message ?: "Unknown error"))
                }
            }
        }
    }

    override fun getFollowedFeeds(token: String): Flow<Result<List<FollowedFeed>>> {
        return flow {
            coroutineScope {
                try {
                    val url = RSS_BASE_URL + "feed_follows"
                    val conn = (URL(url).openConnection() as HttpURLConnection).also {
                        it.setRequestProperty("Accept", "text/event-stream")
                        it.doInput = true
                        it.setRequestProperty("Authorization", token)
                    }
                    conn.connect()
                    val inputReader = conn.inputStream.bufferedReader()
                    while (isActive) {
                        val line = inputReader.readLine()
                        if (line.startsWith("data: ")) {
                            val json = line.substring(5).trim()
                            val feeds = parseJsonToFollowedFeeds(json)
                            emit(Result.Success(feeds))
                        }
                    }
                } catch (e: Exception) {
                    emit(Result.Error(e.message ?: "Unknown error"))
                }

            }
        }
    }

    override fun getPosts(token: String): Flow<Result<List<Post>>> {
        return flow {
            coroutineScope {
                try {
                    val url = RSS_BASE_URL + "posts"
                    val conn = (URL(url).openConnection() as HttpURLConnection).also {
                        it.setRequestProperty("Accept", "text/event-stream")
                        it.doInput = true
                        it.setRequestProperty("Authorization", token)
                    }
                    conn.connect()
                    val inputReader = conn.inputStream.bufferedReader()
                    while (isActive) {
                        val line = inputReader.readLine()
                        if (line.startsWith("data: ")) {
                            val json = line.substring(5).trim()
                            val posts = parseJsonToPosts(json)
                            emit(Result.Success(posts))
                        }
                    }
                } catch (e: Exception) {
                    emit(Result.Error(e.message ?: "Unknown error"))
                }
            }
        }
    }


    override suspend fun unfollowFeed(token: String, id: String): Result<ResponseBody?> {
        return try {
            Result.Success(rssApi.unfollowFeed(token, id))
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }

    private fun parseJsonToFeeds(json: String): List<Feed> {
        val listType = object : TypeToken<List<Feed>>() {}.type
        return Gson().fromJson(json, listType)
    }

    private fun parseJsonToFollowedFeeds(json: String): List<FollowedFeed> {
        val listType = object : TypeToken<List<FollowedFeed>>() {}.type
        return Gson().fromJson(json, listType)
    }

    private fun parseJsonToPosts(json: String): List<Post> {
        val listType = object : TypeToken<List<Post>>() {}.type
        return Gson().fromJson(json, listType)
    }


}