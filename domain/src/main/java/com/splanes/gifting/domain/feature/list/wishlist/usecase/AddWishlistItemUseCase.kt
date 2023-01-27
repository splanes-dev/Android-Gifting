package com.splanes.gifting.domain.feature.list.wishlist.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.common.utils.timestamp
import com.splanes.gifting.domain.common.utils.uuid
import com.splanes.gifting.domain.feature.list.model.GiftPrice
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem
import com.splanes.gifting.domain.feature.list.wishlist.request.NewWishlistItemRequest
import javax.inject.Inject

class AddWishlistItemUseCase @Inject constructor(
    private val repository: WishlistRepository
) : UseCase<NewWishlistItemRequest, Wishlist>() {

    override suspend fun execute(request: NewWishlistItemRequest): Wishlist {
        val item = WishlistItem(
            wishlistId = request.wishlist.id,
            id = uuid(),
            owner = repository.getUid(),
            name = request.name,
            addedOn = timestamp(),
            description = request.description,
            price = request.price?.let { price -> GiftPrice.Exact(price) },
            url = request.url,
            tags = request.tags,
            notes = request.notes,
            categories = request.categories
        )
        val wishlist = request.wishlist.copy(items = request.wishlist.items + item)
        return repository.createOrUpdateWishlist(wishlist)
    }
}
