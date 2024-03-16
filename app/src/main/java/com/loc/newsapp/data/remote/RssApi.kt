package com.loc.newsapp.data.remote

import com.loc.newsapp.domain.model.User
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface RssApi {
    @POST("users")
    suspend fun setUser(@Body body: RequestBody): User?

    @GET("users/{username}")
    suspend fun getUser(@Path("username") name: String): User?


}