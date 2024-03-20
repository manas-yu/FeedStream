package com.loc.newsapp.presentation.rssfeed.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Post
import com.loc.newsapp.presentation.Dimens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp

@Composable
fun PostCard(
    post: Post,
    modifier: Modifier = Modifier, onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Gray),
        modifier = modifier
            .clickable { onClick() }

    ) {
        Column(
            modifier.padding(
                horizontal = Dimens.ExtraSmallPadding2,
                vertical = Dimens.ExtraSmallPadding
            )
        ) {
            Text(
                overflow = TextOverflow.Clip,
                text = post.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.text_title
                ),
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier.size(Dimens.SmallIconSize), tint = colorResource(
                        id = R.color.body
                    )
                )
                Spacer(modifier = modifier.width(Dimens.ExtraSmallPadding))
                Text(
                    text = post.publishedAt,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(
                        id = R.color.body
                    ),
                )
            }

        }
    }
}