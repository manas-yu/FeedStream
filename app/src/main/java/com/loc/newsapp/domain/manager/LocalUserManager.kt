package com.loc.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveAppEntry()
    fun readAppEntry(): Flow<Boolean>
    suspend fun saveUserApi(api: String)
    fun readUserApi(): Flow<String>
    suspend fun removeUserApi()
}