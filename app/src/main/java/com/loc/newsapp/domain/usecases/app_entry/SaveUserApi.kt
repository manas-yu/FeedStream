package com.loc.newsapp.domain.usecases.app_entry

import com.loc.newsapp.domain.manager.LocalUserManager

class SaveUserApi(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke(api: String) {
        localUserManager.saveUserApi(api)
    }
}