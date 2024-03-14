package com.loc.newsapp.presentation.login

import android.content.Context
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.loc.newsapp.R
import com.loc.newsapp.presentation.Dimens.MediumPadding1
import com.loc.newsapp.presentation.Dimens.MediumPadding2
import com.loc.newsapp.presentation.common.NewsButton
import com.loc.newsapp.presentation.common.searchBarBorder
import com.loc.newsapp.presentation.search.SearchEvent
import kotlin.reflect.KFunction2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    state: LoginState,
    onLogin: () -> Unit, event: (LoginEvent) -> Unit,
) {


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
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.input_background),
                    textColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
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
                event(LoginEvent.SetUser)
            })
        }


    }

}

//@Preview(showBackground = true, uiMode = 1, showSystemUi = true)
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen() {}
//}