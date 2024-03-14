package com.loc.newsapp.presentation.login

import com.loc.newsapp.presentation.search.SearchEvent

sealed class LoginEvent {
    data class UpdateName(val inputName: String) : LoginEvent()
    object SetUser : LoginEvent()
    object GetUser : LoginEvent()
}