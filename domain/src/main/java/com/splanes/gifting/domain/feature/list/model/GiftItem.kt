package com.splanes.gifting.domain.feature.list.model

interface GiftItem {
    val id: String
    val owner: String
    val name: String
    val description: String?
    val price: Double?
    val url: String?
    val addedOn: Long?
    val tags: List<GiftTag>
    val notes: String?
    val categories: List<GiftCategory>
}
