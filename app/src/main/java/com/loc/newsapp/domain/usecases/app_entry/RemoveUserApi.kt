package com.loc.newsapp.domain.usecases.app_entry

import com.loc.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class RemoveUserApi(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke() {
        return localUserManager.removeUserApi()
    }
}