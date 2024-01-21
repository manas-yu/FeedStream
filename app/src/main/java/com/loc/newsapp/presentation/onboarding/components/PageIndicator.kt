package com.loc.newsapp.presentation.onboarding.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.loc.newsapp.Presentation.onboarding.Dimens.IndicatorSize
import com.loc.newsapp.ui.theme.BlueGray

@Composable
fun PageIndicator(
    selectedPage: Int,
    modifier: Modifier = Modifier,
    pageSize: Int,
    unselectedColor: Color = BlueGray,
    selectedColor: Color = MaterialTheme.colorScheme.primaryContainer
) {
    Row {
        repeat(pageSize) {
            Box(
                modifier
                    .size(IndicatorSize)
                    .padding(horizontal = 1.dp)
                    .clip(CircleShape)
                    .background(color = if (it == selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}