package com.splanes.gifting.ui.feature.authentication.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.buttons.GiftingButton
import com.splanes.gifting.ui.common.components.buttons.GiftingTextButton
import com.splanes.gifting.ui.common.components.pagerindicator.PagerIndicators
import com.splanes.gifting.ui.common.components.spacer.row.Weight
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.feature.authentication.model.OnBoardingUiPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPagerProgress(
    pagerState: PagerState,
    currentPage: OnBoardingUiPage,
    modifier: Modifier = Modifier,
    onOnBoardingEnd: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val isLastPage = pagerState.currentPage == pagerState.pageCount - 1
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (!isLastPage) {
            GiftingTextButton(
                text = stringResource(id = R.string.skip),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = colorOf {
                        contentColorFor(currentPage.background(this))
                    }
                ),
                onClick = onOnBoardingEnd
            )

            PagerIndicators(
                modifier = Modifier.weight(1f),
                pagerState = pagerState,
                indicatorColor = colorOf {
                    contentColorFor(backgroundColor = currentPage.background(this))
                }
            )
        }

        if (isLastPage) Weight()

        GiftingButton(
            text = stringResource(
                id = if (isLastPage) R.string.sign_up else R.string.next
            ),
            onClick = {
                if (isLastPage) {
                    onOnBoardingEnd()
                } else {
                    coroutineScope.launch { pagerState.next() }
                }
            },
            colors = ButtonDefaults.buttonColors(contentColor = colorOf { onPrimary })
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
private suspend fun PagerState.next() = animateScrollToPage(currentPage + 1)
