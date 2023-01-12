package com.splanes.gifting.domain.feature.list.wishlist.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import javax.inject.Inject

class DeleteWishlistsUseCase @Inject constructor(
    private val repository: WishlistRepository
) : UseCase<List<Wishlist>, List<Wishlist>>() {

    override suspend fun execute(request: List<Wishlist>): List<Wishlist> {
        val removed = mutableListOf<Wishlist>()
        request.forEach { wishlist ->
            if (repository.deleteWishlist(wishlist)) {
                removed.add(wishlist)
            }
        }
        return removed
    }
}
