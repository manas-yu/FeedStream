package com.loc.newsapp.Presentation.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.loc.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val img: Int,
)

val pages = listOf<Page>(
    Page(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Bibendum ut tristique et egestas quis ipsum suspendisse ultrices. Pretium nibh ipsum consequat nisl vel pretium. Nunc id cursus metus aliquam eleifend mi in nulla. Commodo nulla facilisi nullam vehicula ipsum a arcu cursus.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Bibendum ut tristique et egestas quis ipsum suspendisse ultrices. Pretium nibh ipsum consequat nisl vel pretium. Nunc id cursus metus aliquam eleifend mi in nulla. Commodo nulla facilisi nullam vehicula ipsum a arcu cursus.",
        R.drawable.onboarding1
    ), Page(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Bibendum ut tristique et egestas quis ipsum suspendisse ultrices. Pretium nibh ipsum consequat nisl vel pretium. Nunc id cursus metus aliquam eleifend mi in nulla. Commodo nulla facilisi nullam vehicula ipsum a arcu cursus.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Bibendum ut tristique et egestas quis ipsum suspendisse ultrices. Pretium nibh ipsum consequat nisl vel pretium. Nunc id cursus metus aliquam eleifend mi in nulla. Commodo nulla facilisi nullam vehicula ipsum a arcu cursus.",
        R.drawable.onboarding2
    ), Page(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Bibendum ut tristique et egestas quis ipsum suspendisse ultrices. Pretium nibh ipsum consequat nisl vel pretium. Nunc id cursus metus aliquam eleifend mi in nulla. Commodo nulla facilisi nullam vehicula ipsum a arcu cursus.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Bibendum ut tristique et egestas quis ipsum suspendisse ultrices. Pretium nibh ipsum consequat nisl vel pretium. Nunc id cursus metus aliquam eleifend mi in nulla. Commodo nulla facilisi nullam vehicula ipsum a arcu cursus.",
        R.drawable.onboarding3
    )
)