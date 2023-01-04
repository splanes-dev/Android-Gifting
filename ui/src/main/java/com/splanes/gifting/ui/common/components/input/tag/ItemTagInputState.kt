package com.splanes.gifting.ui.common.components.input.tag

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.splanes.gifting.domain.feature.list.model.ItemTag

class ItemTagInputState(
    val tags: List<ItemTag>,
    initialSelected: List<ItemTag> = emptyList()
) {
    val selected: SnapshotStateList<ItemTag> = mutableStateListOf<ItemTag>().apply {
        addAll(initialSelected)
    }

    fun add(text: String) {
        when (val tag = tags.find { tag -> tag.name == text }) {
            null -> {
                selected.add(ItemTag(name = text))
            }

            else -> {
                if (selected.none { t -> t.id == tag.id }) {
                    selected.add(tag)
                }
            }
        }
    }

    fun remove(tag: ItemTag) {
        selected.remove(tag)
    }

    fun predictionsOf(input: String) =
        tags.filter { tag -> tag.name.startsWith(input) }
}
