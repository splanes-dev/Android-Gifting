package com.splanes.gifting.data.feature.list.wishlist.entity.mapper

import com.splanes.gifting.data.feature.list.entity.mapper.GiftPriceMapper
import com.splanes.gifting.data.feature.list.wishlist.entity.WishlistDto
import com.splanes.gifting.data.feature.list.wishlist.entity.WishlistItemDto
import com.splanes.gifting.data.feature.tag.entity.ItemTagDto
import com.splanes.gifting.data.feature.tag.entity.mapper.ItemTagMapper
import com.splanes.gifting.domain.feature.list.model.GiftCategory
import com.splanes.gifting.domain.feature.list.model.fromName
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem
import javax.inject.Inject

class WishlistMapper @Inject constructor(
    private val tagMapper: ItemTagMapper,
    private val priceMapper: GiftPriceMapper
) {

    fun map(dto: WishlistDto, tags: List<ItemTagDto> = emptyList()): Wishlist =
        Wishlist(
            id = dto.id.orEmpty(),
            name = dto.name.orEmpty(),
            description = dto.description,
            items = dto.items.orEmpty().map { itemDto -> map(itemDto, tags) },
            owner = dto.owner.orEmpty(),
            createdOn = dto.createdOn ?: 0L,
            updatedOn = dto.updatedOn ?: 0L
        )

    fun map(wishlist: Wishlist): WishlistDto =
        WishlistDto(
            id = wishlist.id,
            description = wishlist.description,
            name = wishlist.name,
            items = wishlist.items.map(::map),
            owner = wishlist.owner,
            createdOn = wishlist.createdOn,
            updatedOn = wishlist.updatedOn
        )

    fun map(dto: WishlistItemDto, tags: List<ItemTagDto>): WishlistItem =
        WishlistItem(
            wishlistId = dto.wishlistId.orEmpty(),
            id = dto.id.orEmpty(),
            owner = dto.owner.orEmpty(),
            name = dto.name.orEmpty(),
            addedOn = dto.createdOn ?: 0L,
            description = dto.description,
            price = dto.price?.let(priceMapper::map),
            url = dto.url,
            tags = tags.filter { tag ->
                dto.tags.orEmpty().contains(tag.id)
            }.map(tagMapper::map),
            notes = dto.notes,
            categories = dto.categories.orEmpty().map(GiftCategory::fromName)
        )

    fun map(item: WishlistItem): WishlistItemDto =
        WishlistItemDto(
            wishlistId = item.wishlistId,
            id = item.id,
            owner = item.owner,
            name = item.name,
            description = item.description,
            createdOn = item.addedOn,
            price = item.price?.let(priceMapper::map),
            url = item.url,
            tags = item.tags.map { tag -> tag.id },
            notes = item.notes,
            categories = item.categories.map { category -> category.name }
        )
}
