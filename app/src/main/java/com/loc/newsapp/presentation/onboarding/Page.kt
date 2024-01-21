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
        "Lorem ipsum is simply dummy ",
        " ${LoremIpsum(20).toString()}", R.drawable.onboarding1
    ), Page(
        "Lorem ipsum is simply dummy ",
        " ${LoremIpsum(20).toString()}", R.drawable.onboarding2
    ), Page(
        "Lorem ipsum is simply dummy ",
        " ${LoremIpsum(20).toString()}", R.drawable.onboarding3
    )
)