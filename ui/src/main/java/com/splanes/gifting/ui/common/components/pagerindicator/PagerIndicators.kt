package com.splanes.gifting.ui.common.components.pagerindicator

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.splanes.gifting.ui.common.components.spacer.row.Spacer
import com.splanes.gifting.ui.common.utils.color.withAlpha

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerIndicators(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    indicatorColor: Color = Color.Black
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { index ->
            val isSelected = pagerState.currentPage == index
            val bulletSize by animateDpAsState(targetValue = if (isSelected) 12.dp else 8.dp)

            Box {
                Surface(
                    modifier = Modifier.size(bulletSize),
                    color = indicatorColor.apply { if (!isSelected) withAlpha(.5) },
                    shape = RoundedCornerShape(20.dp)
                ) {}
            }
            if (index != pagerState.pageCount - 1) {
                Spacer(spacing)
            }
        }
    }
}

private val spacing = 12.dp
