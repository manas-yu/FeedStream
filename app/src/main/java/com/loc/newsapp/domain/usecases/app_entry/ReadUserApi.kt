package com.loc.newsapp.domain.usecases.app_entry

import com.loc.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadUserApi(private val localUserManager: LocalUserManager) {
    operator fun invoke(): Flow<String> {
        return localUserManager.readUserApi()
    }
}