package com.loc.newsapp.presentation.rssfeed.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.Post
import com.loc.newsapp.presentation.Dimens
import com.loc.newsapp.presentation.common.ArticleCard
import com.loc.newsapp.presentation.common.EmptyScreen

@Composable
fun PostsList(
    posts: List<Post>,
    onClick: (Post) -> Unit,
    modifier: Modifier = Modifier,
) {

    if (posts.isEmpty()) {
        EmptyScreen()
    }
    LazyColumn(
        contentPadding = PaddingValues(Dimens.MediumPadding1),
        verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
        modifier = modifier
            .fillMaxSize()
            .padding(Dimens.MediumPadding2)
    ) {
        items(posts.size) { index ->
            val post = posts[index]
            PostCard(post = post, onClick = { onClick(post) })
            Divider(color = Color.Gray)

        }
    }


}