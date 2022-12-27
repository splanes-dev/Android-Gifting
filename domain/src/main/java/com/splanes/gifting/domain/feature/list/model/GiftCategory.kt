package com.splanes.gifting.domain.feature.list.model

enum class GiftCategory {
    HomeStuff,
    Books,
    Kids,
    Tech,
    Retail,
    Other,
    Undefined;

    companion object
}

fun GiftCategory.Companion.fromName(name: String) =
    GiftCategory.values().find { it.name == name } ?: GiftCategory.Undefined
