package com.splanes.gifting.domain.feature.list.grouplist.model

import com.splanes.gifting.domain.feature.list.model.GiftCategory
import com.splanes.gifting.domain.feature.list.model.GiftItem
import com.splanes.gifting.domain.feature.list.model.GiftPrice
import com.splanes.gifting.domain.feature.list.model.GiftStatus
import com.splanes.gifting.domain.feature.list.model.ItemTag

data class GroupListItem(
    override val id: String,
    override val name: String,
    override val description: String?,
    override val notes: String?,
    override val price: GiftPrice?,
    override val categories: List<GiftCategory>,
    override val tags: List<ItemTag>,
    override val owner: String,
    override val addedOn: Long,
    override val url: String?,
    val groupListId: String,
    val updatedOn: Long,
    val status: GiftStatus
) : GiftItem
