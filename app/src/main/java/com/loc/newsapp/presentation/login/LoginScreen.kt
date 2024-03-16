package com.loc.newsapp.presentation.login

import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRowDefaults.containerColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.loc.newsapp.R
import com.loc.newsapp.presentation.Dimens.ExtraSmallPadding2
import com.loc.newsapp.presentation.Dimens.MediumPadding1
import com.loc.newsapp.presentation.Dimens.MediumPadding2
import com.loc.newsapp.presentation.common.NewsButton
import com.loc.newsapp.presentation.common.searchBarBorder
import com.loc.newsapp.util.UIComponent


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    state: LoginState,
    navigateToHomeState: Boolean,
    event: (LoginEvent) -> Unit,
    sideEffect: UIComponent?,
    navigateToHome: () -> Unit,
) {


    LaunchedEffect(key1 = navigateToHomeState) {
        when (navigateToHomeState) {
            true -> navigateToHome()
            false -> Unit
        }
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = sideEffect) {
        sideEffect?.let {
            when (sideEffect) {
                is UIComponent.Toast -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    event(LoginEvent.RemoveSideEffect)
                }

                else -> Unit
            }
        }
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val view = LocalView.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MediumPadding2), verticalArrangement = Arrangement.Center
    ) {

        Box(modifier = Modifier) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .searchBarBorder(),
                value = state.inputName,
                onValueChange = {
                    event(LoginEvent.UpdateName(it))
                },
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = containerColor,
                    cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                ),
                textStyle = MaterialTheme.typography.bodySmall,
                placeholder = {
                    Text(
                        text = "Username",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(id = R.color.placeholder)
                    )
                },
                leadingIcon = {

                    Icon(

                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = colorResource(id = R.color.body)
                    )
                },

                )
        }
        Spacer(modifier = Modifier.height(MediumPadding1))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            NewsButton(text = "Login", onClick = {
                event(LoginEvent.GetUser)

                view.clearFocus()
                keyboardController?.hide()

            })
            Spacer(modifier = Modifier.width(ExtraSmallPadding2))
            NewsButton(text = "Signup", onClick = {
                event(LoginEvent.SetUser)

                view.clearFocus()
                keyboardController?.hide()
            })
        }


    }

}

//@Preview(showBackground = true, uiMode = 1, showSystemUi = true)
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen() {}
//}