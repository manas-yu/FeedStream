package com.loc.newsapp.domain.manager

import com.loc.newsapp.domain.model.User

object UserDataManager {
    private var user: User? = null

    fun setUser(user: User?) {
        this.user = user
    }

    fun getUser(): User? {
        return user
    }

    // Additional methods for accessing individual user properties if needed
    fun getApiKey(): String? {
        return user?.apiKey
    }

    fun setApiKey(apiKey: String) {
        this.user = User(apiKey, "null", "null", "null", "null")
    }

    // Add more getter methods as needed for other user properties
}