package com.splanes.gifting.ui.common.components.input.categorypicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.splanes.gifting.domain.feature.list.model.GiftCategory

class GiftCategoryPickerState(
    private val initialSelected: List<GiftCategory> = emptyList()
) {
    var itemsSelected by mutableStateOf(initialSelected)
        private set
    var hasChanged by mutableStateOf(false)
        private set

    fun pick(item: GiftCategory) {
        itemsSelected = if (isSelected(item)) {
            itemsSelected - item
        } else {
            itemsSelected + item
        }
        hasChanged = initialSelected != itemsSelected
    }

    fun isSelected(item: GiftCategory) = itemsSelected.contains(item)
}

@Composable
fun rememberGiftCategoryPickerState(initialSelected: List<GiftCategory> = emptyList()) = remember {
    GiftCategoryPickerState(initialSelected)
}
