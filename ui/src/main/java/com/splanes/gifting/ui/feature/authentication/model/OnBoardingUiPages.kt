package com.splanes.gifting.ui.feature.authentication.model

import com.splanes.gifting.ui.R

class OnBoardingUiPages {
    val welcome = OnBoardingUiPage(
        image = R.drawable.img_gifting,
        title = R.string.welcome_page_title,
        description = R.string.welcome_page_description,
        background = { primary }
    )
    val syncWithAnyone = OnBoardingUiPage(
        image = R.drawable.img_list_share,
        title = R.string.sync_page_title,
        description = R.string.sync_page_description,
        background = { secondary }
    )
    val privateLists = OnBoardingUiPage(
        image = R.drawable.img_todo_list,
        title = R.string.private_lists_page_title,
        description = R.string.private_lists_page_description,
        background = { tertiary }
    )
    val otherFeatures = OnBoardingUiPage(
        image = R.drawable.img_confetti,
        title = R.string.other_features_page_title,
        description = R.string.other_features_page_description,
        background = { primaryContainer }
    )

    fun toList() = listOf(
        welcome,
        syncWithAnyone,
        privateLists,
        otherFeatures
    )
}
