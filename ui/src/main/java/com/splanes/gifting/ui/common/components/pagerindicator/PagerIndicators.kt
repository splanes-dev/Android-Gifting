package com.splanes.gifting.ui.common.components.pagerindicator

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.splanes.gifting.ui.common.components.spacer.row.Spacer
import kotlin.math.abs

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerIndicators(
    pagerState: PagerState,
    indicatorColor: Color = Color.Black
) {
    Row {
        val offsetIntPart = pagerState.currentPageOffset.toInt()
        val offsetFractionalPart = pagerState.currentPageOffset - offsetIntPart
        val currentPage = pagerState.currentPage + offsetIntPart
        val targetPage = if (pagerState.currentPageOffset < 0) currentPage - 1 else currentPage + 1
        val currentPageWidth =
            baseWidth * (1 + (1 - abs(offsetFractionalPart)) * MULTIPLIER_SELECTED_PAGE)
        val targetPageWidth = baseWidth * (1 + abs(offsetFractionalPart) * MULTIPLIER_SELECTED_PAGE)

        repeat(pagerState.pageCount) { index ->
            val width = when (index) {
                currentPage -> currentPageWidth
                targetPage -> targetPageWidth
                else -> baseWidth
            }
            Surface(
                modifier = Modifier
                    .width(width)
                    .height(height),
                color = indicatorColor,
                shape = RoundedCornerShape(20.dp)
            ) {}
            if (index != pagerState.pageCount - 1) {
                Spacer(spacing)
            }
        }
    }
}

private const val MULTIPLIER_SELECTED_PAGE = 4
private val baseWidth = 8.dp
private val spacing = 10.dp
private val height = 8.dp
