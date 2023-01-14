package com.splanes.gifting.domain.feature.list.wishlist.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.request.DeleteWishlistItemsRequest
import javax.inject.Inject

class DeleteWishlistItemsUseCase @Inject constructor(
    private val repository: WishlistRepository
) : UseCase<DeleteWishlistItemsRequest, Wishlist>() {

    override suspend fun execute(request: DeleteWishlistItemsRequest): Wishlist {
        val wishlist = request.wishlist.copy(
            items = request.wishlist.items.filter { item ->
                request.itemsToDelete.none { it.id == item.id }
            }
        )
        return repository.createOrUpdateWishlist(wishlist)
    }
}
