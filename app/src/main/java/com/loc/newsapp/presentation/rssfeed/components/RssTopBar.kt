package com.loc.newsapp.presentation.rssfeed.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.loc.newsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RssTopBar(onBackClick: () -> Unit, onFollowClicked: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = { onFollowClicked() }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }

        },
        title = {
            Text(
                text = "Bookmarks",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = colorResource(
                    id = R.color.text_title
                )
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            actionIconContentColor = colorResource(
                id = R.color.body
            ),
            containerColor = Color.Transparent,
            navigationIconContentColor = colorResource(
                id = R.color.body
            ),
        )

    )
}