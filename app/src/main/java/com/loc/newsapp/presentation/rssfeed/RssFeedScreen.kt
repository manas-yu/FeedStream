package com.loc.newsapp.presentation.rssfeed

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Feed
import com.loc.newsapp.presentation.Dimens
import com.loc.newsapp.presentation.Dimens.ExtraSmallPadding2
import com.loc.newsapp.presentation.Dimens.MediumPadding1
import com.loc.newsapp.presentation.common.CustomTextField
import com.loc.newsapp.presentation.common.NewsButton
import com.loc.newsapp.presentation.common.NewsTextButton
import com.loc.newsapp.presentation.rssfeed.components.FeedsList
import com.loc.newsapp.presentation.rssfeed.components.RssTopBar

@Composable
fun RssFeedScreen(
    onBackClick: () -> Unit,
    onFollowClicked: () -> Unit,
    onUnfollowClick: (Feed) -> Unit,
    onFeedClick: (Feed) -> Unit
) {
    var isDialogue by remember { mutableStateOf(false) }
    var feedName by remember { mutableStateOf("") }
    var feedURL by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(MediumPadding1)
    ) {
        RssTopBar(onBackClick = onBackClick, onFollowClicked = { isDialogue = true })

        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
//        FeedsList(
//            feeds = listOf(
//                Feed(name = "anime", url = "wwd"),
//                Feed(name = "asd", url = "sadasd")
//            ), onClick = {
//                onFeedClick(it)
//            }, onUnfollowClick = {
//                onUnfollowClick(it)
//            })
        if (isDialogue) {
            AlertDialog(onDismissRequest = { isDialogue = false },
                text = {
                    Column {
                        CustomTextField(
                            painter = painterResource(id = R.drawable.rss_icon),
                            placeholder = "Feed Name",
                            onValueChange = {
                                feedName = it
                            },
                            value = feedName
                        )
                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                        CustomTextField(
                            painter = painterResource(id = R.drawable.ic_network),
                            placeholder = "Feed URL",
                            onValueChange = {
                                feedURL = it
                            },
                            value = feedURL
                        )
                    }
                },
                confirmButton = {
                    NewsButton(text = "Follow") {
                        onFollowClicked()
                        isDialogue = false
                    }
                }, dismissButton = {
                    NewsTextButton(
                        text = "Cancel"
                    ) {
                        isDialogue = false
                    }
                })

        }

    }
}