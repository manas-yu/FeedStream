package com.loc.newsapp.domain.usecases.app_entry

data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry,
    val readUserApi: ReadUserApi,
    val saveUserApi: SaveUserApi,
    val removeUserApi: RemoveUserApi
)
