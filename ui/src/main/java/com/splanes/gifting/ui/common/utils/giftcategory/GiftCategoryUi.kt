package com.splanes.gifting.ui.common.utils.giftcategory // ktlint-disable filename

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoStories
import androidx.compose.material.icons.rounded.Checkroom
import androidx.compose.material.icons.rounded.ChildCare
import androidx.compose.material.icons.rounded.Cottage
import androidx.compose.material.icons.rounded.Devices
import androidx.compose.material.icons.rounded.QuestionMark
import androidx.compose.runtime.Composable
import com.splanes.gifting.domain.feature.list.model.GiftCategory

@Composable
fun GiftCategory.icon() = Icons.Rounded.run {
    when (this@icon) {
        GiftCategory.HomeStuff -> Cottage
        GiftCategory.Books -> AutoStories
        GiftCategory.Kids -> ChildCare
        GiftCategory.Tech -> Devices
        GiftCategory.Retail -> Checkroom
        GiftCategory.Other,
        GiftCategory.Undefined -> QuestionMark
    }
}
