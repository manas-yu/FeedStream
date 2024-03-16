package com.loc.newsapp.presentation.login


sealed class LoginEvent {
    data class UpdateName(val inputName: String) : LoginEvent()
    object SetUser : LoginEvent()
    object GetUser : LoginEvent()
    object RemoveSideEffect : LoginEvent()
}