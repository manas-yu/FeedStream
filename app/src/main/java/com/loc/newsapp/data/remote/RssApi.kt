package com.loc.newsapp.data.remote

import com.loc.newsapp.domain.model.User
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RssApi {
    @POST("users")
    suspend fun setUser(@Body body: RequestBody): User?

    @GET("users")
    suspend fun getUser(@Header("Authorization") api: String): User?


}