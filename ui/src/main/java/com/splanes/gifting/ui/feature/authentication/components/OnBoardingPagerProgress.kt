package com.splanes.gifting.ui.feature.authentication.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
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
import com.splanes.gifting.ui.common.components.spacer.row.Spacer
import com.splanes.gifting.ui.common.components.spacer.row.Weight
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.color.withAlpha
import com.splanes.gifting.ui.feature.authentication.model.OnBoardingUiPage

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPagerProgress(
    pagerState: PagerState,
    currentPage: OnBoardingUiPage,
    modifier: Modifier = Modifier,
    onOnBoardingEnd: () -> Unit
) {
    val isLastPage = pagerState.currentPage == pagerState.pageCount - 1
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(width = 16.dp)

        AnimatedVisibility(visible = !isLastPage) {
            GiftingTextButton(
                text = stringResource(id = R.string.skip),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = colorOf {
                        contentColorFor(currentPage.background(this))
                    }
                ),
                onClick = onOnBoardingEnd
            )
        }

        Weight(if (isLastPage) 2.0 else 1.0)

        PagerIndicators(
            pagerState = pagerState,
            indicatorColor = colorOf {
                contentColorFor(
                    backgroundColor = currentPage.background(this).withAlpha(.7)
                )
            }
        )

        Weight(if (!isLastPage) 2.0 else 1.0)

        AnimatedVisibility(
            modifier = Modifier.padding(end = 16.dp),
            visible = isLastPage
        ) {
            GiftingButton(
                text = stringResource(id = R.string.sign_up),
                onClick = onOnBoardingEnd,
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorOf {
                        contentColorFor(currentPage.background(this))
                    }
                )
            )
        }
    }
}
