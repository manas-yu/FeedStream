package com.loc.newsapp.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.loc.newsapp.domain.usecases.rss.RssUseCases
import com.loc.newsapp.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val rssUseCases: RssUseCases,
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state
    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set
    var navigateToHomeState by mutableStateOf(false)
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.GetUser -> {
                viewModelScope.launch {
                    if (_state.value.inputName.isEmpty()) {
                        sideEffect = UIComponent.Toast("Name is empty")

                        return@launch
                    }

                    val user = rssUseCases.getUser(_state.value.inputName)
                    if (user.message != null) {
                        sideEffect = UIComponent.Toast("Error: ${user.message}")
                        println("Error: ${user.message}")

                        return@launch
                    }
                    navigateToHomeState = true
                    println("user: ${user.data?.name}, ${user.data?.apiKey}, ${user.data?.id}, ${user.data?.createdAt}, ${user.data?.updatedAt}")
                    appEntryUseCases.saveUserApi(user.data?.apiKey!!)
                    sideEffect = UIComponent.Toast("Welcome ${user.data.name}!!")

                }
            }

            is LoginEvent.SetUser -> {
                viewModelScope.launch {
                    if (_state.value.inputName.isEmpty()) {
                        sideEffect = UIComponent.Toast("Name is empty")

                        return@launch
                    }

                    val user = rssUseCases.setUser(_state.value.inputName)
                    if (user.message != null) {
                        sideEffect = UIComponent.Toast("Error: ${user.message}")

                        return@launch
                    }
                    navigateToHomeState = true
                    println("user: ${user.data?.name}, ${user.data?.apiKey}, ${user.data?.id}, ${user.data?.createdAt}, ${user.data?.updatedAt}")
                    appEntryUseCases.saveUserApi(user.data?.apiKey!!)
                    sideEffect = UIComponent.Toast("Welcome ${user.data.name}!!")

                }
            }

            is LoginEvent.RemoveSideEffect -> {
                sideEffect = null
            }

            is LoginEvent.UpdateName -> {
                _state.value = _state.value.copy(inputName = event.inputName)
            }
        }
    }
}