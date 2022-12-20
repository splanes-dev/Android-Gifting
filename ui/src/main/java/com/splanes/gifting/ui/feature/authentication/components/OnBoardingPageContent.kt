package com.splanes.gifting.ui.feature.authentication.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.feature.authentication.model.OnBoardingUiPage

@Composable
internal fun OnBoardingPageContent(
    page: OnBoardingUiPage,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = page.image),
            contentDescription = stringResource(id = page.title)
        )

        Spacer(height = 32.dp)

        Text(
            text = stringResource(id = page.title),
            style = textStyleOf { headlineLarge },
            color = colorOf { contentColorFor(backgroundColor = page.background(this)) }
        )

        Spacer(height = 24.dp)

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = page.description),
            style = textStyleOf { bodyLarge },
            color = colorOf { contentColorFor(backgroundColor = page.background(this)) },
            textAlign = TextAlign.Center
        )
    }
}
