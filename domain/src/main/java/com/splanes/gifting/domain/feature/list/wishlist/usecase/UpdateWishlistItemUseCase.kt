package com.splanes.gifting.domain.feature.list.wishlist.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem
import com.splanes.gifting.domain.feature.list.wishlist.request.UpdateWishlistItemRequest
import javax.inject.Inject

class UpdateWishlistItemUseCase @Inject constructor(
    private val repository: WishlistRepository
) : UseCase<UpdateWishlistItemRequest, Wishlist>() {

    override suspend fun execute(request: UpdateWishlistItemRequest): Wishlist {
        val item = WishlistItem(
            wishlistId = request.wishlist.id,
            id = request.item.id,
            owner = request.item.owner,
            name = request.item.name,
            addedOn = request.item.addedOn,
            description = request.item.description,
            price = request.item.price,
            url = request.item.url,
            tags = request.item.tags,
            notes = request.item.notes,
            categories = request.item.categories
        )
        val wishlist = request.wishlist.copy(
            items = request.wishlist.items.filter { it.id != request.item.id } + item
        )
        return repository.createOrUpdateWishlist(wishlist)
    }
}
