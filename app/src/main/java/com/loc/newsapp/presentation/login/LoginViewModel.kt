package com.loc.newsapp.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.usecases.rss.RssUseCases
import com.loc.newsapp.presentation.search.SearchEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val rssUseCases: RssUseCases) : ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state
    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.GetUser -> {


            }

            is LoginEvent.SetUser -> {
                viewModelScope.launch {
                    if (_state.value.inputName.isEmpty()) {
                        return@launch
                    }
                    val user = rssUseCases.setUser(_state.value.inputName)
                    println("User: ${user?.name}")
                }

            }

            is LoginEvent.UpdateName -> {
                _state.value = _state.value.copy(inputName = event.inputName)
            }


        }

    }

}