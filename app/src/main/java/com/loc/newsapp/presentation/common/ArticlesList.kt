package com.loc.newsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.presentation.Dimens.MediumPadding1

import com.loc.newsapp.domain.model.Article

@Composable
fun ArticlesList(
    articles: List<Article>,
    onClick: (Article) -> Unit,
    modifier: Modifier = Modifier,
) {


    LazyColumn(
        contentPadding = PaddingValues(MediumPadding1),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        modifier = modifier.fillMaxSize()
    ) {
        items(articles.size) { index ->
            val article = articles[index]
            ArticleCard(article = article, onClick = { onClick(article) })

        }
    }


}

@Composable
fun ArticlesList(
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit,
    modifier: Modifier = Modifier,
) {
    val handlePagingResult = handelPagingResult(articles = articles)
    if (handlePagingResult) {
        LazyColumn(
            contentPadding = PaddingValues(MediumPadding1),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            modifier = modifier.fillMaxSize()
        ) {
            items(articles.itemCount) { index ->
                articles[index]?.let { article ->
                    ArticleCard(article = article, onClick = { onClick(article) })
                }
            }
        }
    }

}

@Composable
private fun handelPagingResult(articles: LazyPagingItems<Article>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen()
            false
        }

        else -> true
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        repeat(10) {
            AnimatedCardShimmerEffect(modifier = Modifier.padding(horizontal = MediumPadding1))
        }
    }

}