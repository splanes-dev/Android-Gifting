package com.splanes.gifting.domain.feature.list.grouplist.model

data class GroupList(
    val id: String,
    val name: String,
    val description: String? = null,
    val items: List<GroupListItem> = emptyList(),
    val owner: String,
    val members: List<String>,
    val createdOn: Long,
    val updatedOn: Long
)
