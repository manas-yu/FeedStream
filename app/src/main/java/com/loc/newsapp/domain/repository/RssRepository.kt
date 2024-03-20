package com.loc.newsapp.domain.repository

import com.loc.newsapp.data.repository.Result
import com.loc.newsapp.domain.model.Feed
import com.loc.newsapp.domain.model.FollowedFeed
import com.loc.newsapp.domain.model.Post
import com.loc.newsapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface RssRepository {


    suspend fun setUser(name: String): Result<User?>
    suspend fun getUser(name: String): Result<User?>
    suspend fun addFeed(token: String, name: String, url: String): Result<Feed?>

    suspend fun followFeed(token: String, feedId: String): Result<ResponseBody?>
    fun getFeeds(): Flow<Result<List<Feed>>>
    fun getFollowedFeeds(token: String): Flow<Result<List<FollowedFeed>>>
    fun getPosts(token: String): Flow<Result<List<Post>>>
    suspend fun unfollowFeed(token: String, id: String): Result<ResponseBody?>

}