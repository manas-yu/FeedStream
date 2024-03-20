package com.loc.newsapp.data.remote

import com.loc.newsapp.domain.model.Feed
import com.loc.newsapp.domain.model.Post
import com.loc.newsapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface RssApi {
    @POST("users")
    suspend fun setUser(@Body body: RequestBody): User?

    @GET("users/{username}")
    suspend fun getUser(@Path("username") name: String): User?

    @POST("feeds")
    suspend fun addFeed(@Header("Authorization") token: String, @Body body: RequestBody): Feed?


    @POST("feed_follows")
    suspend fun followFeed(
        @Header("Authorization") token: String,
        @Body body: RequestBody
    ): ResponseBody?


    @DELETE("feed_follows/{id}")
    suspend fun unfollowFeed(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): ResponseBody?


}